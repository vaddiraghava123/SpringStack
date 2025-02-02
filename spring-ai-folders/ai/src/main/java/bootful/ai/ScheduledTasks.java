package bootful.ai;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	
	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	@Scheduled(fixedRate = 1000)
	public void performTasks() {
		System.out.println("Scheduled PerformTasks -> "+ SIMPLE_DATE_FORMAT.format(new  Date()));
	}
	
	@Scheduled(initialDelay = 1000, fixedRate = 1000)
	public void perfromDelayTasks() {
		System.out.println("Scheduled PerformTasks -> delay"+ SIMPLE_DATE_FORMAT.format(new  Date()));
	}
	
	@Scheduled(cron =  "*/5 * * * * *")
	public void perfromTaskUsingCron() {
		System.out.println("Scheduled PerformTasks -> Cron"+ SIMPLE_DATE_FORMAT.format(new  Date()));
	}
	
	
}
