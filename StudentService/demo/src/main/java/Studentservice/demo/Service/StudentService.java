package Studentservice.demo.Service;
import Studentservice.demo.Dto.AnswerFullDTO;
import Studentservice.demo.Dto.AnswerRequest;
import Studentservice.demo.Dto.SubmitRequest;
import Studentservice.demo.Entity.Answer;
import Studentservice.demo.Entity.Student;
import Studentservice.demo.QuestionModel.Question;
import Studentservice.demo.Repository.AnswerRepo;
import Studentservice.demo.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private AnswerRepo answerRepo;


    @Autowired
    private WebClient.Builder webClientBuilder;


//    public List<Question> getQuestionsFromQuestionService(){
//        System.out.println("NEW CODE EXECUTED");
//        return webClientBuilder.build()
//                .get()
//                .uri("https://examsystem-4.onrender.com/questions/random") // ✅ deployed URL
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Question>>() {}) // ✅ FIX
//                .block();
//
//
//    }

    public List<Question> getQuestionsFromQuestionService() {

        try {
            return webClientBuilder.build()
                    .get()
                    .uri("https://examsystem-4.onrender.com/questions/random")
                    .retrieve()
                    .bodyToMono(new org.springframework.core.ParameterizedTypeReference<List<Question>>() {})
                    .block();

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 MUST
            throw new RuntimeException("FAILED TO FETCH QUESTIONS");
        }
    }




    // 🔹 Submit Data
    public void submit(SubmitRequest request){

        Student s = new Student();

        s.setName(request.getName());
        s.setEmail(request.getEmail());
        s.setGender(request.getGender());
        s.setAge(request.getAge());
        s.setQualification(request.getQualification());
        s.setCollege(request.getCollege());
        s.setCity(request.getCity());
        s.setState(request.getState());

        Student savedStudent = studentRepo.save(s);

        for(AnswerRequest a : request.getAnswers()){

            Answer ans = new Answer();
            ans.setStudentId(savedStudent.getId());
            ans.setQuestionId(a.getQuestionId());
            ans.setSelectedOption(a.getSelectedOption());
            answerRepo.save(ans);

        }
    }












    // 🔹 Dashboard (Combined Table)
    public List<AnswerFullDTO> getDashboard(){

        List<Answer> answers = answerRepo.findAll();
        List<AnswerFullDTO> list = new ArrayList<>();

        for(Answer a : answers){

            Student s = studentRepo.findById(a.getStudentId()).orElse(null);

            AnswerFullDTO dto = new AnswerFullDTO();

            dto.setId(a.getId());
            dto.setStudentId(a.getStudentId());

            if(s != null){
                dto.setName(s.getName());
                dto.setEmail(s.getEmail());
                dto.setGender(s.getGender());
                dto.setAge(s.getAge());
                dto.setQualification(s.getQualification());
                dto.setCollege(s.getCollege());
                dto.setCity(s.getCity());
                dto.setState(s.getState());
            }

            dto.setQuestionId(a.getQuestionId());
            dto.setSelectedOption(a.getSelectedOption());

            list.add(dto);
        }

        return list;
    }

    // 🔹 Get all students
    public List<Student> getStudents(){
        return studentRepo.findAll();
    }

    // 🔹 Get all answers
    public List<Answer> getAnswers(){
        return answerRepo.findAll();
    }

    public void deleteStudent(int id){
        studentRepo.deleteById(id);
    }

    public void deleteAnswerbyid(int id){
        answerRepo.deleteById(id);
    }
    public void deleteAllStudents(){
        answerRepo.deleteAll();
        studentRepo.deleteAll();
    }
}
