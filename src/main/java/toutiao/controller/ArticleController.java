package toutiao.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sonatype.aether.resolution.ArtifactRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toutiao.model.ApiResult;
import toutiao.model.ArticleRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户进入app,需要加载文章(load);用户上拉(load_more)、下拉(load_new),也会导致加载;
 * load,
 * 已登录,根据用户信息返回文章;
 * 未登录,直接加载默认文章;
 * <p>
 * 用户在时刻timeA进入系统,浏览了一会儿现在要上/下拉文章了,此时是时刻timeB;
 * load_more,返回timB之前的文章;
 * load_new,返回timeA之后的文章;
 * <p>
 * behavior,记录用户行为;
 * <p>
 * <p>
 * [
 * <p>
 * curl -H 'content-type:application/json;charset=utf8' --data '{"cityId":1,"countyId":1,"provinceId":1,"tag":"财经","ts":1610704821318}' 'http://localhost:8055/toutiao/load.json'
 * <p>
 * ]
 */
@Slf4j
@RestController
public class ArticleController {


    @RequestMapping(value = {"/load.json"})
    public ApiResult load(@RequestBody ArticleRequest articleRequest) {
        log.info("articleRequest={};", articleRequest);

        return new ApiResult();
    }


    @RequestMapping(value = {"/load_more.json"})
    public ApiResult loadMore(@RequestBody ArticleRequest articleRequest) {

        return new ApiResult();
    }

    @RequestMapping(value = {"/load_new.json"})
    public ApiResult loadNew(@RequestBody ArtifactRequest artifactRequest) {

        return new ApiResult();
    }

    @RequestMapping(value = "/load_test.json")
    public ApiResult loadTest() {
        return new ApiResult(0, "ok", loadArticles());
    }

    /**
     * 以下为测试代码;
     */
    @AllArgsConstructor
    static class ArticleVM {
        public long id;
        public String title;
        public String url;
        public String summary;
        public String author;

    }

    private List<ArticleVM> loadArticles() {

        List<ArticleVM> articleVMList = new ArrayList<>();
        articleVMList.add(new ArticleVM(1, "1万属性，100亿数据，每秒10万吞吐，架构如何设计？",
                "https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651962219&idx=1&sn=30545c7a9f46fa74a61cc09323a6a8c9&chksm=bd2d0eb78a5a87a1c16b1d10fbb688adb2848345b70fa2fbc161b3a566c7c3e02adaccd5981e&scene=21#wechat_redirect",
                "大数据架构设计", "58沈剑"));
        articleVMList.add(new ArticleVM(2, "司马懿这五句话，听懂两句都够用一生了",
                "https://mp.weixin.qq.com/s?__biz=MzI4MzU3NzY1MQ==&mid=2247504740&idx=2&sn=0fe0a767caa0269fa72274f425855c60&chksm=eb8a0f38dcfd862e188f794ed4898262c2afd5ce2f1d0acebbc790e1cd55115e44c3873bb212&scene=132#wechat_redirect",
                "智慧", "国学文化之粹"));
        articleVMList.add(new ArticleVM(3, "IM 架构设计03 读扩散 && 写扩散",
                "https://blog.csdn.net/kuaipao19950507/article/details/107673744",
                "消息系统,读扩散,写扩散", "kuaipao19950507"));
        articleVMList.add(new ArticleVM(4, "炸！亿级数据DB秒级平滑扩容！！！",
                "https://blog.csdn.net/z50L2O08e2u4afToR9A/article/details/89839471",
                "亿级数据,秒级", "架构师之路_"));
        articleVMList.add(new ArticleVM(5, "系统通知，居然有人使用拉取？",
                "https://mp.weixin.qq.com/s?__biz=MjM5ODYxMDA5OQ==&mid=2651961154&idx=1&sn=277f6ec612555bf5a95585e9a161bb5f&chksm=bd2d029e8a5a8b884c9855b8e315a697a0e8eccf227fb36395334d140dd9eebf2489e99862d3&scene=21#wechat_redirect",
                "系统通知,拉,推", "58沈剑 架构师之路"));

        return articleVMList;
    }

    @RequestMapping(value = "/articleTitle.json")
    public ApiResult articleTitle(@RequestParam("uid") long uid, @RequestParam("articleId") long articleId) {
        String articleTitle = new StringBuilder()
                .append(uid).append("_").append(articleId)
                .toString();
        return new ApiResult(0, "ok", articleTitle);
    }

}
