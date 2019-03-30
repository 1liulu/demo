package com.example.demo1.util;
import com.example.demo1.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    TokenService tokenService;

    /**
     * 每小时更新一次
     */
   /* @Scheduled(cron = "0 0 * * * ?")*/
    public void reportCurrentTime() {
        //tokenService.update();
        System.out.println("刷新token：" + dateFormat.format(new Date()));
    }
}
