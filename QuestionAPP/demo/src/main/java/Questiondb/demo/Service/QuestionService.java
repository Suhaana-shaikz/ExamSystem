package Questiondb.demo.Service;



import Questiondb.demo.Entity.Question;
import Questiondb.demo.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    // 🔹 Add Question
    public Question addQuestion(Question q){
        return repo.save(q);
    }

    // 🔹 Get All Questions
    public List<Question> getAll(){
        return repo.findAll();
    }

    // 🔹 Delete
    public void delete(int id){
        repo.deleteById(id);
    }

    // 🔹 Update
    public Question update(int id, Question q){
        Question existing = repo.findById(id).orElseThrow();

        existing.setQuestion(q.getQuestion());
        existing.setOption1(q.getOption1());
        existing.setOption2(q.getOption2());
        existing.setOption3(q.getOption3());
        existing.setOption4(q.getOption4());

        return repo.save(existing);
    }

    // 🔥 Random 20 Questions (IMPORTANT)
    public List<Question> getRandomQuestions(){

        List<Question> all = repo.findAll();

        Collections.shuffle(all);

        return all.subList(0, Math.min(20, all.size()));
    }
}
