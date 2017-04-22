package hackaton.constants;

import hackaton.dto.UserDTO;

public class UserConstant {
    private static UserDTO loggedUser;

    public static UserDTO getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(UserDTO loggedUser) {
        UserConstant.loggedUser = loggedUser;
    }
}
