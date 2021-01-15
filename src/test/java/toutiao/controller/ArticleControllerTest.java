package toutiao.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import toutiao.model.ArticleRequest;

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
        ArticleRequest articleRequest = new ArticleRequest(1, 1, 1, "财经", ts);

        String jsonString = JSON.toJSONString(articleRequest);
        log.info("jsonString={};", jsonString);


    }
}