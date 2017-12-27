package com.allinpay.channel.credit.base.common.dto;

import lombok.Data;

/**
 * Created by mengsen on 2017/9/20.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: mengsen on 2017/9/20.
 */
@Data
public class RedisResultDto {
    private String verify;
    private String seralNo;
    private String verifyMsg;

    private String interfaceId;

    private String channelId;

    private String funcId;

    private String insertTime;

    private Object param;

    private Object data;

}
