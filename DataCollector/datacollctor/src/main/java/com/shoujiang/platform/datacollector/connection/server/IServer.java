package com.shoujiang.platform.datacollector.connection.server;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: datacollector
 * @Package com.shoujiang.platform.datacollector.connection
 * @Description: 服务器接口
 * @date Date : 2018年08月20日 9:36
 */
public interface IServer {

    public void init();

    public void start();

    public void close();

}
