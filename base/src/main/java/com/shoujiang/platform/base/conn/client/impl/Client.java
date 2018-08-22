package com.shoujiang.platform.base.conn.client.impl;

import com.shoujiang.platform.base.conn.client.IClient;
import com.shoujiang.platform.base.conn.client.handler.ClientHandler;
import com.shoujiang.platform.base.util.CommonUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: DataCollector
 * @Package com.shoujiang.platform.datacollector.connection.client
 * @Description: 客户端 做测试用
 * @date Date : 2018年08月21日 15:57
 */
public class Client implements IClient {

    private Logger logger = LoggerFactory.getLogger(Client.class);
    //客服端启动类
    private Bootstrap bootstrap;
    //EventLoopGroup
    private EventLoopGroup group;
    //channelFutrue
    private ChannelFuture future;



    /**
     * 客户端初始化
     */
    @Override
    public void init() {
        logger.info("...........................数据采集器客户端初始化   开始...........................");
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup();
        bootstrap.group(group);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new ClientHandler());
            }
        });
        logger.info("...........................数据采集器客户端初始化   完成...........................");
    }

    /**
     * 客户端启动
     */
    @Override
    public void start() {
        logger.info("...........................数据采集器客户端启动   开始...........................");
        //1.初始胡
        init();
        //2.启动
        try {
            future = bootstrap.connect(ClientConfig.ip, ClientConfig.port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info("...........................数据采集器客户端启动   失败...........................");
        } finally {
            //close();
        }
        logger.info("...........................数据采集器客户端启动   完成...........................");
    }

    /**
     * 客户端关闭
     */
    @Override
    public void close() {
        logger.info("...........................数据采集器客户端关闭   开始...........................");
        if (CommonUtil.isNotNull(group)){
            group.shutdownGracefully();
        }
        if(CommonUtil.isNotNull(bootstrap)){
            bootstrap.clone();
        }
        logger.info("...........................数据采集器客户端关闭   完成...........................");
    }

    public void sendMsg(String msg){
        logger.info("客户端发送数据："+msg);
        future.channel().writeAndFlush(msg);
    }
}
