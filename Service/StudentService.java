package com.example.quiz2.Service;

import com.example.quiz2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id, Student student){
        for (Student s : students){
            if(s.getId().equals(id)){
                students.set(students.indexOf(s), student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(String id){
        for(Student s : students){
            if(s.getId().equals(id)){
                students.remove(s);
                return true;
            }
        }
        return false;
    }

    public Student getStudentByName(String name) {
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(name))
                return s;
        }
        return null;
    }

    public ArrayList<Student> searchByMajor(String major) {
        ArrayList<Student> list = new ArrayList<>();

        for (Student s : students){
            if(s.getMajor().equalsIgnoreCase(major))
                list.add(s);
        }

        return list;
    }
}
