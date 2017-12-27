package com.allinpay.channel.credit.base.database.service;



import com.alibaba.druid.util.HttpClientUtils;
import com.allinpay.channel.credit.base.database.mapper.TChanlqyInfoMapper;
import com.allinpay.channel.credit.base.database.model.TChanlqyInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * Created by mengsen on 2017/11/20.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: mengsen on 2017/11/20.
 */
@Slf4j
@Service
public class TChanlqyInfoService {
    @Autowired
    private TChanlqyInfoMapper tChanlqyInfoMapper;

    @Async
    public void insertLogMessage(TChanlqyInfo chanlInfo){

        try {
            tChanlqyInfoMapper.insert(chanlInfo);
            log.info("渠道查询流水入库成功");
        }catch (Exception e){
            log.error("渠道查询流水入库失败",e);
        }

    }
}
