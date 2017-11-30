package com.spbgti.dispatcherapp.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "teacher")
public class Teacher extends Person {

    @ManyToOne
    @JoinColumn(name = "chair_id", nullable = false)
    private Chair chair;

    public Teacher() {

    }

    public Teacher(String name, String surname, String patronymic, Chair chair) {
        super(name, surname, patronymic);
        this.chair = chair;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }
}
