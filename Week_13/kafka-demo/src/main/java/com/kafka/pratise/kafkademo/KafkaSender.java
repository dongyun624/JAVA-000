package com.kafka.pratise.kafkademo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author : dabing
 * @date : 2021/1/12
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(int i) {
        JSONObject message = new JSONObject();
        message.put("time", System.currentTimeMillis());
        message.put("msg", "第"+i + "条消息");
        message.put("price", "hhhh");
        kafkaTemplate.send("topic", message.toJSONString());
    }

}
