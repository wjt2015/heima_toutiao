package toutiao.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class MaxSubSetMethodTest {

    @Test
    public void findMaxSubSet() {

        int[] a = {0, 5, 6, 9, 10, 2, 7, 4, 3, 1, 8};
        List<Integer> vList = MaxSubSetMethod.findMaxSubSet(a);
        log.info("vList={}", vList);

    }
}