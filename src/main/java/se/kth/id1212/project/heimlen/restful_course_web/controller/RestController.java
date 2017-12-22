package se.kth.id1212.project.heimlen.restful_course_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.service.CourseService;
import se.kth.id1212.project.heimlen.restful_course_web.service.TeacherService;

import java.util.List;

/**
 * The restful controller of the application. Responsible for handling client HTTP requests and providing the correct
 * views in return.
 */

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private CourseService courseService;

    @Autowired private TeacherService teacherService;

    @GetMapping("/test")
    public String getTest() {
        return "This is a test and if you see this it was successfull";
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @GetMapping("/courses/{dName}")
    public Course getCourseByDisplayName(@PathVariable String dName) {
        return courseService.getCourseByDisplayName(dName);
    }

    @DeleteMapping("/courses/{dName}")
    public void deleteCourse(@PathVariable String dName) {
        courseService.deleteCourseByDisplayName(dName);
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PostMapping("/teachers")
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    @GetMapping("/teachers/{firstName}")
    public Teacher getTeacherByFirstName(@PathVariable String firstName) {
        return teacherService.getTeacherByFirstName(firstName);
    }

}
