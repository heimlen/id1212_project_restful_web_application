package se.kth.id1212.project.heimlen.restful_course_web.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;

import javax.transaction.Transactional;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    Teacher getTeacherByFirstName(String firstName);

    @Transactional
    void deleteTeacherByFirstName(String firstName);
}
