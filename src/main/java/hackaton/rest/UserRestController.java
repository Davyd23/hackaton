package hackaton.rest;

import hackaton.dto.UserDTO;
import hackaton.entity.Role;
import hackaton.entity.User;
import hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@RestController
public class UserRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Principal user(Principal user) {
       return user;
    }

    @RequestMapping(value = "/app/register",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity register(@RequestBody UserDTO userDTO){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(!userDTO.getPassword().equals(userDTO.getRePassword() ) ){
            return responseEntity;
        }

        User registeringUser = new User();
        registeringUser.setEmail(userDTO.getEmail());
        registeringUser.setPassword(passwordEncoder.encode(userDTO.getPassword() ) );
        registeringUser.setNume(userDTO.getNume());
        registeringUser.setPrenume(userDTO.getPrenume());
        registeringUser.setEnabled(true);//momentan defaul pana se implementeaza serverul de SMTP

        Set<Role> userRoles = new HashSet<Role>();
        if(userDTO.isCandidate() ){
            userRoles.add(new Role(registeringUser, "candidate"));
        }
        if(userDTO.isRecruiter() ){
            userRoles.add(new Role(registeringUser, "recruiter") );
        }

        registeringUser.setRoles(userRoles);


        try{
            userRepository.save(registeringUser);
        }catch (Exception ex){
            //exista deja un user cu acest email in baza
            ex.printStackTrace();
            return responseEntity;
        }

        responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }
}
