package se.kth.id1212.project.heimlen.restful_course_web.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The CourseRepository is responsible for all the interaction with the database that has to do with courses,
 * and is used by the CourseService to communicate with the database.
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course getCourseByDisplayName(String dName);

    @Transactional
    void deleteCourseByDisplayName(String dName);

    List<Course> getCoursesByTeacherFirstName(String firstName);
}
