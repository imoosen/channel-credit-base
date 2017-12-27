package com.allinpay.channel.credit.base.common.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.Validate.matchesPattern;

/**
 * Created by mengsen on 2017/8/17.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: mengsen on 2017/8/17.
 */
public class CheckUtils {
    /**
     * 匹配18位身份证
     */
    public static final String IDCARD_REGEX18 = "^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X)?$";

    /**
     * 匹配15位身份证
     */
    public static final String IDCARD_REGEX15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 姓名正则表达式,支持少数名族 汉子带圆点
     */
    public static final String NAME_REGEX = "[\\u4e00-\\u9fa5]{2,}(·[\\u4e00-\\u9fa5]+)*";

    public static final String PHONE_REGEX="^[1][34578][0-9]{9}$";

    /**
     * 姓名验证
     */
    public static void checkName(String name){
        checkArgument(!Strings.isNullOrEmpty(name), "申请信息[name]必填");
        matchesPattern(name, NAME_REGEX, "申请信息姓名[%s]格式错误", name);
    }
    /**
     * 身份证验证
     */
    public static void checkIccard(String idCard){
        checkArgument(!Strings.isNullOrEmpty(idCard), "申请信息[identity_card_number]必填");
        if (idCard.length() == 18) {
            matchesPattern(idCard, IDCARD_REGEX18, "身份证号码[%s]格式错误", idCard);
        } else {
            matchesPattern(idCard, IDCARD_REGEX15, "身份证号码[%s]格式错误", idCard);
        }
    }

    /**
     * 手机号码验证
     */
    public static void checkPhone(String phone) {
        checkArgument(!Strings.isNullOrEmpty(phone), "申请信息[cell_phone_number]必填",phone);
        checkArgument(phone.length() == 11, "手机号码[%s]格式错误", phone);
        checkArgument(CharMatcher.DIGIT.matchesAllOf(phone), "手机号码[%s]格式错误", phone);
        matchesPattern(phone, PHONE_REGEX, "手机号码[%s]格式错误", phone);
    }

/*
    校验过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
    /**
     * 校验银行卡卡号
     */
    public static void checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            throw new IllegalArgumentException("银行卡格式不正确");
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            throw new IllegalArgumentException("银行卡格式不正确");
        }
        if(!(bankCard.charAt(bankCard.length() - 1) == bit)){
            throw new IllegalArgumentException("银行卡格式不正确");
        }
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

}
