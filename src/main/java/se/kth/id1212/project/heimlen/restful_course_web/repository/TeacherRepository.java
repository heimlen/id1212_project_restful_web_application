package se.kth.id1212.project.heimlen.restful_course_web.repository;

import org.springframework.data.repository.CrudRepository;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;

import javax.transaction.Transactional;

/**
 * The TeacherRepository is responsible for all the interaction with the database that has to do with teachers,
 * and is used by the TeacherService to communicate with the database.
 */
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

    Teacher getTeacherByFirstName(String firstName);

    @Transactional
    void deleteTeacherByFirstName(String firstName);

    Teacher getTeacherByFirstNameAndEmailAdress(String firstName, String emailAdress);
}
