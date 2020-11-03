package com.ab.gateway.outbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @classname: HttpOutboundHandler
 * @description:
 * @author: sunxinbo
 * @time: 2020/11/3、22:29
 */
public class HttpOutboundHandler {

    private CloseableHttpClient defaultClient;
    private String backendUrl;

    public HttpOutboundHandler(String proxyServer) {
        defaultClient = HttpClients.createDefault();
        this.backendUrl = proxyServer.endsWith("/")?proxyServer.substring(0,proxyServer.length()-1):proxyServer;
    }

    public void handle(ChannelHandlerContext ctx, FullHttpRequest fullRequest){
        CloseableHttpResponse execute = null;
        final String url = this.backendUrl + fullRequest.uri();
        HttpHeaders headers = fullRequest.headers();
        System.out.println(headers);
        System.out.println("url : " + url);
        FullHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            List<Map.Entry<String, String>> entries = headers.entries();
            if(null != entries){
                for (Map.Entry<String, String> entry : entries) {
                    if("nio".equals(entry.getKey())){
                        httpGet.addHeader("nio", entry.getValue());
                    }
                }
            }
            execute = defaultClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            System.out.println("statusCode : " + execute.getStatusLine().getStatusCode());
            byte[] body = EntityUtils.toByteArray(entity);
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(execute.getFirstHeader("Content-Length").getValue()));
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (!HttpUtil.isKeepAlive(fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                //response.headers().set(CONNECTION, KEEP_ALIVE);
                ctx.write(response);
            }
            ctx.flush();
            //ctx.close();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
