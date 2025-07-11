package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCourses();

  //↓30代の学生を取得するメソッド(No.11)
  @Select("SELECT * FROM students WHERE age >= 30 AND age <=39")
  List<Student> searchStudentsInThirties();

  //↓Javaコースの情報を取得するメソッド(No.11)
  @Select("SELECT * FROM students_courses WHERE course_name = 'Java' ")
  List<StudentsCourses> searchJavaCourse();
}

