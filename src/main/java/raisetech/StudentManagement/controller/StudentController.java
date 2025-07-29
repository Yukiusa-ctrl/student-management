package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  // ↓ /studentListにアクセスされたとき、List<StudentDetail>を返す
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    // ↓ 全学生の情報を取得してリストに入れる
    List<Student> students = service.searchStudentList();
    // ↓ 全学生のコース受講情報を取得してリストに入れる
    List<StudentsCourses> studentsCourses = service.searchStudentsCourseList();

      // ↓ 完成した学生詳細情報のリストを返す必要がある。returnのあとにconverterってクラスを入れる。
    return converter.convertStudentDetails(students, studentsCourses);
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

