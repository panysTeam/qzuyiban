package com.qzuyiban.pan.system.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
@Component
public class SchedulerAllJob {

	 @Autowired
	  private SchedulerFactoryBean schedulerFactoryBean;    
	  /* 
	   * 此处可以注入数据库操作，查询出所有的任务配置 
	   */    
	  /** 
	   * 该方法用来启动所有的定时任务 
	   * @throws SchedulerException 
	   */ 
	  public void scheduleJobs() throws SchedulerException { 
	    Scheduler scheduler = schedulerFactoryBean.getScheduler();  	   
	    /** 
	     *  定时任务
	     */ 
	    scheduleJob1(scheduler);  
	    scheduleJob2(scheduler);  
	  } 
	    
	  /** 
	   * 配置Job1 
	   * 此处的任务可以配置可以放到properties或者是放到数据库中 
	   * 如果此时需要做到动态的定时任务，请参考：http://blog.csdn.net/liuchuanhong1/article/details/60873295 
	   * 博客中的ScheduleRefreshDatabase类 
	   * @param scheduler 
	   * @throws SchedulerException 
	   */ 
	  private void scheduleJob1(Scheduler scheduler) throws SchedulerException{ 
	    /* 
	     * 此处可以先通过任务名查询数据库，如果数据库中存在该任务，则按照ScheduleRefreshDatabase类中的方法，更新任务的配置以及触发器 
	     * 如果此时数据库中没有查询到该任务，则按照下面的步骤新建一个任务，并配置初始化的参数，并将配置存到数据库中 
	     */ 
	    JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity("job", "grou1").build();  
	    // 每5s执行一次 
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");  
	    CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger", "grou11") .withSchedule(scheduleBuilder).build();  
	    scheduler.scheduleJob(jobDetail,cronTrigger);  
	  }    
	  /** 
	   * 配置Job2 
	   * @param scheduler 
	   * @throws SchedulerException 
	   */
	  private void scheduleJob2(Scheduler scheduler) throws SchedulerException{  
	    JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class) .withIdentity("job2", "grou2").build(); 
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");  
	    // 每10s执行一次 
	    CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "grou22") .withSchedule(scheduleBuilder).build();  
	    scheduler.scheduleJob(jobDetail,cronTrigger); 
	  }
}
