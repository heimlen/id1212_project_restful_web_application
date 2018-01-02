package se.kth.id1212.project.heimlen.restful_course_web.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Entity representing how an Object, Course, relates to a table, Course, in the relational database.
 */

@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String displayName;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int period;
    //Cascade-type persist, so that removing a course does not remove all courses sharing teacher as well as the teacher.
    @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JsonIgnoreProperties("courses")
    private Teacher teacher;

    public Course() {
    }

    public Course(String displayName, String name, int period, Teacher teacher) {
        this.displayName = displayName;
        this.name = name;
        this.period = period;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
