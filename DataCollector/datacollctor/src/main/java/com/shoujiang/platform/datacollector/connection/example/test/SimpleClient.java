package com.shoujiang.platform.datacollector.connection.example.test;

import com.shoujiang.platform.datacollector.connection.client.impl.CollectorClient;
import org.apache.log4j.BasicConfigurator;

import java.util.Scanner;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platform.datacollector.connection.example
 * @Description: TODO
 * @date Date : 2018年08月21日 16:22
 */
public class SimpleClient {
    public static void main(String[] args){
        BasicConfigurator.configure();
        CollectorClient client = new CollectorClient();
        client.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            client.sendMsg(scanner.next());
        }
    }
}
