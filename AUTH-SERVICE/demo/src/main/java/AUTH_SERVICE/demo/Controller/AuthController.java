package AUTH_SERVICE.demo.Controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

