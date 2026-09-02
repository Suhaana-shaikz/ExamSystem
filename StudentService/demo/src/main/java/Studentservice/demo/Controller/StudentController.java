package Studentservice.demo.Controller;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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
    public void downloadExcel(HttpServletResponse response) throws IOException {

        response.setContentType(
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        );

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=students_data.xlsx"
        );

        List<AnswerFullDTO> data = service.getDashboard();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Students");

        // HEADER
        Row header = sheet.createRow(0);

        String[] headers = {
                "Name",
                "Email",
                "Gender",
                "Age",
                "Qualification",
                "College",
                "City",
                "State",
                "QuestionID",
                "SelectedOption"
        };

        for (int i = 0; i < headers.length; i++) {
            header.createCell(i).setCellValue(headers[i]);
        }

        // DATA
        int rowNum = 1;

        for (AnswerFullDTO d : data) {

            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(d.getName());
            row.createCell(1).setCellValue(d.getEmail());
            row.createCell(2).setCellValue(d.getGender());
            row.createCell(3).setCellValue(d.getAge());
            row.createCell(4).setCellValue(d.getQualification());
            row.createCell(5).setCellValue(d.getCollege());
            row.createCell(6).setCellValue(d.getCity());
            row.createCell(7).setCellValue(d.getState());
            row.createCell(8).setCellValue(d.getQuestionId());
            row.createCell(9).setCellValue(d.getSelectedOption());
        }

        // Adjust column widths
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }




//    @GetMapping("/download")
//    public void downloadCSV(HttpServletResponse response) throws IOException {
//
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=students_data.csv");
//
//        List<AnswerFullDTO> data = service.getDashboard();
//
//        PrintWriter writer = response.getWriter();
//
//        // HEADER
//        writer.println("Name,Email,Gender,Age,Qualification,College,City,State,QuestionID,SelectedOption");
//
//        // DATA
//        for(AnswerFullDTO d : data){
//            writer.println(
//
//
//                            d.getName() + "," +
//                            d.getEmail() + "," +
//                            d.getGender() + "," +
//                            d.getAge() + "," +
//                            d.getQualification() + "," +
//                            d.getCollege() + "," +
//                            d.getCity() + "," +
//                            d.getState() + "," +
//                            d.getQuestionId() + "," +
//                            d.getSelectedOption()
//            );
//        }
//
//        writer.flush();
//        writer.close();
//    }
    // 🔹 Submit
    @PostMapping("/submit")
    public String submit(@RequestBody SubmitRequest request){
        service.submit(request);
        return "Submitted Successfully";
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