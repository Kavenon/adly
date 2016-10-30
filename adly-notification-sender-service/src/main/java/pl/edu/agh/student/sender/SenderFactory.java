package pl.edu.agh.student.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.System;

@Component
public class SenderFactory {

    @Autowired
    private Sender fcmSender;

    public Sender getSender(System system){
        switch(system){
            case ANDROID:
                return fcmSender;
            default:
                throw new IllegalArgumentException("System not supported");
        }
    }
}
