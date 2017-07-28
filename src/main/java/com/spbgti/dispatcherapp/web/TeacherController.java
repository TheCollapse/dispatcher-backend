package com.spbgti.dispatcherapp.web;

import com.spbgti.dispatcherapp.entity.Teacher;
import com.spbgti.dispatcherapp.service.impl.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService service;

    @RequestMapping(value = "/teacher",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(service.add(teacher), HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/teacher/{name}", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getByName(@PathVariable String name) {
        return new ResponseEntity<>(service.getByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> edit(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(service.edit(teacher), HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}