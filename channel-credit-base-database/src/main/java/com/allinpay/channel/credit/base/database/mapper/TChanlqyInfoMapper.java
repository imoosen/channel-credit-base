package com.allinpay.channel.credit.base.database.mapper;


import com.allinpay.channel.credit.base.database.model.TChanlqyInfo;
import com.allinpay.channel.credit.base.database.model.TChanlqyInfoKey;

public interface TChanlqyInfoMapper {
    int deleteByPrimaryKey(TChanlqyInfoKey key);

    int insert(TChanlqyInfo record);

    int insertSelective(TChanlqyInfo record);

    TChanlqyInfo selectByPrimaryKey(TChanlqyInfoKey key);

    int updateByPrimaryKeySelective(TChanlqyInfo record);

    int updateByPrimaryKey(TChanlqyInfo record);
}