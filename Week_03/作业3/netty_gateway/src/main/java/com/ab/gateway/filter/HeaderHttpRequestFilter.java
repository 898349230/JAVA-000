
package com.ab.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @classname: HeaderFilter
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/3、23:43
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders entries = fullRequest.trailingHeaders();
        entries.set("nio","sunxinbo");
    }
}
