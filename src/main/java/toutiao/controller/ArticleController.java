package toutiao.controller;

import lombok.extern.slf4j.Slf4j;
import org.sonatype.aether.resolution.ArtifactRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toutiao.model.ApiResult;
import toutiao.model.AriticleRequest;

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
 *
 *
 * [
 *
 * curl -H 'content-type:application/json;charset=utf8' --data '{"cityId":1,"countyId":1,"provinceId":1,"tag":"财经","ts":1610704821318}' 'http://localhost:8055/toutiao/load.json'
 *
 * ]
 */
@Slf4j
@RestController
public class ArticleController {


    @RequestMapping(value = {"/load.json"})
    public ApiResult load(@RequestBody AriticleRequest ariticleRequest) {
        log.info("ariticleRequest={};",ariticleRequest);

        return new ApiResult();
    }


    @RequestMapping(value = {"/load_more.json"})
    public ApiResult loadMore(@RequestBody AriticleRequest ariticleRequest) {

        return new ApiResult();
    }

    @RequestMapping(value = {"/load_new.json"})
    public ApiResult loadNew(@RequestBody ArtifactRequest artifactRequest) {

        return new ApiResult();
    }

}
