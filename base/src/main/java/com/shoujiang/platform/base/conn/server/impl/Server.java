package com.shoujiang.platform.base.conn.server.impl;

import com.shoujiang.platform.base.conn.server.IServer;
import com.shoujiang.platform.base.conn.server.handler.ServerHandler;
import com.shoujiang.platform.base.util.CommonUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
 * @Project: datacollector
 * @Package com.shoujiang.platform.datacollector.connection
 * @Description: 采集器服务器
 * @date Date : 2018年08月21日 10:52
 */
public class Server implements IServer {

    private Logger logger = LoggerFactory.getLogger(Server.class);
    //Server客户端
    private static ServerBootstrap bootstrap;
    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2;
    //EventLoopGroup
    private static EventLoopGroup boss;
    private static EventLoopGroup worker;
    //channelFuture
    private ChannelFuture future;

    /**
     * 服务端初始化
     */
    @Override
    public void init() {
        logger.info("...........................数据采集器服务初始化 开始...........................");
        //Server客户端
        bootstrap = new ServerBootstrap();
        boss = new NioEventLoopGroup(BIZGROUPSIZE);
        worker = new NioEventLoopGroup(BIZGROUPSIZE);
        bootstrap.group(boss, worker);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new ServerHandler());
            }
        });
        /*bootstrap.option(ChannelOption.TCP_NODELAY,true);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
        bootstrap.option(ChannelOption.MAX_MESSAGES_PER_READ,Integer.MAX_VALUE);*/
        logger.info("...........................数据采集器服务初始化 完成...........................");
    }

    /**
     * 启动服务器
     */
    @Override
    public void start() {
        logger.info("...........................数据采集器服务启动   开始...........................");
        //1.初始化服务端
        init();
        //2.启动
        try {
            future = bootstrap.bind(ServerConfig.ip, ServerConfig.port).sync();
            // 监听服务器关闭监听
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            close();
            System.exit(0);
            logger.info("...........................数据采集器服务启动   失败...........................");
        } finally {
            //close();
        }
        logger.info("...........................数据采集器服务启动   完成...........................");
    }

    /**
     *关闭服务器
     */
    @Override
    public void close() {
        logger.info("...........................数据采集器服务关闭   开始...........................");
        if(CommonUtil.isNotNull(boss)){
            boss.shutdownGracefully();
        }
        if(CommonUtil.isNotNull(worker)){
            worker.shutdownGracefully();
        }
        logger.info("...........................数据采集器服务关闭   完成...........................");
    }

}
