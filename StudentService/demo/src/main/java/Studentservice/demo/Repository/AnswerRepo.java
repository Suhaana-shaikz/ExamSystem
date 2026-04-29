package Studentservice.demo.Repository;




import Studentservice.demo.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AnswerRepo extends JpaRepository<Answer, Integer> {

    List<Answer> findByStudentId(int studentId);

    Answer findByStudentIdAndQuestionId(int studentId, int questionId);
}