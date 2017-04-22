package hackaton.rest;

import hackaton.entity.Posting;
import hackaton.repository.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostingRestController {

    @Autowired
    PostingRepository postingRepository;

    @RequestMapping(value = "/hackaton/postings",
            method = RequestMethod.GET,
            produces = "application/json")
    @ResponseBody
    public List<Posting> getAll() {
        return postingRepository.findAll();
    }


}
