package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.jetbrains.annotations.NotNull;

/**
 * @author : dabing
 * @date : 2020/11/4
 */
public class OkHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(@NotNull FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("nio", "dabing");
    }
}
