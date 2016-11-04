package pl.edu.agh.student.services;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;

@Component
public class EventHandler {


    public void handleEvent(UserEvent userEvent){

        //@todo:
        /*
         *   1. wyciagnac z bazy by eventType
         *   2. przegladnac petla elementy i sprawdzic czy parametry z bazy odpowiadaja parametrom eventu usera
         *   ( zamknac to w jakiejs osobnej klasie)
         *   3. Dla kazdego warunku zrobić evaluate (osobne komponenty w zaleznosci od typu warunku)
         *   4. Dla każdej akcji wykonać execute ( np. SendPush executor rzuci na kolejke push)
         *   5. done (+sprawdzic, czy push payload jest dostepny z zewnatrz, a pewnie nie jest bo nie zapisuje nigdzie pusha na bazie)
         */
    }

}
