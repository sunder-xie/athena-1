package com.tqmall.athena.common.utils;

import com.tqmall.athena.common.bean.HttpClientResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.concurrent.Future;


/**
 * Created by ximeng on 2015/4/15.
 * 接口http get posr类
 */
@Slf4j
public class HttpClientUtil {
    private final static String defaultCharset = "UTF-8";
    private static CloseableHttpAsyncClient httpclient = createClient();

    private static CloseableHttpAsyncClient createClient() {
        // Set HTTP params.
        // Create I/O reactor configuration
        IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setIoThreadCount(5).setConnectTimeout(5000).setSoTimeout(5000)
                .setSoKeepAlive(true).build();

        // Create a custom I/O reactort
        ConnectingIOReactor ioReactor;
        try {
            ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        } catch (IOReactorException e) {
            throw new RuntimeException(e);
        }

        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor);
        HttpAsyncClientBuilder httpClientBuilder = HttpAsyncClients.custom().setConnectionManager(connManager);

        RequestConfig defaultRequestConfig = RequestConfig.custom().setStaleConnectionCheckEnabled(true).setConnectTimeout(5000)
                .setSocketTimeout(5000).build();

        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
        httpClientBuilder.setMaxConnTotal(10);
        httpClientBuilder.setUserAgent("auction-agent");

        CloseableHttpAsyncClient httpclient = httpClientBuilder.build();

        httpclient.start();

        return httpclient;
    }

    /**
     *
     * @param url
     * @return
     */
    public static HttpClientResult getUrl(String url) {
        return getUrl(url, null);
    }

    /**
     *
     * @param url
     * @param nvps
     * @return
     */
    public static HttpClientResult getUrl(String url, List<NameValuePair> nvps) {
        try {
            if (nvps != null && nvps.size() > 0) {
                String params = (URLEncodedUtils.format(nvps, defaultCharset));
                url = url + "?" + params;
            }

            HttpGet httpGet = new HttpGet(url);

            Future<HttpResponse> future = httpclient.execute(httpGet, null);
            HttpResponse response = future.get();

            HttpClientResult result = new HttpClientResult();
            result.setStatusCode(response.getStatusLine().getStatusCode());

            log.info("http get url:" + url + ", status:" + result.getStatusCode());

            if (result.getStatusCode() != HttpStatus.SC_OK) {
                return result;
            }

            HttpEntity entity = response.getEntity();

            result.setResult(EntityUtils.toString(entity, defaultCharset));
            return result;
        } catch (Exception e) {
            log.error("http get error, url:" + url, e);
        }
        return null;
    }

    /**
     *
     * @param url
     * @return
     */
    public static HttpClientResult postUrl(String url) {
        return postUrl(url, null);
    }

    /**
     *
     * @param url
     * @param nvps
     * @return
     */
    public static HttpClientResult postUrl(String url, List<NameValuePair> nvps) {
        try {
            HttpPost httpost = new HttpPost(url);

            if (nvps != null && nvps.size() > 0) {
                httpost.setEntity(new UrlEncodedFormEntity(nvps, defaultCharset));
            }

            Future<HttpResponse> future = httpclient.execute(httpost, null);

            HttpResponse response = future.get();

            HttpClientResult result = new HttpClientResult();
            result.setStatusCode(response.getStatusLine().getStatusCode());
            if (result.getStatusCode() != HttpStatus.SC_OK) {
                return result;
            }

            HttpEntity entity = response.getEntity();

            result.setResult(EntityUtils.toString(entity, defaultCharset));
            return result;
        } catch (Exception ex) {
            throw new RuntimeException("HttpClientUtil postUrl error", ex);
        }
    }


    public static HttpClientResult postJson(String url, String jsonString){
        try {
            HttpPost request = new HttpPost(url);
            if (jsonString != null && !"".equals(jsonString)) {
                request.setEntity(new StringEntity(jsonString));
            }
            request.addHeader("Content-Type", "application/json");

            Future<HttpResponse> future  = httpclient.execute(request,null);
            HttpResponse response = future.get();

            HttpClientResult result = new HttpClientResult();
            result.setStatusCode(response.getStatusLine().getStatusCode());
            if (result.getStatusCode() != HttpStatus.SC_OK) {
                return result;
            }

            HttpEntity entity = response.getEntity();

            result.setResult(EntityUtils.toString(entity, defaultCharset));
            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}