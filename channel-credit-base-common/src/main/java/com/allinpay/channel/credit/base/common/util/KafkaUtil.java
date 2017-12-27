package com.allinpay.channel.credit.base.common.util;



import com.alibaba.fastjson.JSONObject;
import com.allinpay.channel.credit.base.common.constant.BaseConstant;
import com.allinpay.channel.credit.base.common.constant.BaseErrorCode;
import com.allinpay.channel.credit.base.common.dto.RedisResultDto;
import com.allinpay.channel.credit.base.database.model.TChanlqyInfo;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mengsen on 2017/11/20.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: mengsen on 2017/11/20.
 */

public class KafkaUtil {

    public static Map<String,Object> parseMessage(String value)throws Exception{
        JSONObject returnJson = new JSONObject();
        RedisResultDto resultDto = new RedisResultDto();
        //解析消息
        JSONObject jsonResult = JSONObject.parseObject(value);
        String interfaceId = jsonResult.getString(BaseConstant.QUERY_PARAMS_INTERFACEID);//接口ID
        String channelId = jsonResult.getString(BaseConstant.QUERY_PARAMS_CHANNELID);//渠道ID
        String interfaceName = jsonResult.getString(BaseConstant.QUERY_PARAMS_INTERFACENAME);//接口名
        String memberId = jsonResult.getString(BaseConstant.QUERY_PARAMS_MEMBERID);//会员ID
        String merId = jsonResult.getString(BaseConstant.QUERY_PARAMS_MERID);//商户ID
        String redisKey = jsonResult.getString(BaseConstant.QUERY_PARAMS_REDISKEY);//redisKey
        String qyBatchId = jsonResult.getString(BaseConstant.QUERY_PARAMS_SERALNO);//查询批次
        String funcId = jsonResult.getString(BaseConstant.QUERY_PARAMS_FUNCID);
        String locKey = jsonResult.getString(BaseConstant.QUERY_PARAMS_LOCKEY);
        Object param = jsonResult.get(BaseConstant.QUERY_PARAMS_PARAM);
        String timestamp = DateUtil.formatDateTime(new Date());
        //从消息中取出需要回传给功能的信息
        returnJson.put(BaseConstant.QUERY_PARAMS_LOCKEY,locKey);
        returnJson.put(BaseConstant.QUERY_PARAMS_REDISKEY,redisKey);
        returnJson.put("timestamp",timestamp);
        //组装回传消息
        resultDto.setChannelId(channelId);
        resultDto.setInterfaceId(interfaceId);
        resultDto.setFuncId(funcId);
        resultDto.setSeralNo(qyBatchId);
        resultDto.setInsertTime(timestamp);
        resultDto.setParam(param);
        //组装查询入库流水
        TChanlqyInfo chanlInfo = new TChanlqyInfo();
        chanlInfo.setQyBatchid(qyBatchId);
        chanlInfo.setMerId(merId);
        chanlInfo.setMemberId(memberId);
        chanlInfo.setIntfcId(interfaceId);
        chanlInfo.setIntfcName(interfaceName);
        chanlInfo.setRediskey(redisKey);
        chanlInfo.setInsertime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("param",param);
        map.put("chanlInfo",chanlInfo);
        map.put("returnJson",returnJson);
        map.put("redisDto",resultDto);
        return map;
    }

    public static String getStatus(String code){
        return BaseErrorCode.SUCCESS_CODE_0000.equals(code)? BaseConstant.QUERY_STATUS_SUCCESS: BaseConstant.QUERY_STATUS_FAIL;
    }
}
