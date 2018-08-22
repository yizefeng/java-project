package com.shoujiang.platform.datacollector.connection.client;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platform.datacollector.connection.client
 * @Description: TODO
 * @date Date : 2018年08月21日 15:48
 */
public interface IClient {
    public void init();

    public void start();

    public void close();
}
