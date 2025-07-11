package raisetech.StudentManagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList(){
    return service.searchStudentList();
  }

  @GetMapping("/studentsCourseList")
  public List<StudentsCourses> getStudentsCourseList(){
    return service.searchStudentsCourseList();
  }

  // ↓30代の学生のみを取得するエンドポイント(No.11)
  @GetMapping("/students/thirties")
  public List<Student> getStudentsInThirties() {
    return service.getStudentsInThirties();
  }

  // ↓Javaコースの情報のみを取得するエンドポイント(No.11)
  @GetMapping("/students-courses/java")
  public List<StudentsCourses> getJavaCourse() {
    return service.getJavaCourse();
  }

}
