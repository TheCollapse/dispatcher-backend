package com.spbgti.dispatcherapp.Repository;

import com.spbgti.dispatcherapp.Entity.Event.ClassParser;
import com.spbgti.dispatcherapp.Entity.Event.Command.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Component(value = "Entity Repository")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityRepository() {

    }

    public Object create(CreateCommand createCommand) throws Exception {
        Object object = new ClassParser().parse((LinkedHashMap) createCommand.getEntity(), createCommand.getType());
        entityManager.persist(object);
        return object;
    }

    public Object update(UpdateCommand command) throws Exception {
        String sqlQuery = "UPDATE " + new ClassParser().firstCharToUpperCase(command.getType())
                + " SET " + command.getField()
                + " " + " = :fieldValue "
                + "WHERE id = :idValue";
        ClassParser classParser = new ClassParser();
        Object newObject = classParser.parse((LinkedHashMap) command.getNewEntity(), command.getType());
        Object field = classParser.getField((LinkedHashMap) command.getNewEntity(),
                command.getType(), command.getField());
        entityManager
                .createQuery(sqlQuery)
                .setParameter("fieldValue", field)
                .setParameter("idValue", (long) command.getEntityId())
                .executeUpdate();
        return newObject;
    }

    public Object delete(DeleteCommand deleteCommand) {
        String sqlQuery = "DELETE "
                + new ClassParser().firstCharToUpperCase(deleteCommand.getType())
                + " WHERE id = :idValue";
        entityManager.createQuery(sqlQuery).setParameter("idValue",
                (long) deleteCommand.getEntityId()).executeUpdate();
        return null;
    }

    public List executeQuery(QueryImpl executableQuery) throws Exception {
        String sqlString = "SELECT *" + " FROM " + new ClassParser().firstCharToUpperCase(executableQuery.getType());
        Set set = ((LinkedHashMap) executableQuery.getParams()).entrySet();
        if (!set.isEmpty()) {
            sqlString += " WHERE ";
        }
        for(Object item : set){
            Map.Entry me = (Map.Entry) item;
            sqlString += me.getKey().toString() + " = :" + me.getKey().toString() + "Value AND ";
        }
        sqlString = sqlString.substring(0, sqlString.length() - 4); //:c
        javax.persistence.Query query = entityManager.createNativeQuery(sqlString,
                new ClassParser().getClassFor(executableQuery.getType()));
        for(Object item : set){
            Map.Entry me = (Map.Entry) item;
            query.setParameter(me.getKey().toString() + "Value", me.getValue());
        }
        return query.getResultList();
    }

    public Object migrate(MigrationCommand command) {
        return entityManager.createNativeQuery(command.getSqlForApply()).executeUpdate();
    }
}
