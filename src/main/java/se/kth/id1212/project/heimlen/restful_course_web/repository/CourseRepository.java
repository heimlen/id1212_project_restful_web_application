package se.kth.id1212.project.heimlen.restful_course_web.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    Course getCourseByDisplayName(String dName);

    @Transactional
    void deleteCourseByDisplayName(String dName);
}
