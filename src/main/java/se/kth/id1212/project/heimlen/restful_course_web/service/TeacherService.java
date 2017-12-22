package se.kth.id1212.project.heimlen.restful_course_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Returns all teachers currently in the database
     * @return a list of teachers
     */
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    public Teacher getTeacherByFirstName(String firstName) {
        return teacherRepository.getTeacherByFirstName(firstName);
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public void deleteTeacherByFirstName(String firstName) {
        teacherRepository.deleteTeacherByFirstName(firstName);
    }
}
