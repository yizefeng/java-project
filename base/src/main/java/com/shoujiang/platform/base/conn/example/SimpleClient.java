package com.shoujiang.platform.base.conn.example;

import com.shoujiang.platform.base.conn.client.impl.Client;
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
        Client client = new Client();
        client.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            client.sendMsg(scanner.next());
        }
    }
}
