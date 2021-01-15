package toutiao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AriticleRequest {

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
