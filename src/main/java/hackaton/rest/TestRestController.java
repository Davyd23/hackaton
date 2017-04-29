package hackaton.rest;

import hackaton.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
//@RequestMapping("/hackaton")
public class TestRestController {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/default")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

    @RequestMapping("/resource")
    public Map<String,Object> resources() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
}
