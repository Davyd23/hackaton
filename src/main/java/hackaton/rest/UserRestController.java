package hackaton.rest;

import hackaton.dto.UserDTO;
import hackaton.service.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRestController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/hackaton/login",
            method = RequestMethod.POST,
            produces = "application/json")
    @ResponseBody
    public UserDTO login(@RequestBody UserDTO userDTO) {
        boolean userExist = userService.checkUser(userDTO);

        if(userExist){
            return userDTO;
        }else{
            return null;
        }
    }
}
