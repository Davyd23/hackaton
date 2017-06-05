package hackaton.rest;

import hackaton.dto.PostingDTO;
import hackaton.dto.UserDTO;
import hackaton.entity.User;
import hackaton.service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingRestController {
    @Autowired
    private PostingService postingService;

    @RequestMapping(method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody PostingDTO postingDTO, Principal principal){
        postingService.save(postingDTO, principal);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostingDTO> getAll(Principal principal){
        return  postingService.getAll(principal);
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody List<PostingDTO> postingDTOs, Principal principal){
        if(!postingService.delete(postingDTOs, principal) ){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/matching",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostingDTO> getAllMatches(Principal principal){
        return postingService.getAllMatches(principal);
    }

    @RequestMapping(value = "/apply",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity apply(@RequestBody PostingDTO postingDTO, Principal principal){
        postingService.apply(postingDTO, principal);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/candidates",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllApplicants(@RequestBody PostingDTO postingDTO){

        return postingService.getApplicants(postingDTO );
    }
}
