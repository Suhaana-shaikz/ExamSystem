package Questiondb.demo.Repo;


import Questiondb.demo.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
}