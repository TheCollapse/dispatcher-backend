package com.spbgti.dispatcherapp.Repository;

import com.spbgti.dispatcherapp.Entity.Event.Event;
import com.spbgti.dispatcherapp.Entity.Event.FailedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

@Component
public class MongoRepository {

    @Autowired
    MongoOperations mongoOperations;

    public void addSuccessfulEvent(Event event) {
        mongoOperations.insert(event, "successful" + event.getClass().getSimpleName());
    }

    public void addFailedEvent(FailedEvent event) {
        mongoOperations.insert(event);
    }
}
