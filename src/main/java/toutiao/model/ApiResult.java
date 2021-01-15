package toutiao.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ApiResult {

    public int errCode;

    public String errMsg;

    public Object data;

}
