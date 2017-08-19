package com.spbgti.dispatcherapp.Entity.Event;

import org.hibernate.SQLQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

public class UpdateCommand implements Command {
    private String type;
    private int entityId;
    private String field;
    private Object oldEntity;
    private Object newEntity;

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
    public Object apply(EntityManager entityManager) throws
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            InstantiationException,
            IllegalAccessException {
            String sqlQuery = "UPDATE " + new LinkedHashMapParser().firstCharToUpperCase(this.type) + " SET " + this.field + " " + " = :fieldValue " +
                    "WHERE id = :idValue";
        entityManager
                .createQuery(sqlQuery)
                .setParameter("fieldValue", new LinkedHashMapParser().getField((LinkedHashMap)this.newEntity, this.type, this.field))
                .setParameter("idValue", (long)this.entityId)
                .executeUpdate();
        return "2";
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