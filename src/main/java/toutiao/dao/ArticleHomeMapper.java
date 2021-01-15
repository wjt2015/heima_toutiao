package toutiao.dao;

import org.springframework.stereotype.Repository;
import toutiao.model.ArticleHomeEntity;

@Repository
public interface ArticleHomeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleHomeEntity record);

    int insertSelective(ArticleHomeEntity record);

    ArticleHomeEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleHomeEntity record);

    int updateByPrimaryKey(ArticleHomeEntity record);
}