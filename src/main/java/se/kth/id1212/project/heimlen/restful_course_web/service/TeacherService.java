package se.kth.id1212.project.heimlen.restful_course_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service containing all business logic that has to do with teachers.
 */

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Returns all teachers currently in the database.
     * @return a list of teachers.
     */
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    /**
     * Returns a teacher given a identifying first-name.
     * @param firstName the identifier for the teacher.
     * @return A teacher with the provided identifier.
     */
    public Teacher getTeacherByFirstName(String firstName) {
        return teacherRepository.getTeacherByFirstName(firstName);
    }

    /**
     * Returns a teacher given a first-name and an email-address.
     * @param firstName identifying email-adress.
     * @param emailAdress identifying email-adress.
     * @return a teacher matching the provided identifiers.
     */
    public Teacher getTeacherByFirstNameAndEmailAdress(String firstName, String emailAdress) {
        return teacherRepository.getTeacherByFirstNameAndEmailAdress(firstName, emailAdress);
    }

    /**
     * Adds a new teacher to the system.
     * @param teacher the teacher object to add.
     */
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    /**
     * Deletes a teacher given an identifying first-name.
     * @param firstName identifier.
     */
    public void deleteTeacherByFirstName(String firstName) {
        teacherRepository.deleteTeacherByFirstName(firstName);
    }

    /**
     * Updates an existing teacher identified by the provided firstname.
     * @param teacher Object containing data to be added to the system.
     * @param firstName identifies teacher to be updated.
     */
    public void updateTeacher(Teacher teacher, String firstName) {
        Teacher existingTeacher = teacherRepository.getTeacherByFirstName(firstName);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setEmailAdress(teacher.getEmailAdress());
        existingTeacher.setLastName(teacher.getLastName());
        teacherRepository.save(existingTeacher);
    }
}
