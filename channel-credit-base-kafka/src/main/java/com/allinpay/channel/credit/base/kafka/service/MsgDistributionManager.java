package com.allinpay.channel.credit.base.kafka.service;

import org.springframework.stereotype.Component;


@Component
public interface MsgDistributionManager {

	public void messageHandler(String key,String value);
	
}
