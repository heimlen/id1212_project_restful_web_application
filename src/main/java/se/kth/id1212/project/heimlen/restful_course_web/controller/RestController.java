package se.kth.id1212.project.heimlen.restful_course_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Course;
import se.kth.id1212.project.heimlen.restful_course_web.entity.Teacher;
import se.kth.id1212.project.heimlen.restful_course_web.service.CourseService;
import se.kth.id1212.project.heimlen.restful_course_web.service.TeacherService;

import java.util.List;

/**
 * The controller of the application. Responsible for both handling client HTTP requests and providing the correct
 * graphical view in return, as well as containing the entire REST API which can be used completely free from the
 * graphical interface.
 */

@Controller
public class RestController {

    @Autowired
    private CourseService courseService;

    @Autowired private TeacherService teacherService;

    /**
     * Returns the index.html template
     * @return index.html template
     */
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    /**
     * See @getIndex()
     */
    @GetMapping("/index")
    public String getIndex2() {
        return "index";
    }

    /**
     * Returns the courses page, in HTML format.
     * @param model The model object, which represents data that is available to the view.
     * @return courses.html template.
     */
    @GetMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("listOfCourses",getAllCourses());
        model.addAttribute("course", new Course());
        return "courses";
    }

    /**
     * Returns all courses in JSON format.
     * @return json data containing all courses
     */
    @GetMapping(value = "/courses.json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    /**
     * Used for adding a course graphically.
     * @param course Course object to be added.
     * @param model contains data available in the view.
     * @return course.html template
     */
    @PostMapping("/courses.html")
    @ResponseStatus(HttpStatus.OK)
    public String addCourseForm(Course course, Model model) {
        courseService.addCourse(course);
        return getCourses(model);
    }

    /**
     * Adds a course to the application.
     * @param course JSON data representing a course.
     */
    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public void addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    /**
     * Update course given a display name and an updated course.
     * @param dName
     * @param course
     */
    @PutMapping("courses/{dName}")
    public void updateCourseByDisplayName(@PathVariable String dName, @RequestBody Course course) {
        courseService.updateCourseByDisplayName(dName, course);
    }

    /**
     * Returns the courseForm template used to edit a course graphically.
     * @param dName the identifier for a course.
     * @param model contains the data available in the view.
     * @return courseForm.html template
     */
    @GetMapping("/courses/{dName}/edit")
    public String getUpdateCourseForm(@PathVariable String dName, Model model) {
        model.addAttribute("course", courseService.getCourseByDisplayName(dName));
        return "courseForm";
    }

    /**
     * Used when a user utilizes the edit GUI for a course.
     * @param dName the identifier for a course.
     * @param course a Course object that contains updated data.
     * @param model contains the data available in the view.
     * @return courses.html template
     */
    @PutMapping("/courses/{dName}/edit")
    @ResponseStatus(HttpStatus.OK)
    public String updateCourseByDisplayNameForm(@PathVariable String dName, Course course, Model model) {
        courseService.updateCourseByDisplayName(dName, course);
        return getCourses(model);
    }

    /**
     * Returns a course specified by a name in json data.
     * @param dName the display-name of the course.
     * @return JSON data of the course.
     */
    @GetMapping(value = "/courses/{dName}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Course getCourseByDisplayName(@PathVariable String dName) {
        return courseService.getCourseByDisplayName(dName);
    }

    /**
     * Returns the teacher of a course, given the name of the course.
     * @param dName the display-name of the course
     * @return JSON data representing teacher in charge of course
     */
    @GetMapping(value = "/courses/{dName}/teacher", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Teacher getTeacherOfCourseByFirstNameAndCourseName(@PathVariable String dName) {
        Course course = courseService.getCourseByDisplayName(dName);
        return course.getTeacher();
    }

    /**
     * Deletes the course with the given name
     * @param dName the display name of the course
     */
    @DeleteMapping("/courses/{dName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCourse(@PathVariable String dName, Model model) {
        courseService.deleteCourseByDisplayName(dName);
        return getCourses(model);
    }

    /**
     * Returns JSON data representing all teachers.
     * @return JSON data of all teachers.
     */
    @GetMapping(value = "/teachers.json", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    /**
     * Returns the teachers page, in HTML format.
     * @param model The model object, which represents data that is available to the view.
     * @return teachers.html template.
     */
    @GetMapping("/teachers")
    public String getTeachers(Model model) {
        model.addAttribute("listOfTeachers", teacherService.getAllTeachers());
        model.addAttribute("teacher", new Teacher());
        return "teachers";
    }

    /**
     * Used to automatically reload the UI after adding a teacher using the UI.
     * @param teacher teacher to add
     * @param model model to represent data
     * @return the teachers.html template.
     */
    @PostMapping("/teachers.html")
    @ResponseStatus(HttpStatus.OK)
    public String addTeacherForm(Teacher teacher, Model model) {
        teacherService.addTeacher(teacher);
        return getTeachers(model);
    }

    /**
     * Adds a teacher to the application, given JSON data representing a teacher.
     * @param teacher JSON data representing a teacher.
     */
    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    /**
     * Returns the JSON data representing a teacher specified by its name.
     * @param firstName The first-name of a teacher.
     * @return JSON data representing the teacher.
     */
    @GetMapping(value = "/teachers/{firstName}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Teacher getTeacherByFirstName(@PathVariable String firstName) {
        return teacherService.getTeacherByFirstName(firstName);
    }

    /**
     * Returns the teacherForm.html page in which the user can edit a teacher, graphically.
     * @param firstName the identifier for the teacher to edit.
     * @param model the model containing the data available in the view
     * @return teacherform.html
     */
    @GetMapping("/teachers/{firstName}/edit")
    public String getUpdateTeacherForm(@PathVariable String firstName, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherByFirstName(firstName));
        return "teacherForm";
    }

    @GetMapping(value = "/teachers/{firstName}/courses", produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Course> getCoursesByTeacher(@PathVariable String firstName) {
        return courseService.getCoursesByTeachersFirstName(firstName);
    }

    /**
     * Deletes the teacher identified by param firstName
     * @param firstName the identifier for the teacher to be deleted
     * @param model the model containing the data available in the view
     * @return teachers.html
     */
    @DeleteMapping("/teachers/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeacher(@PathVariable String firstName, Model model) {
        teacherService.deleteTeacherByFirstName(firstName);
        return getTeachers(model);
    }

    /**
     * Updates a teacher given the teachers first name and an updated teacher.
     * @param teacher JSON-data in the form of a teacher.
     * @param firstName the identifier for the existing teacher object.
     */
    @PutMapping("/teachers/{firstName}")
    public void updateTeacherWithDisplayName(@RequestBody Teacher teacher, @PathVariable String firstName) {
        teacherService.updateTeacher(teacher, firstName);
    }

    @PutMapping("/teachers/{firstName}.html")
    @ResponseStatus(HttpStatus.OK)
    public void updateTeacherWithDisplayNameForm(Teacher teacher, @PathVariable String firstName) {
        teacherService.updateTeacher(teacher, firstName);
    }

    /**
     * Used when updating a user via the GUI.
     * @param teacher the new teacher object that contains the data that is to be replaced in the existing.
     * @param firstName the identifier for the existing teacher object.
     * @param model the model which holds the data available to the view.
     * @return teachers.html template
     */
    @PutMapping("/teachers/{firstName}/edit")
    @ResponseStatus(HttpStatus.OK)
    public String updateTeacherWithFirstNameForm(Teacher teacher, @PathVariable String firstName, Model model) {
        teacherService.updateTeacher(teacher, firstName);
        return getTeachers(model);
    }
}
