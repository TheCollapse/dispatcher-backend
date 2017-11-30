package com.spbgti.dispatcherapp.Entity.Event;

public class FailedEvent {
    Event event;
    String exception;

    public FailedEvent() {
    }

    public FailedEvent(Event event, String exception) {
        this.event = event;
        this.exception = exception;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
