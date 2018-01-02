package se.kth.id1212.project.heimlen.restful_course_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * Entity representing how an Object, Teacher, relates to a table, Teacher, in the relational database.
 */

@Entity
public class Teacher {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String emailAdress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    @JsonIgnoreProperties("teacher")
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String emailAdress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
