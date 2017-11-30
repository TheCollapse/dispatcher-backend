package com.spbgti.dispatcherapp.Entity.Event.Command;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.spbgti.dispatcherapp.Repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@JsonTypeName(value = "migrate")
public class MigrationCommand implements Command {

    @Autowired
    EntityRepository entityRepository;

    private String sqlForApply;
    private String sqlForRollBack;

    public MigrationCommand() {
    }

    public MigrationCommand(String sqlForApply, String sqlForRollBack) {
        this.sqlForApply = sqlForApply;
        this.sqlForRollBack = sqlForRollBack;
    }

    public EntityRepository getEntityRepository() {
        return entityRepository;
    }

    public void setEntityRepository(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public String getSqlForApply() {
        return sqlForApply;
    }

    public void setSqlForApply(String sqlForApply) {
        this.sqlForApply = sqlForApply;
    }

    public String getSqlForRollBack() {
        return sqlForRollBack;
    }

    public void setSqlForRollBack(String sqlForRollBack) {
        this.sqlForRollBack = sqlForRollBack;
    }

    @Override
    public Object apply() throws Exception {
        return entityRepository.migrate(this);
    }
}
