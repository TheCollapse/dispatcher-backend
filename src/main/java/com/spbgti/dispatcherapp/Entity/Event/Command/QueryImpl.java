package com.spbgti.dispatcherapp.Entity.Event.Command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spbgti.dispatcherapp.Repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@JsonTypeName(value = "query")
public class QueryImpl implements Query{
    private String type;
    private boolean oneOrMany;
    private Object params;

    @Autowired
    EntityRepository entityRepository;

    public QueryImpl() {
    }

    public QueryImpl(String type, boolean oneOrMany, Object params) {
        this.type = type;
        this.oneOrMany = oneOrMany;
        this.params = params;
    }

    public List execute() throws Exception {
        return entityRepository.executeQuery(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOneOrMany() {
        return oneOrMany;
    }

    public void setOneOrMany(boolean oneOrMany) {
        this.oneOrMany = oneOrMany;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
