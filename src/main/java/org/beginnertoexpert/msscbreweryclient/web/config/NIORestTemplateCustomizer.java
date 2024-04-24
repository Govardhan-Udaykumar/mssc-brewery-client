package org.beginnertoexpert.msscbreweryclient.web.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.nio.AsyncClientConnectionManager;
import org.apache.hc.core5.http.config.Lookup;
import org.apache.hc.core5.http.impl.nio.ClientHttp1IOEventHandlerFactory;
import org.apache.hc.core5.http.nio.ssl.TlsStrategy;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.net.NamedEndpoint;
import org.apache.hc.core5.reactor.*;
import org.apache.hc.core5.reactor.ssl.SSLBufferMode;
import org.apache.hc.core5.reactor.ssl.SSLSessionInitializer;
import org.apache.hc.core5.reactor.ssl.SSLSessionVerifier;
import org.apache.hc.core5.reactor.ssl.TlsDetails;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Component
public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

    private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception{

        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setSoTimeout(3000,TimeUnit.MILLISECONDS)
                .setIoThreadCount(4).build();

        DefaultConnectingIOReactor defaultConnectingIOReactor = new DefaultConnectingIOReactor(null,ioReactorConfig,null,null,null,null,null);

        PoolingAsyncClientConnectionManager connectionManager = new PoolingAsyncClientConnectionManager((Lookup<TlsStrategy>) defaultConnectingIOReactor);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new HttpComponentsClientHttpRequestFactory((HttpClient) httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {

    }
}
