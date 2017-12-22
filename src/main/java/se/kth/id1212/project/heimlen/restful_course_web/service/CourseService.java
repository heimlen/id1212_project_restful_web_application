package se.kth.id1212.project.heimlen.restful_course_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.repository.CourseRepository;

import java.util.ArrayList;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Returns a list of all courses currently in the db.
     * @return list of courses found in db.
     */
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    public Course getCourseByDisplayName(String dName) {
        return courseRepository.getCourseByDisplayName(dName);
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void deleteCourseByDisplayName(String dName) {
        courseRepository.deleteCourseByDisplayName(dName);
    }

}
