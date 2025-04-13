package test;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class SchedulerBean {

    @Schedule(second = "2", minute = "*", hour = "*", persistent = false)
    public void performScheduledTask() {
        System.out.println("Scheduled Task wird ausgeführt...");
    }
}