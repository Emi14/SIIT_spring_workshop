package org.siit.springworkshop.helper;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StudentInfoContributor implements InfoContributor {

    int numberOfDataNotFoundExceptions;
    int numberOfSentEmails;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Integer> studentStats = new HashMap<>();
        studentStats.put("data-not-found-exceptions", numberOfDataNotFoundExceptions);
        studentStats.put("sent-emails", numberOfSentEmails);

        builder.withDetail("students", studentStats);
    }

    public void incrementNumberOfDataNotFoundExceptions()
    {
        this.numberOfDataNotFoundExceptions++;
    }

    public void incrementNumberOfSentEmails()
    {
        this.numberOfSentEmails++;
    }
}
