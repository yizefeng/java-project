package com.shoujiang.platform.base.conn.example;

import com.shoujiang.platform.base.conn.server.impl.Server;
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
        Server server = new Server();
        server.start();
    }
}
