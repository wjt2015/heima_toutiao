package toutiao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toutiao.model.ApiResult;

@Slf4j
@RestController
public class HomeController {

    @RequestMapping(value = "/home.json")
    public ApiResult home() {
        return new ApiResult(0, "ok", "home_success");
    }

    @RequestMapping(value = "/query.json")
    public ApiResult query() {
        return new ApiResult(0, "ok", "query_success");
    }

}
