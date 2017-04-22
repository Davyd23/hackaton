package hackaton.service;


import hackaton.constants.UserConstant;
import hackaton.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public boolean checkUser(UserDTO userDTO){
        UserConstant.setLoggedUser(userDTO);
        return true;
    }
}
