package com.spbgti.dispatcherapp.web;

import com.spbgti.dispatcherapp.entity.Student;
import com.spbgti.dispatcherapp.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Gilbord on 29.07.2017.
 */
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/student",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.add(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        studentService.delete(id);
    }

    @RequestMapping(value = "/student/{number}", method = RequestMethod.GET)
    public ResponseEntity<Student> getByNumber(@PathVariable String num) {
        return new ResponseEntity<>(studentService.getByName(num), HttpStatus.OK);
    }

    @RequestMapping(value = "/student",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> edit(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.edit(student), HttpStatus.OK);
    }

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }
}