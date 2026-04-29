package Studentservice.demo.Repository;

import Studentservice.demo.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamRepo extends JpaRepository<Exam, Integer> {

    List<Exam> findByStudentId(int studentId);
}
