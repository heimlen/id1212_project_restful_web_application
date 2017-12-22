package se.kth.id1212.project.heimlen.restful_course_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.repository.CourseRepository;

@Component
public class RestfulCourseWebApplicationRunner implements ApplicationRunner {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        courseRepository.save(new Course("java", "Java Programming", 4, new Teacher("Seb", "Beb","seb@course.se")));
        courseRepository.save(new Course("html", "Learning HTML", 2,new Teacher("Ajdo", "Tyro","ajdo@course.se")));
        courseRepository.save(new Course("oop", "Object Oriented Programming", 1,new Teacher("Otto", "Van Brechen","otto@course.se")));
    }
}
