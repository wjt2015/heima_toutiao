package toutiao.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import toutiao.dao.ArticleHomeMapper;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = {ArticleHomeMapper.class})
public class DaoConfig {
}
