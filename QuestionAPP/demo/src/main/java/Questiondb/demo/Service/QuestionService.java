package Questiondb.demo.Service;



import Questiondb.demo.Entity.Question;
import Questiondb.demo.Repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.*;
import org.springframework.data.domain.Sort;
@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    // 🔹 Add Question
    public Question addQuestion(Question q){
        return repo.save(q);
    }

    // 🔹 Get All Questions


    public List<Question> getAll() {

        List<Question> questions = repo.findAll(Sort.by("id"));

        int number = 1;

        for (Question q : questions) {
            q.setQuestionNumber(number++);
        }

        return questions;
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

    public List<Question> getRandomQuestions(int limit) {

        List<Question> all = repo.findAll(Sort.by("id"));

        int number = 1;

        for (Question q : all) {
            q.setQuestionNumber(number++);
        }

        Collections.shuffle(all);

        return all.subList(0, Math.min(limit, all.size()));
    }

    public int getQuestionNumberById(int questionId) {

        List<Question> questions = repo.findAll(Sort.by("id"));

        for (int i = 0; i < questions.size(); i++) {

            if (questions.get(i).getId() == questionId) {
                return i + 1;
            }

        }

        return -1;
    }



    public int getQuestionNumber(int questionId) {

        List<Question> questions = repo.findAll(Sort.by("id"));

        int number = 1;

        for (Question q : questions) {

            if (q.getId() == questionId) {
                return number;
            }

            number++;
        }

        return 0;
    }




}
