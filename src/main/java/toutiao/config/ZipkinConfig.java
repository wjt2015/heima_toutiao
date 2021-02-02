package toutiao.config;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.github.kristofa.brave.mysql.MySQLStatementInterceptorManagementBean;
import com.github.kristofa.brave.spring.BraveClientHttpRequestInterceptor;
import com.github.kristofa.brave.spring.ServletHandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 参考:
 * [
 * https://www.cnblogs.com/jmcui/p/10940372.html
 * https://www.cnblogs.com/mengyixin/p/9850765.html
 * https://www.cnblogs.com/mengyixin/p/9857343.html
 * https://my.oschina.net/u/4373297/blog/3778393
 * ----
 * zipkin的源码分析:
 * https://my.oschina.net/mozhu/blog/1585187
 * ]
 * ----
 * 利用Zipkin追踪Mysql数据库调用链:
 * [
 * http://www.spring4all.com/article/1079
 * ]
 */
@Slf4j
@Configuration
@Import(value = {BraveClientHttpRequestInterceptor.class, ServletHandlerInterceptor.class})
public class ZipkinConfig {
    @Resource
    private Sender sender;

    @Resource
    private Reporter<Span> reporter;

    @Resource
    private Brave brave;

    @Bean
    public Sender sender() {
        return OkHttpSender.create("http://127.0.0.1:9411/api/v1/spans");
    }

    @Bean
    public Reporter<Span> reporter() {
        return AsyncReporter.builder(sender).build();
    }


    @Bean
    public Brave brave() {
        return new Brave.Builder("heima_toutiao").reporter(reporter).build();
    }

    @Bean
    public SpanNameProvider spanNameProvider() {
        return new DefaultSpanNameProvider();
    }

    @Resource
    private ServletHandlerInterceptor servletHandlerInterceptor;

    @Resource
    private BraveClientHttpRequestInterceptor braveClientHttpRequestInterceptor;

    @Resource
    private RestTemplate restTemplate;

    // adds tracing to the application-defined rest template
    @PostConstruct
    public void init() {
        List<ClientHttpRequestInterceptor> interceptors =
                new ArrayList<>(restTemplate.getInterceptors());
        interceptors.add(braveClientHttpRequestInterceptor);
        restTemplate.setInterceptors(interceptors);
    }

    /**
     * 利用Zipkin追踪Mysql数据库调用链;
     * @return
     */
    @Bean
    public MySQLStatementInterceptorManagementBean mySQLStatementInterceptorManagementBean() {
        return new MySQLStatementInterceptorManagementBean(brave.clientTracer());
    }


}
