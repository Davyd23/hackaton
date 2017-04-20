package hackaton.rest;

import hackaton.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hackaton")
public class TestRestController {

    @Autowired
    ExampleRepository exampleRepository;

    @RequestMapping("/default")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

}
