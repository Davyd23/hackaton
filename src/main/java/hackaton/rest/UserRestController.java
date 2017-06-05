package hackaton.rest;

import hackaton.dto.ProfileDTO;
import hackaton.dto.UserDTO;
import hackaton.entity.User;
import hackaton.repository.UserRepository;
import hackaton.service.MailService;
import hackaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
public class UserRestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/data",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO user(Principal user) {
        if(user == null){
            return null;
        }

        User loggedUser = userRepository.findByEmail(user.getName());
        UserDTO userDTO = new UserDTO(loggedUser);

       return userDTO;
    }

    @RequestMapping(value = "/user/register",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity register(@RequestBody UserDTO userDTO, HttpServletRequest httpServletRequest){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(!userDTO.getPassword().equals(userDTO.getRePassword() ) ){
            return responseEntity;
        }

        User registeringUser = null;

        try{
            registeringUser = userService.register(userDTO);
        }catch (Exception ex){
            //exista deja un user cu acest email in baza
            ex.printStackTrace();
            return responseEntity;
        }
        userService.createDefaultProfile(registeringUser);

        String baseUrl = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        mailService.send(registeringUser, baseUrl);

        responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/user/profile",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveProfile(@RequestBody ProfileDTO profileDTO, Principal principal){
        User loggedUser = userRepository.findByEmail(principal.getName() );
        userService.saveProfile(profileDTO, loggedUser);

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/user/profile",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileDTO> getProfile(Principal principal){
        User loggedUser = userRepository.findByEmail(principal.getName() );

        ProfileDTO profileDTO = userService.getProfile(loggedUser);
        if(profileDTO == null){
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(profileDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/activate/{activation_key}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity activate(@PathVariable("activation_key") String activationKey){
        boolean activatedAccount = userService.activateAccount(activationKey);
        if(activatedAccount == false){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
