package toutiao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.sonatype.aether.resolution.ArtifactRequest;
import toutiao.model.AriticleRequest;

import java.util.Date;

import static org.junit.Assert.*;

@Slf4j
public class ArticleControllerTest {


    @Test
    public void load() {
    }

    @Test
    public void loadMore() {
    }

    @Test
    public void loadNew() {
    }


    @Test
    public void json() {

        long ts = System.currentTimeMillis() - 1000000L;
        AriticleRequest ariticleRequest = new AriticleRequest(1, 1, 1, "财经", ts);

        String jsonString = JSON.toJSONString(ariticleRequest);
        log.info("jsonString={};", jsonString);


    }
}