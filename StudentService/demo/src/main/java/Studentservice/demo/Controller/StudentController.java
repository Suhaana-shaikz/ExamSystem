package Studentservice.demo.Controller;



import Studentservice.demo.Dto.AnswerFullDTO;
import Studentservice.demo.Dto.SubmitRequest;
import Studentservice.demo.Entity.Answer;
import Studentservice.demo.Entity.Student;

import Studentservice.demo.QuestionModel.Question;
import Studentservice.demo.Repository.StudentRepo;
import Studentservice.demo.Service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private StudentService service;
    @GetMapping("/download")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=students_data.csv");

        List<AnswerFullDTO> data = service.getDashboard();

        PrintWriter writer = response.getWriter();

        // HEADER
//        writer.println("ID,StudentID,Name,Email,Gender,Age,Qualification,College,City,State,QuestionID,SelectedOption");
        writer.println("StudentID,Email,Gender,Age,Qualification,QuestionNumber,SelectedOption");

        // DATA
        for(AnswerFullDTO d : data){
            writer.println(

                            d.getStudentId() + "," +

                            d.getEmail() + "," +
                            d.getGender() + "," +
                            d.getAge() + "," +
                            d.getQualification() + "," +

                                    d.getQuestionNumber() + "," +


                            d.getSelectedOption()
            );
        }

        writer.flush();
        writer.close();
    }

    // 🔹 Submit
//    @PostMapping("/submit")
//    public String submit(@RequestBody SubmitRequest request){
//        service.submit(request);
//        return "Submitted Successfully";
//    }


    @PostMapping("/submit")
    public String submit(@RequestBody SubmitRequest request){
        return service.submit(request);
    }




    @GetMapping("/check/{email}")
    public boolean checkUser(@PathVariable String email){
        return studentRepo.existsByEmail(email);
    }

    // 🔹 Dashboard (MAIN API)
    @GetMapping("/dashboard")
    public List<AnswerFullDTO> dashboard(){
        return service.getDashboard();
    }

    @GetMapping("/test")
    public String test(){
        return "Working";
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(){
        return service.getQuestionsFromQuestionService();
    }

    // 🔹 Students
    @GetMapping("/all")
    public List<Student> getStudents(){
        return service.getStudents();
    }

    // 🔹 Answers
    @GetMapping("/answers")
    public List<Answer> getAnswers(){
        return service.getAnswers();
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(){
        service.deleteAllStudents();
        return "All Students Deleted Sucessfully";
    }

    @DeleteMapping("/deleteByid/{id}")
    public String deletebyid(@PathVariable int id){
        service.deleteStudent(id);
        return "deleted student";
    }
    @DeleteMapping("/deleteanswetbyid/{id}")
    public String deleteAnswer(@PathVariable int id){
        service.deleteAnswerbyid(id);
        return "deleted Answer";
    }






}