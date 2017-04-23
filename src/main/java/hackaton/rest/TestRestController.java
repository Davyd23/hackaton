package hackaton.rest;

import hackaton.constants.UserConstant;
import hackaton.dto.UserDTO;
import hackaton.repository.*;
import hackaton.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Collection;

@Controller
//@RequestMapping("/hackaton")
public class TestRestController {


    @Autowired
    private DoneStudiesRepository doneStudiesRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private PostingSkillsRepository postingSkillsRepository;

    @Autowired
    private CandidateService candidateService;

    @RequestMapping("/hackaton/default")
    @ResponseBody
    public String home() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@yahoo.com");
        UserConstant.setLoggedUser(userDTO);
        candidateService.getPostingsInRelevantOrder();
        return "Hello World!";
    }
}
