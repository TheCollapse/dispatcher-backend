package com.spbgti.dispatcherapp.Web;

import com.spbgti.dispatcherapp.Entity.Event.Command.Command;
import com.spbgti.dispatcherapp.Entity.Event.Command.Query;
import com.spbgti.dispatcherapp.Entity.Event.ModifyEvent;
import com.spbgti.dispatcherapp.Entity.Event.ReadEvent;
import com.spbgti.dispatcherapp.Entity.Event.SessionInfo;
import com.spbgti.dispatcherapp.Entity.Event.User;
import com.spbgti.dispatcherapp.Service.EventService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping(value = "/event")
public class EventController {
    private static final Logger logger = LogManager.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @RequestMapping(value = "/append",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createModifyEvent(@RequestBody Command[] commands) {
        try {
            Arrays.stream(commands).forEach(c -> beanFactory.autowireBean(c));
            return new ResponseEntity<>(eventService.addModifyEvent(commands), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            eventService.addFailedEvent(
                    new ModifyEvent(
                            new User(),
                            new SessionInfo(),
                            new Date().toString(),
                            Arrays.asList(commands)),
                    e);
            return new ResponseEntity<>(e.getCause() + " " + e.getMessage(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/retrieve",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addReadEvent(@RequestBody Query[] queries) {
        try {
            Arrays.stream(queries).forEach(c -> beanFactory.autowireBean(c));
            return new ResponseEntity<>(eventService.addReadEvent(queries), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            eventService.addFailedEvent(
                    new ReadEvent(
                            new User(),
                            new SessionInfo(),
                            new Date().toString(),
                            Arrays.asList(queries)),
                    e);
            return new ResponseEntity<>(e.getCause() + " " + e.getMessage(), HttpStatus.OK);
        }
    }
}
