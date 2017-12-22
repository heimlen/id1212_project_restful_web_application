package se.kth.id1212.project.heimlen.restful_course_web.entity;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;
    private String displayName;
    private String name;
    private int period;
    @ManyToOne(cascade=CascadeType.ALL)
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
