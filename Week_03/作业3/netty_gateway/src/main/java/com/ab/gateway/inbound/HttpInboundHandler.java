package com.ab.gateway.inbound;

import com.ab.gateway.filter.HeaderHttpRequestFilter;
import com.ab.gateway.filter.HttpRequestFilter;
import com.ab.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * @classname: HttpInboundHandler
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/3、22:21
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private String proxyServer;
    private HttpOutboundHandler outboundHandler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        outboundHandler = new HttpOutboundHandler(proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            filter.filter(fullRequest, ctx);
            outboundHandler.handle(ctx, fullRequest);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
