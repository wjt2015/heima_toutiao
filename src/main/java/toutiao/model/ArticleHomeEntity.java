package toutiao.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class ArticleHomeEntity {
    public Long id;

    public Date createTime;

    public Date updateTime;

    public String createUser;

    public String updateUser;

    public Integer provinceId;

    public Integer cityId;

    public Integer countyId;

    public Date maxBehotTime;

    public Date minBehotTime;

    public String tag;

    
}
