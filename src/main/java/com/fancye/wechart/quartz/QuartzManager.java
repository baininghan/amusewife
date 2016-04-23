package com.fancye.wechart.quartz;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
 
/** 
* 定时任务管理类 
* 
* @author Fancye
*/  
public class QuartzManager {
	
   private static StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
   private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";  
   private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";  
 
   /** 
    * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
    * 
    * @param jobName 
    *            任务名 
    * @param jobClass 
    *            任务 
    * @param cronExpression 
    *            时间设置，参考quartz说明文档 
    * @throws SchedulerException 
    * @throws ParseException 
    */  
   public static void addJob(String jobName, Class <? extends Job> jobClass, String cronExpression) {
       try {
    	   Scheduler sched = stdSchedulerFactory.getScheduler();
           JobDetail jobDetail = JobBuilder
        		   							.newJob(jobClass)
        		   							.withIdentity(jobName, JOB_GROUP_NAME)
        		   							.build();
           
           // 触发器
//           Trigger trigger = TriggerBuilder
//        		   						.newTrigger()
//        		   						.withIdentity("trigger_1", "group_1")
//        		   						.startNow()
//        		   						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
//        		   								.withIntervalInSeconds(10)/* 10秒執行一次 */
//        		   								.withRepeatCount(5))/* 重複執行5次 */
//        		   						.build();
           CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
           Trigger trigger = TriggerBuilder
						.newTrigger()
						.withIdentity("trigger_1", JOB_GROUP_NAME)
						.startNow()
						.withSchedule(scheduleBuilder)/* 指定表達式 */
						.build();
           sched.scheduleJob(jobDetail, trigger);  
           // 启动  
           if (!sched.isShutdown()){
               sched.start();  
           }  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
 
   /** 
    * 添加一个定时任务 
    * 
    * @param jobName 
    *            任务名 
    * @param jobGroupName 
    *            任务组名 
    * @param triggerName 
    *            触发器名 
    * @param triggerGroupName 
    *            触发器组名 
    * @param jobClass 
    *            任务 
    * @param time 
    *            时间设置，参考quartz说明文档 
    * @throws SchedulerException 
    * @throws ParseException 
    */  
   public static void addJob(String jobName, String jobGroupName,
           String triggerName, String triggerGroupName, Class <? extends Job> jobClass, String time){  
       try {
    	   Scheduler sched = stdSchedulerFactory.getScheduler();
           JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
           // 触发器
           Trigger trigger = TriggerBuilder
        		   						.newTrigger()
        		   						.withIdentity(triggerName, triggerGroupName)
        		   						.startNow()
        		   						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
        		   								.withIntervalInSeconds(10)
        		   								.withRepeatCount(5))
        		   						.build();
           sched.scheduleJob(jobDetail, trigger);  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
 
   /** 
    * 修改一个任务的触发时间 
    * 
    * @param triggerName 
    * @param triggerGroupName 
    * @param time 
    */  
   public static void modifyJobTime(String triggerName,  
           String triggerGroupName, String time) {  
       try {
    	   Scheduler sched = stdSchedulerFactory.getScheduler();
    	   TriggerKey triggerKey = new TriggerKey(TRIGGER_GROUP_NAME);
    	   Trigger trigger = sched.getTrigger(triggerKey);
           if(trigger == null) {  
               return;  
           }  
           String calendar = trigger.getCalendarName();
           if (!calendar.equalsIgnoreCase(time)) {
//               CronExpression cronExpression = new CronExpression(time);
//               CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
//               trigger.getTriggerBuilder()
//    						.withIdentity(triggerName, triggerGroupName)
//    						.withSchedule(scheduleBuilder)/* 指定表達式 */
//    						.build();
               
               
               // 修改时间  
//               ct.setCronExpression(time);
               // 重启触发器  
               sched.resumeTrigger(new TriggerKey(triggerName));
           }  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
   

   /**
    * 更新任务定时器
    * 
    * @param jobName
    * @param jobGroup
    * @param cronExp
    */
   public static void updateJob(String jobName, String jobGroup, String cronExp) {
	   try {
		   Scheduler scheduler = stdSchedulerFactory.getScheduler();
		   TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		   
		   //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		   CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		   
		   //表达式调度构建器
		   CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
		     
		   //按新的cronExpression表达式重新构建trigger
		   trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
		   .withSchedule(scheduleBuilder).build();
		     
		   //按新的trigger重新设置job执行
		   scheduler.rescheduleJob(triggerKey, trigger);
	} catch (SchedulerException e) {
		e.printStackTrace();
	}
   }
   /** 
    * 移除任务 
    * 
    * @param jobName 
    * @param jobGroup 
    */  
   public static void removeJob(String jobName, String jobGroup) {
       try {
           Scheduler scheduler = stdSchedulerFactory.getScheduler();
           JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
           scheduler.deleteJob(jobKey);
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
 
   /** 
    * 启动所有定时任务 
    */  
   public static void startJobs() {  
       try {
    	   Scheduler sched = stdSchedulerFactory.getScheduler();
           sched.start();  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
 
   /** 
    * 关闭所有定时任务 
    */  
   public static void shutdownJobs() {
       try {
    	   Scheduler sched = stdSchedulerFactory.getScheduler();
           if(!sched.isShutdown()) {  
               sched.shutdown(true);
           }  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }
   
   /**
    * 暂停任务
    * 
    * @param jobName
    * @param jobGroup
    */
	public static void pauseJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = stdSchedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
   }
	
	/**
    * 恢复任务
    * 
    * @param jobName
    * @param jobGroup
    */
	public static void resumeJob(String jobName, String jobGroup) {
		try {
			Scheduler scheduler = stdSchedulerFactory.getScheduler();
			JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
   }
	
   public static void main(String[] args) {
       SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
       Date d = new Date();  
       String returnstr = DateFormat.format(d);          
         
       String job_name ="11";  
       try {  
           System.out.println(returnstr+ "【系统启动】");  
           QuartzManager.addJob(job_name, WechartJob.class,"0/2 * * * * ?"); //每2秒钟执行一次  
             
//           Thread.sleep(10000);  
//           System.out.println("【修改时间】");  
//           QuartzManager.modifyJobTime(job_name,"0/10 * * * * ?");  
//           Thread.sleep(20000);  
//           System.out.println("【移除定时】");  
//           QuartzManager.removeJob(job_name);  
//           Thread.sleep(10000);  
//             
//           System.out.println("/n【添加定时任务】");  
//           QuartzManager.addJob(job_name,job,"0/5 * * * * ?");  
             
       }  catch (Exception e) {  
           e.printStackTrace();  
       }  
   }  
} 
