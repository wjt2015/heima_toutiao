package toutiao.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class KMPTest {

    @Test
    public void next() {

        char[] pattern={'a','a','c','d','a','a','a','c','d','e','f'};
        int[] next = KMP.next(pattern);
        log.info("next={};",next);

        char[] s={'d','a','a','c','d','a','a','a','c','d','e','f','e'};
        int matchIndex = KMP.matchIndex(s, pattern, next);
        log.info("matchIndex={};",matchIndex);

    }


}