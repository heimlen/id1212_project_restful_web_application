package se.kth.id1212.project.heimlen.restful_course_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.repository.CourseRepository;

import java.util.ArrayList;

import java.util.List;

/**
 * The CourseService contains all business logic needed for handling courses.
 */

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherService teacherService;

    /**
     * Returns a list of all courses currently in the db.
     * @return list of courses found in db.
     */
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }

    /**
     * Returns a course given its identifying display-name.
     * @param dName the identifier for a course.
     * @return Course object with the given display-name.
     */
    public Course getCourseByDisplayName(String dName) {
        return courseRepository.getCourseByDisplayName(dName);
    }

    /**
     * Adds a new course to the system. If the teacher object in the course already exists in the database, the existing
     * teacher is used instead of creating another identical teacher.
     * @param course the course to add.
     */
    public void addCourse(Course course) {
        Teacher teacherInCourse = course.getTeacher();
        Teacher existingTeacher = teacherService.getTeacherByFirstNameAndEmailAdress(teacherInCourse.getFirstName(), teacherInCourse.getEmailAdress());
        if(existingTeacher != null) {
            course.setTeacher(existingTeacher);
        }
        courseRepository.save(course);
    }

    /**
     * Deletes a course given a identifying display-name.
     * @param dName identifier.
     */
    public void deleteCourseByDisplayName(String dName) {
        courseRepository.deleteCourseByDisplayName(dName);
    }

    /**
     * Updates a course given a identifying display-name. If the updated teacher already exists in the system, the existing
     * teacher will be used instead of creating a new identical one.
     * @param dName identifier.
     * @param course Object containing the updated data.
     */
    public void updateCourseByDisplayName(String dName, Course course) {
        Teacher courseTeacher = course.getTeacher();
        Course existingCourse = courseRepository.getCourseByDisplayName(dName);
        existingCourse.setDisplayName(course.getDisplayName());
        existingCourse.setName(course.getName());
        existingCourse.setPeriod(course.getPeriod());
        Teacher existingTeacher = teacherService.getTeacherByFirstNameAndEmailAdress(course.getTeacher().getFirstName(), course.getTeacher().getEmailAdress());
        if(existingTeacher == null) {
            teacherService.addTeacher(courseTeacher);
            existingCourse.setTeacher(courseTeacher);

        } else {
            existingCourse.setTeacher(existingTeacher);
        }
        courseRepository.save(existingCourse);
    }

    /**
     * Returns all courses that has the teacher identified by the provided first-name as "teacher"
     * @param firstName the identifier for the teacher of the courses.
     * @return a list of courses all having the teacher as "teacher".
     */
    public List<Course> getCoursesByTeachersFirstName(String firstName) {
        return courseRepository.getCoursesByTeacherFirstName(firstName);
    }
}
