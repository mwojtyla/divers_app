package com.crud.diver.scheduler;

import com.crud.diver.domain.Mail;
import com.crud.diver.domain.User;
import com.crud.diver.service.DiversLogDbService;
import com.crud.diver.service.SimpleEmailService;
import com.crud.diver.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private final SimpleEmailService simpleEmailService;
    private final UserDbService userDbService;
    private final DiversLogDbService diversLogDbService;
    private static final String SUBJECT = "Your daily diving statistics";

    @Scheduled(cron = "0 0 10 * * *")
    public void sendDailyStatisticsEmail() {

        List<User> users = userDbService.getAllUsers();

        for (User user : users) {
            long amountOfDives = diversLogDbService.getDiversLogsByUserId(user.getId()).stream()
                    .count();

            String diveWord = " dive";
            if (amountOfDives > 1) {
                diveWord = " dives";
            }
            simpleEmailService.send(
                    new Mail(
                            user.getEmail(),
                            SUBJECT,
                            "Currently You have: " + amountOfDives + diveWord + " in Your Diver's Log."
                    )
            );
        }
    }
}
