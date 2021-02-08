package toutiao.zipkin;

import brave.ScopedSpan;
import brave.Tracer;
import brave.Tracing;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext;
import brave.propagation.ExtraFieldPropagation;
import brave.propagation.TraceContext;
import lombok.extern.slf4j.Slf4j;
import toutiao.common.AuxUtils;
import zipkin2.Span;
import zipkin2.codec.SpanBytesEncoder;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;

import java.util.concurrent.TimeUnit;

/**
 * Java分布式跟踪系统Zipkin（二）：Brave源码分析-Tracer和Span
 * [
 * https://blog.csdn.net/chengwu4352/article/details/100704190?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
 * ]
 */
@Slf4j
public class TraceDemo {

    private static final String SENDER_URL = "http://localhost:9411/api/v2/spans";

    public void trace() {

        String[] traceNames = {"wjt7", "wjt8", "wjt9"};

        //zipkinSpanHandler = AsyncZipkinSpanHandler.create(sender);
        try (OkHttpSender okHttpSender = OkHttpSender.create(SENDER_URL);
             AsyncReporter<Span> spanAsyncReporter = AsyncReporter.builder(okHttpSender)
                     .closeTimeout(1, TimeUnit.SECONDS).build(SpanBytesEncoder.JSON_V2);
             Tracing tracing = Tracing.newBuilder()
                     .spanReporter(spanAsyncReporter)
                     .localServiceName("heima_toutiao")
                     .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user_name"))
                     .currentTraceContext(CurrentTraceContext.Default.create())
                     .build()) {

            final Tracer tracer = tracing.tracer();
            for (String traceName : traceNames) {

                ScopedSpan span = tracer.startScopedSpan("wjt_scoped");

                //brave.Span span = tracer.newTrace().name(traceName).start();

                log.info("tracing={};tracer={};span={};", tracing, tracer, span);
                span.tag("wb1", "wb_100");
                span.annotate(traceName + "_annotation");

                subFuncA(tracing, traceName, tracing.currentTraceContext().get());

                span.finish();

/*
                brave.Span nextSpan = tracer.nextSpan();
                nextSpan.tag("next_wb1", "next_wb_100");
                nextSpan.annotate(traceName + "_annotation_next");
                log.info("tracing={};tracer={};nextSpan={};", tracing, tracer, nextSpan);
                nextSpan.finish();*/

            }

        } catch (Exception e) {
            log.error("trace error!", e);
        }
    }

    private static void subFuncA(final Tracing tracing, final String traceName, final TraceContext traceContext) {
        final Tracer tracer = tracing.tracer();
        brave.Span span = tracer.newChild(traceContext).name("sub_" + traceName).annotate("subFuncA").tag(traceName + "_tag", "tag_subFuncA").start();
        AuxUtils.sleep(200);
        span.finish();
    }


    public void traceB() {
        brave.Span rootSpan, childSpanA, childSpanB;
        ScopedSpan rootScopedSpan;
        CurrentTraceContext currentTraceContext = CurrentTraceContext.Default.create();

        try (OkHttpSender okHttpSender = OkHttpSender.create(SENDER_URL);
             AsyncReporter<Span> asyncReporter = AsyncReporter.builder(okHttpSender).closeTimeout(500, TimeUnit.MILLISECONDS).build(SpanBytesEncoder.JSON_V2);
             Tracing tracing = Tracing.newBuilder().spanReporter(asyncReporter).localServiceName("wjt_server")
                     .propagationFactory(ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "user_name"))
                     .currentTraceContext(currentTraceContext)
                     .build()
        ) {
            Tracer tracer = tracing.tracer();
            //rootSpan = tracer.newTrace().name("rootSpan").start();
            rootScopedSpan = tracer.startScopedSpan("rootScopedSpan");

            //TraceContext traceContext = tracing.currentTraceContext().get();
            //TraceContext traceContext = currentTraceContext.get();
            TraceContext traceContext = tracing.currentTraceContext().get();

            log.info("traceContext={};", traceContext);

            subFunc(tracer, traceContext, "sub_A");
            subFunc(tracer, traceContext, "sub_B");
            subFunc(tracer, traceContext, "sub_C");


            rootScopedSpan.finish();

        } catch (Exception e) {
            log.error("traceB error!", e);
        } finally {
        }
    }

    private static void subFunc(final Tracer tracer, final TraceContext traceContext, final String spanName) {
        brave.Span span = tracer.newChild(traceContext).name(spanName)
                .tag("subFunc", spanName + "_subFunc")
                .annotate("annotation_" + spanName)
                .start();

        AuxUtils.sleep(150);

        span.finish();

    }


}


