package com.spbgti.dispatcherapp.Entity.Event.Command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({@JsonSubTypes.Type(value = CreateCommand.class, name = "create"),
        @JsonSubTypes.Type(value = DeleteCommand.class, name = "delete"),
        @JsonSubTypes.Type(value = UpdateCommand.class, name = "update"),
        @JsonSubTypes.Type(value = MigrationCommand.class, name = "migrate")})
public interface Command {
    public Object apply() throws Exception;
}
