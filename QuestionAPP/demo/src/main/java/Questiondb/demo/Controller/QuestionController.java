package Questiondb.demo.Controller;



import Questiondb.demo.Entity.Question;
import Questiondb.demo.Repo.QuestionRepo;
import Questiondb.demo.Service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")

public class QuestionController {

    @Value("${PORT}")
    private String port;


    @Autowired
    private QuestionService service;
@Autowired
private QuestionRepo questionRepo;




    @PostMapping
    public Question add(@RequestBody Question q){



        return service.addQuestion(q);
    }



    // 🔹 Get All






    @GetMapping("/all")
    public List<Question> getAll(){



        return service.getAll();
    }








    // 🔹 Random 20
    @GetMapping("/random/{limit}")
    public List<Question> random(
            @PathVariable int limit
    ){
        return service.getRandomQuestions(limit);
    }

    // 🔹 Update
    @PutMapping("/{id}")
    public Question update(@PathVariable int id, @RequestBody Question q){
        return service.update(id, q);
    }

    // 🔹 Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        service.delete(id);
        return "Deleted Successfully";
    }

    @PostMapping("/byIds")
    public List<Question> getByIds(@RequestBody List<Integer> ids){
        return questionRepo.findAllById(ids);
    }
}