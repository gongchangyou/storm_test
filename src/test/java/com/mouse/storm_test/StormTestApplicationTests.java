package com.mouse.storm_test;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StormTestApplicationTests {

    @Test
    void contextLoads() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("SpoutTest", new SpoutTest(), 1);

        builder.setBolt("Step03Bolt", new Step03Bolt(), 1).setNumTasks(1).shuffleGrouping("SpoutTest");
//        builder.setBolt("Step02Bolt", new Step02Bolt(), 5).localOrShuffleGrouping("Step01Bolt", "Step02Bolt");
//        builder.setBolt("Step03Bolt", new Step03Bolt(), 5).localOrShuffleGrouping("Step02Bolt", "Step03Bolt");

        Config conf = new Config();
        conf.setDebug(false);
        conf.put("enable.auto.commit", false);
        conf.setNumAckers(5); // 生成多少个线程来执行acker，acker负责跟踪任务是否执行完成。如果不设置，系统默认生成一个线程来跟踪任务。
        conf.setMaxSpoutPending(2000); // 设置任务在发出后，但还没处理完成的中间状态任务的最大数量
        conf.setMessageTimeoutSecs(15); // 设置任务在多久之内没处理完成，就任务这个任务处理失败,默认30秒；
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("SDataTopology", conf, builder.createTopology());

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cluster.shutdown();
    }

}
