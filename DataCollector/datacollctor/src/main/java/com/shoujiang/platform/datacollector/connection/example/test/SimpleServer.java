package com.shoujiang.platform.datacollector.connection.example.test;

import com.shoujiang.platform.datacollector.connection.server.impl.CollectorServer;
import org.apache.log4j.BasicConfigurator;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platform.datacollector.connection.example
 * @Description: 实现简单的通信
 * @date Date : 2018年08月21日 16:14
 */
public class SimpleServer {

    public static void main(String[] args){
        BasicConfigurator.configure();
        CollectorServer server = new CollectorServer();
        server.start();
    }
}
