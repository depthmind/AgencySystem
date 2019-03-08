/**
 * 
 */
package com.agency.crm.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zyy
 *
 */
@Component
@EnableScheduling
public class HandleFormId {

	private final Logger logger = LoggerFactory.getLogger(HandleFormId.class);
	
	@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
		logger.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }
}
