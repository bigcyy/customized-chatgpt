package com.cyy.chat.config;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.http.HttpClient;

/**
 * @author CYY
 * @date 2023年03月11日 下午9:30
 * @description
 */
@Configuration
public class DefaultConfig {
    @Value("${proxy.ip}")
    private String proxyIp;
    @Value("${proxy.port}")
    private int proxyPort;
    @Value("${milvus.ip}")
    private String milvusIp;
    @Value("${milvus.port}")
    private int milvusPort;
    @Bean
    public OkHttpClient openAIHttpClient(){
        return new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp,proxyPort))).build();
    }
    @Bean
    public MilvusServiceClient milvusClient(){
        return new MilvusServiceClient(
                ConnectParam.newBuilder()
                        .withHost(milvusIp)
                        .withPort(milvusPort)
                        .build()
        );
    }
}
