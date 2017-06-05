package hackaton.service;

import hackaton.dto.ProfileDTO;
import hackaton.dto.UserDTO;
import hackaton.entity.Profile;
import hackaton.entity.Role;
import hackaton.entity.User;
import hackaton.entity.UserToProfile;
import hackaton.repository.UserRepository;
import hackaton.repository.UserToProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserToProfileRepository userToProfileRepository;

    public User register(UserDTO userDTO){

        User registeringUser = new User();
        registeringUser.setEmail(userDTO.getEmail());
        registeringUser.setPassword(passwordEncoder.encode(userDTO.getPassword() ) );
        registeringUser.setNume(userDTO.getNume());
        registeringUser.setPrenume(userDTO.getPrenume());
        registeringUser.setEnabled(false);//default until users activates acount through email link

        String code = UUID.randomUUID().toString().replaceAll("\\W", "");// remove all non word chars
        registeringUser.setActivationKey(code);
        code = UUID.randomUUID().toString().replaceAll("\\W", "");
        registeringUser.setResetKey(code);


        Set<Role> userRoles = new HashSet<Role>();

        userRoles.add(new Role(registeringUser, "candidate"));

        if(userDTO.isRecruiter() ){
            userRoles.add(new Role(registeringUser, "recruiter") );
        }

        registeringUser.setRoles(userRoles);

        userRepository.save(registeringUser);

        return registeringUser;
    }

    public void saveProfile(ProfileDTO profileDTO, User loggedUser){
        Profile userProfile = null;
        UserToProfile userToProfile = userToProfileRepository.findByUser(loggedUser);
        if(userToProfile == null){
            userToProfile = new UserToProfile();
            userToProfile.setUser(loggedUser );
            userToProfile.setProfile(new Profile() );
        }


        userProfile = userToProfile.getProfile();

        userProfile.setAnalitical(profileDTO.isAnalitical() );
        userProfile.setCreative(profileDTO.isCreative() );
        userProfile.setMultitasking(profileDTO.isMultitasking() );
        userProfile.setOvertimeWork(profileDTO.isOvertimeWork() );
        userProfile.setTeamwork(profileDTO.isTeamwork() );
        userProfile.setWorkExperience(profileDTO.getWorkExperience() );

        userToProfileRepository.save(userToProfile );
    }

    public void createDefaultProfile(User user){
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setWorkExperience("0+");

        saveProfile(profileDTO, user);
    }

    public ProfileDTO getProfile(User loggedUser){

        UserToProfile userToProfile = userToProfileRepository.findByUser(loggedUser);
        if(userToProfile == null){
            return null;
        }

        Profile userProfile = userToProfile.getProfile();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAnalitical(userProfile.isAnalitical() );
        profileDTO.setCreative(userProfile.isCreative() );
        profileDTO.setMultitasking(userProfile.isMultitasking() );
        profileDTO.setOvertimeWork(userProfile.isOvertimeWork() );
        profileDTO.setTeamwork(userProfile.isTeamwork() );
        profileDTO.setWorkExperience(userProfile.getWorkExperience() );

        return profileDTO;
    }

    public boolean activateAccount(String activationKey){
        boolean activatedAccount = false;
        User user = userRepository.findByActivationKey(activationKey);

        if(user == null){
            return activatedAccount;
        }
        user.setEnabled(true);
        userRepository.save(user);

        activatedAccount = true;

        return activatedAccount;
    }
}
