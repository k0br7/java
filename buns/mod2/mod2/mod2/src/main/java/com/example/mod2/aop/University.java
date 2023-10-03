package com.example.mod2.aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class University {
    private List<Student> students = new ArrayList<>();

    public void addStudents(){
        Student st1 = new Student("Novgorodtsev Nikita",3, 7);
        Student st2 = new Student("Ivan Ivanov",2, 10);
        Student st3 = new Student("Nikita Orlov",1, 8);

        students.add(st1);
        students.add(st2);
        students.add(st3);
    }

    public List<Student> getStudents(){
        System.out.println("Начало работы метода getStudents");
//        System.out.println(students.get(3));
        System.out.println("Information from method getStudentsЖ");
        System.out.println(students);
        return students;
    }
}
