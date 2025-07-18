package raisetech.StudentManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search();
  }

  public List<StudentsCourses> searchStudentsCourseList() {
    return repository.searchStudentsCourses();
  }

  // ↓30代の学生を取得するサービスメソッド(No.11)
  public List<Student> getStudentsInThirties() {
    return repository.searchStudentsInThirties();
  }

  // ↓Javaコースの情報を取得するサービスメソッド(No.11)
  public List<StudentsCourses> getJavaCourse() {
    return repository.searchJavaCourse();
  }
}
