package hackaton.rest;

import hackaton.repository.AdministratorRepository;
import hackaton.repository.ExampleRepository;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collection;

@Controller
//@RequestMapping("/hackaton")
public class TestRestController {

    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @RequestMapping("/hackaton/default")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }
}
