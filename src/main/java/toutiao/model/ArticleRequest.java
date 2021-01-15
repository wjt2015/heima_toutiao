package toutiao.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleRequest {

    public Integer provinceId;

    public Integer cityId;

    public Integer countyId;

    public String tag;


    /**
     * load,忽略;
     * load_more,相当于timeB;
     * load_new,相当于timeA;
     */
    public long ts;
}
