package com.example.quiz2.Service;

import com.example.quiz2.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean updateTeacher(String id, Teacher teacher) {
        for (Teacher t : teachers){
            if(t.getId().equals(id)){
                teachers.set(teachers.indexOf(t), teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(String id) {
        for(Teacher t: teachers){
            if(t.getId().equals(id)){
                teachers.remove(t);
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacherById(String id) {
        for (Teacher t : teachers){
            if(t.getId().equals(id)){
                return t;
            }
        }
        return null;
    }

    public ArrayList<Teacher> searchBySalary(Integer salary) {
        ArrayList<Teacher> list = new ArrayList<>();

        for (Teacher t : teachers){
            if(t.getSalary() >=  salary)
                list.add(t);
        }

        return list;
    }
}
