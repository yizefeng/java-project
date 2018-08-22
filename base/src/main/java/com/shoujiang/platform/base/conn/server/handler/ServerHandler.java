package com.shoujiang.platform.base.conn.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : zengqiang
 * @version V1.0
 * @Project: datacollector
 * @Package com.shoujiang.platform.com.shoujiang.platform.datacollector.connection.server.handler
 * @Description: 数据处理Handler
 * @date Date : 2018年08月21日 14:17
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("server receive message :"+ msg);
        ctx.channel().writeAndFlush("yes server already accept your message" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
