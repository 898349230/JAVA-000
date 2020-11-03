package com.ab.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @classname: HttpInboundServer
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/3、22:12
 */
public class HttpInboundServer {

    private int port;
    /** 后端实际调用接口 */
    private String proxyServer;

    public HttpInboundServer(int port, String proxyServer) {
        this.port = port;
        this.proxyServer = proxyServer;
    }

    public void run(){

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        设置参数
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(EpollChannelOption.SO_REUSEPORT, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
//                绑定 HttpInboundInitializer
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInboundInitializer(proxyServer));

        try {
//            nettyServer 监听 port端口
            Channel channel = serverBootstrap.bind(port).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
