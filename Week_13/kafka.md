# kafka单节点
1、进入kafka_2.13-2.7.0目录
vim ./config/server.properties
listeners=PLAINTEXT://localhost:9092

2、后台启动 zookeeper : 
nohup bin/zookeeper-server-start.sh config/zookeeper.properties

3、启动kafka：
nohup ./bin/kafka-server-start.sh ./config/server.properties &

4、创建topic   
1.  看有多少topic   
bin/kafka-topics.sh --zookeeper localhost:2181 --list    
2.  创建testk 的topic, 1个副本，4个partitions    
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic testk --partitions 4  --replication-factor 1
3.  查看testk 的topic信息    
bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic testk

5、 创建consumer   
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --from-beginning --topic testk

6、 生产消息  
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic testk

7、性能测试  
bin/kafka-producer-perf-test.sh --topic testk --num-records 1000000 --record-size 1000  --throughput 1000000 --producer-props bootstrap.servers=localhost:9092
结果：
645441 records sent, 129088.2 records/sec (123.11 MB/sec), 224.6 ms avg latency, 459.0 ms max latency.
1000000 records sent, 151263.046438 records/sec (144.26 MB/sec), 197.68 ms avg latency, 459.00 ms max latency, 194 ms 50th, 308 ms 95th, 412 ms 99th, 444 ms 99.9th.

bin/kafka-consumer-perf-test.sh --bootstrap-server  localhost:9092 --topic testk --fetch-size 1048576 --messages 1000000 --threads 1
结果：
WARNING: option [threads] and [num-fetch-threads] have been deprecated and will be ignored by the test
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2021-01-12 21:20:52:623, 2021-01-12 21:20:55:457, 953.6743, 336.5118, 1000002, 352858.8567, 1610457652916, -1610457650082, -0.0000, -0.0006

---
# kafka集群
1、进入kafka_2.13-2.7.0目录   
cp config/server.properties kafka9001.properties
cp config/server.properties kafka9001.properties
cp config/server.properties kafka9001.properties

2、更改配置文件   
vi kafka9001.properties, 设置 broker.id=1, log.dirs=/tmp/kafka-logs1, listeners=PLAINTEXT://localhost:9001,broker.list=localhost:9001,localhost:9002,localhost:9003,zookeeper.connect=localhost:2181   
vi kafka9002.properties, 设置 broker.id=2, log.dirs=/tmp/kafka-logs2, listeners=PLAINTEXT://localhost:9002,broker.list=localhost:9001,localhost:9002,localhost:9003,zookeeper.connect=localhost:2181   
vi kafka9003.properties, 设置 broker.id=3, log.dirs=/tmp/kafka-logs3, listeners=PLAINTEXT://localhost:9003,broker.list=localhost:9001,localhost:9002,localhost:9003,zookeeper.connect=localhost:2181   

3、清理掉zk上的所有数据   
可以删除zk的本地文件或者用ZooInspector操作:   
rm -rf /tmp/kafka,    
启动zookeeper:    
nohup bin/zookeeper-server-start.sh config/zookeeper.properties

4、启动3个kafka: 三个命令行下进入kafka目录，分别执行   
./bin/kafka-server-start.sh kafka9001.properties &   
./bin/kafka-server-start.sh kafka9002.properties &   
./bin/kafka-server-start.sh kafka9003.properties &   

5、创建topic
bin/kafka-topics.sh --zookeeper localhost:2181 --list    
bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic test32   
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic test32 --partitions 3  --replication-factor 2

bin/kafka-topics.sh --zookeeper localhost:2181 --describe --topic test32

6、创建producer、consumer   
bin/kafka-console-producer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --topic test32   
bin/kafka-console-consumer.sh --bootstrap-server localhost:9001,localhost:9002,localhost:9003 --topic test32 --from-beginning   

7、压测   
bin/kafka-producer-perf-test.sh --topic test32 --num-records 1000000 --record-size 1000  --throughput 1000000 --producer-props bootstrap.servers=localhost:9002   
结果：   
387424 records sent, 77484.8 records/sec (73.90 MB/sec), 335.1 ms avg latency, 1137.0 ms max latency.
598335 records sent, 119667.0 records/sec (114.12 MB/sec), 290.2 ms avg latency, 1072.0 ms max latency.
1000000 records sent, 98231.827112 records/sec (93.68 MB/sec), 309.70 ms avg latency, 1137.00 ms max latency, 127 ms 50th, 1025 ms 95th, 1092 ms 99th, 1134 ms 99.9th.

bin/kafka-producer-perf-test.sh --topic test32 --num-records 1000000 --record-size 1000  --throughput 1000000 --producer-props bootstrap.servers=localhost:9002,localhost:9001,localhost:9003   
结果：   
402052 records sent, 80410.4 records/sec (76.69 MB/sec), 326.5 ms avg latency, 1005.0 ms max latency.
1000000 records sent, 102092.904543 records/sec (97.36 MB/sec), 297.51 ms avg latency, 1005.00 ms max latency, 100 ms 50th, 948 ms 95th, 988 ms 99th, 1002 ms 99.9th.

bin/kafka-consumer-perf-test.sh --bootstrap-server  localhost:9002 --topic test32 --fetch-size 1048576 --messages 1000000 --threads 1   
结果：   
WARNING: option [threads] and [num-fetch-threads] have been deprecated and will be ignored by the test
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2021-01-12 20:42:50:308, 2021-01-12 20:42:51:848, 954.0691, 619.5254, 1000416, 649620.7792, 1610455370636, -1610455369096, -0.0000, -0.0006

bin/kafka-consumer-perf-test.sh --bootstrap-server  localhost:9002,localhost:9001,localhost:9003 --topic test32 --fetch-size 1048576 --messages 1000000 --threads 1   
结果：   
WARNING: option [threads] and [num-fetch-threads] have been deprecated and will be ignored by the test
start.time, end.time, data.consumed.in.MB, MB.sec, data.consumed.in.nMsg, nMsg.sec, rebalance.time.ms, fetch.time.ms, fetch.MB.sec, fetch.nMsg.sec
2021-01-12 20:43:13:810, 2021-01-12 20:43:15:216, 954.0329, 678.5440, 1000378, 711506.4011, 1610455394096, -1610455392690, -0.0000, -0.0006

结论：集群情况下，单节点性能不能单个kafka实例。整体性能基本一致
