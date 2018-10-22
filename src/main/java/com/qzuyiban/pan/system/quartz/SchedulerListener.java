package com.qzuyiban.pan.system.quartz;


import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
@Component
public class SchedulerListener implements ApplicationRunner{// ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	public SchedulerAllJob myScheduler;

	/**
	 * 启动的时候执行该方法，或者是使用ApplicationListener，在启动的时候执行该方法
	 * 具体使用见：http://blog.csdn.net/liuchuanhong1/article/details/77568187
	 * 
	 * @throws SchedulerException
	 */	
	//@Scheduled(cron = "0 39 16  * * ?")	
	public void schedule(){// throws SchedulerException {
		try {
			myScheduler.scheduleJobs();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 多任务时的触发器
	 * @return
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setStartupDelay(5);//延时五秒执行
		return schedulerFactoryBean;
	}

	//自动执行方法
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
//		Thread.sleep(5000);//睡眠五秒
//		schedule();
		System.out.println("没有开启定时任务");
	}

	

}
