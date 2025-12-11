package app.model;

import java.util.List;
import java.util.NoSuchElementException;

import app.dao.StudentDBAccess;

// Mostly in charge of complex data validation
public class StudentDBModel {

    private StudentDBAccess dao;

    public StudentDBModel(StudentDBAccess dao) {
        this.dao = dao;
    }

    public String insertStudent(String[] name) {

        if (name.length == 3) {
            this.dao.insertStudent(name[0], name[1], name[2]);
        } else {
            this.dao.insertStudent(name[0], null, name[1]);
        }

        return "Insert successful!";
    }

    public List<String> displayStudents() { // Displays student table contents
        return this.dao.selectStudents();
    }

    public String deleteStudent(String[] name) {
        try {
            this.dao.deleteStudent(name[0], name[1], name[2]);
        } catch (NoSuchElementException e) {
            return "No such student in database.";
        }

        return "Delete successful!";
    }

}
