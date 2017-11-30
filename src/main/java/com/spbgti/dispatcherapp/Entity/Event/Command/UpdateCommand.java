package com.spbgti.dispatcherapp.Entity.Event.Command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spbgti.dispatcherapp.Repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;


@JsonTypeName(value = "update")
public class UpdateCommand implements Command {
    private String type;
    private int entityId;
    private String field;
    private Object oldEntity;
    private Object newEntity;

    @Autowired
    private EntityRepository repository;

    public UpdateCommand() {
    }

    public UpdateCommand(String type, int entityId, String field, Object oldEntity, Object newEntity) {
        this.type = type;
        this.entityId = entityId;
        this.field = field;
        this.oldEntity = oldEntity;
        this.newEntity = newEntity;
    }

    @Override
    public Object apply() throws Exception {
        return repository.update(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getOldEntity() {
        return oldEntity;
    }

    public void setOldEntity(Object oldEntity) {
        this.oldEntity = oldEntity;
    }

    public Object getNewEntity() {
        return newEntity;
    }

    public void setNewEntity(Object newEntity) {
        this.newEntity = newEntity;
    }
}
