package toutiao.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toutiao.dao.ArticleHomeMapper;
import toutiao.model.ArticleHomeEntity;

import javax.annotation.Resource;

import java.util.Date;

import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class DaoConfigTest {
    @Resource
    private ArticleHomeMapper articleHomeMapper;

    @Test
    public void insert() {
        long ts = System.currentTimeMillis() - 1000000L;
        ArticleHomeEntity articleHomeEntity = new ArticleHomeEntity(null, null, null, "wjt", "wjt", 1, 1, 1,20, new Date(ts + 10000),
                new Date(ts), "娱乐");
        int i = articleHomeMapper.insertSelective(articleHomeEntity);
        log.info("insert!i={};id={};", i, articleHomeEntity.id);

    }


}