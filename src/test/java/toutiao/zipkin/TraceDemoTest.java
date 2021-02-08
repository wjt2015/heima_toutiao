package toutiao.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TraceDemoTest {

    private TraceDemo traceDemo;

    @Before
    public void init() {
        traceDemo = new TraceDemo();
    }

    @Test
    public void trace() {
        traceDemo.trace();
    }

    @Test
    public void traceB() {
        traceDemo.traceB();
    }

    @Test
    public void subTrace() {
        traceDemo.subTrace();

    }
}

