package com.spbgti.dispatcherapp.Entity.Event;

import com.spbgti.dispatcherapp.Entity.Event.Command.Command;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "ModifyEvent")
public class ModifyEvent extends Event {
    private List<Command> commands;


    public ModifyEvent() {
    }

    public ModifyEvent(User user, SessionInfo session, String date, List<Command> commands) {
        super(user, session, date);
        this.commands = commands;
    }

    @Override
    public List<Object> apply() throws Exception {
        List<Object> list = new ArrayList<>();
        for (Command command : commands) {
            list.add(command.apply());
        }
        return list;
    }
}
