package com.spbgti.dispatcherapp.Service.impl;

import com.spbgti.dispatcherapp.Entity.Chair;
import com.spbgti.dispatcherapp.Repository.ChairRepository;
import com.spbgti.dispatcherapp.Service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService implements ServiceInterface<Chair> {

    @Autowired
    private ChairRepository chairs;

    @Override
    public Chair add(Chair c) {
        return chairs.saveAndFlush(c);
    }

    @Override
    public void delete(long id) {
        chairs.delete(id);
    }

    @Override
    public Chair getByName(String name) {
        return chairs.findByName(name);
    }

    @Override
    public Chair edit(Chair c) {
       return chairs.saveAndFlush(c);
    }

    @Override
    public List<Chair> getAll() {
        return chairs.findAll();
    }
}