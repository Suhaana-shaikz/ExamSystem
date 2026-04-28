package AUTH_SERVICE.demo.Controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;
@CrossOrigin(origins = "https://qa-frontend-obj9h2hhf-suhanashaik044-5430s-projects.vercel.app")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> data){

        String username = data.get("username");
        String password = data.get("password");

        if("admin".equals(username) && "admin123".equals(password)){
            return "Login Success ✅";
        } else {
            throw new RuntimeException("Invalid credentials ❌");
        }
    }
}

