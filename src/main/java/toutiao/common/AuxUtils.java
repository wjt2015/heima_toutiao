package toutiao.common;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AuxUtils {

    public static List<String> processResult(final Process process) {
        List<String> result = Collections.EMPTY_LIST;
        String sumStr = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            result = reader.lines().collect(Collectors.toList());
            sumStr = Joiner.on("\n").join(result);
        } catch (Exception e) {
            log.error("process error!process={};", process, e);
        }
        log.info("sumStr=\n{}", sumStr);
        return result;
    }
}
