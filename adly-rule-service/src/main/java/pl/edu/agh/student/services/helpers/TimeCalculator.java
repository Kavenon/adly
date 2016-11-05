package pl.edu.agh.student.services.helpers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class TimeCalculator {

    public Date dateFromNow(Integer timeValue, ChronoUnit timeUnit){
        long millis = millisFromNow(timeValue, timeUnit);

        return new Date(millis);
    }

    public long millisFromNow(Integer timeValue, ChronoUnit timeUnit) {
        return LocalDateTime
                    .now()
                    .minus(timeValue, timeUnit)
                    .toEpochSecond(ZoneOffset.UTC) * 1000;
    }
}
