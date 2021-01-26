package toutiao.alg;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.*;
@Slf4j
public class AlgMethodTest {

    @Test
    public void kettle() throws FileNotFoundException {
        AlgMethod.kettle();

    }

    @Test
    public void array(){
        String s = Arrays.asList(new int[]{1, 2, 3}).toString();
        log.info("s={};",s);
    }
}