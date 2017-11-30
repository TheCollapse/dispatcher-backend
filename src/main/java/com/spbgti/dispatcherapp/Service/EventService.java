package com.spbgti.dispatcherapp.Service;

import com.spbgti.dispatcherapp.Entity.Event.Command.Command;
import com.spbgti.dispatcherapp.Entity.Event.Command.Query;
import com.spbgti.dispatcherapp.Entity.Event.*;
import com.spbgti.dispatcherapp.Repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("Event Service")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EventService {

    @Autowired
    private MongoRepository mongoRepository;

    public Object addModifyEvent(Command[] commands) throws Exception {
        ModifyEvent modifyEvent = new ModifyEvent(new User(), new SessionInfo(), new Date().toString(), Arrays.asList(commands));
        List<Object> result = modifyEvent.apply();
        mongoRepository.addSuccessfulEvent(modifyEvent);
        return result;
    }

    public Object addReadEvent(Query[] queries) throws Exception {
        ReadEvent readEvent = new ReadEvent(new User(), new SessionInfo(), new Date().toString(), Arrays.asList(queries));
        List<Object> result = readEvent.apply();
        mongoRepository.addSuccessfulEvent(readEvent);
        return result;
    }

    public void addFailedEvent(Event event, Exception e) {
        FailedEvent failedModifyEvent = new FailedEvent(
                event,
                e.getClass().toString() + " " + e.getMessage());
        mongoRepository.addFailedEvent(failedModifyEvent);
    }
}
