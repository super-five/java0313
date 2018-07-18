package day004_Quartz_001;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyTrigger {
	public static void main(String[] args) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(MyJop.class)
			.withIdentity("作业1", "小组 1")
			.build();
		
//		Date date= new Date(System.currentTimeMillis());
		
		
//		SimpleTrigger trigger = TriggerBuilder.newTrigger()
//			.withIdentity("触发器1", "小组1")
//			.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//					.withIntervalInSeconds(1)
//					.withRepeatCount(100))
//			.startAt(date)
//			.build();
		
		CronTrigger trigger = TriggerBuilder.newTrigger()
			.withIdentity("trigger1", "group1")
			.withSchedule(CronScheduleBuilder.cronSchedule("0 0 10,12,17 * * ？"))
			.build();
		
		
		
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
		
		scheduler.start();
		
		try {
			Thread.sleep(500000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		scheduler.shutdown();
		
	}
	
}
