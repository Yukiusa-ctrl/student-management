package raisetech.StudentManagement.domain;

import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourses;
import java.util.List;

@Getter
@Setter
public class StudentDetail {

    private Student student;
    // ↓ studentsCourses studentsCourses;のままだと一つのコースのみになるため、リスト型の複数のコースを呼べるようにした
    private List<StudentsCourses> studentsCourses;

}
