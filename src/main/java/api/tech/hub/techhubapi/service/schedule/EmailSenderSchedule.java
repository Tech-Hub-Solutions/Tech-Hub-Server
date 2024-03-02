package api.tech.hub.techhubapi.service.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderSchedule {
    @Scheduled(cron = "0 0 0 * * 5")
    public void enviarEmails () {

    }
}
