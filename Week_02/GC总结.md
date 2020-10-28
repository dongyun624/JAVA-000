在Parallel GC 下， young GC 处理 young 区， Full GC 处理 young 区 和 old 区。
并行GC 如果堆内存设置为256m， 在触发几次youngGC后会一直触发FullGC;堆内存设置越大youngGC次数越少，对应的GC时间越长；
由于并行GC会占用服务全部cpu进行GC，所以内存设置不应太大，以免影响到业务吞吐量。建议堆内存512m-4g;

CMSGC 在堆内存较小的阶段执行GC的次数比较多，随着堆内存的增大，GC次数逐渐减小；CMSGC 年轻代用串性GC，老年代用CMSGC；
CMSGC 有六个阶段：
阶段 1:Initial Mark(初始标记)
阶段 2:Concurrent Mark(并发标记)
阶段 3:Concurrent Preclean(并发预清理) 
阶段 4: Final Remark(最终标记)
阶段 5: Concurrent Sweep(并发清除) 
阶段 6: Concurrent Reset(并发重置)
只有第一个阶段和第四个阶段会STW，但是随着堆内存的增大，STW时间也会变大，不太可控；CMS在堆内存512m之后，生成对象次数增加不是特别明显


G1GC 的五个阶段：
阶段 1: Initial Mark(初始标记)
阶段 2: Root Region Scan(Root区扫描)
阶段 3: Concurrent Mark(并发标记)
阶段 4: Remark(再次标记)
阶段 5: Cleanup(清理)
G1GC适合堆内存较大的场景，堆内存8G以上，特别适合G1GC，堆内存4G到8G，也很建议并行GC 和 G1GC;

Parallel GC、CMS GC、G1GC 三种GC，G1GC 造成的GC停顿时间可控。
Parallel GC 在 GC 期间，所有 CPU 内核都在并行清理垃圾，所以总暂停时间更短;在两次 GC 周期的间隔期，没有 GC 线程在运行，不会消耗任何系统资源。但是堆内存较大时，会影响业务吞吐量，系统不友好。

三种GC情况下，请求速度差不多，主要是在于GC时的停顿时间长短，以及对业务造成的影响。




