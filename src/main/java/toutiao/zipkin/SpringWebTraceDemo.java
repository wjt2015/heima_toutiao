package toutiao.zipkin;

import brave.Tracing;
import brave.spring.web.TracingAsyncClientHttpRequestInterceptor;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.AsyncClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * [
 * https://github.com/openzipkin/brave/tree/master/instrumentation/spring-web
 * <p>
 * HttpAsyncClient应用举例:
 * https://www.cnblogs.com/yz-yang/p/11596844.html
 * https://sq.163yun.com/blog/article/200013225085108224
 * ]
 */
@Slf4j
public class SpringWebTraceDemo {

    public static void httpTrace(final Tracing tracing, final String queryUrl) {
        AsyncClientHttpRequestInterceptor asyncClientHttpRequestInterceptor = TracingAsyncClientHttpRequestInterceptor.create(tracing);
        //CloseableHttpAsyncClient;
        try (CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createSystem()) {

            HttpComponentsAsyncClientHttpRequestFactory requestFactory = new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
            requestFactory.setReadTimeout(1000);
            requestFactory.setConnectTimeout(1000);
            AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate(requestFactory);
            asyncRestTemplate.setInterceptors(Lists.newArrayList(asyncClientHttpRequestInterceptor));

            get(asyncRestTemplate, requestFactory, queryUrl);

        } catch (Exception e) {
            log.error("CloseableHttpAsyncClient error!", e);
        } finally {

        }

    }

    private static void get(final AsyncRestTemplate asyncRestTemplate, AsyncClientHttpRequestFactory requestFactory, final String queryUrl) {
        ResponseEntity<String> responseEntity = null;
        try {
            ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity(queryUrl, String.class);
            log.info("future={};", future);
            responseEntity = future.get(2, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("get error!url={};", queryUrl, e);
        }
        log.info("responseEntity={};", responseEntity, responseEntity);
    }

}
