package toutiao.zipkin;

import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ExtraFieldPropagation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import toutiao.common.CommonData;
import zipkin2.Span;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
public class SpringWebTraceDemoTest {

    @Test
    public void httpTrace() {

        try (OkHttpSender okHttpSender = OkHttpSender.create(CommonData.V2_SPAN_SENDER_URL);
             AsyncReporter<Span> spanAsyncReporter = AsyncReporter.builder(okHttpSender)
                     .closeTimeout(1, TimeUnit.SECONDS).build(SpanBytesEncoder.JSON_V2);

             Tracing tracing = Tracing.newBuilder()
                     .spanReporter(spanAsyncReporter)
                     .localServiceName("http_trace2")
                     .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "http_argv"))
                     .currentTraceContext(CurrentTraceContext.Default.create())
                     .build()) {

            //SpringWebTraceDemo.httpTrace(tracing,"https://www.jianshu.com/p/f177a5e2917f");
            SpringWebTraceDemo.httpTrace(tracing,"http://localhost:8055/toutiao/home.json");

        } catch (Exception e) {
            log.error("trace error!", e);
        }
    }

}

