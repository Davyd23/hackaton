package hackaton.rest;

import hackaton.dto.ProfileDTO;
import hackaton.dto.UserDTO;
import hackaton.entity.Profile;
import hackaton.entity.Role;
import hackaton.entity.User;
import hackaton.entity.UserToProfile;
import hackaton.repository.ProfileRepository;
import hackaton.repository.UserRepository;
import hackaton.repository.UserToProfileRepository;
import hackaton.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@RestController
public class UserRestController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserToProfileRepository userToProfileRepository;
    @Autowired
    MailService mailService;

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

        User registeringUser = new User();
        registeringUser.setEmail(userDTO.getEmail());
        registeringUser.setPassword(passwordEncoder.encode(userDTO.getPassword() ) );
        registeringUser.setNume(userDTO.getNume());
        registeringUser.setPrenume(userDTO.getPrenume());
        registeringUser.setEnabled(false);//momentan defaul pana se implementeaza serverul de SMTP

        registeringUser.setActivationKey(UUID.randomUUID().toString());
        registeringUser.setResetKey(UUID.randomUUID().toString());


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

        String baseUrl = httpServletRequest.getRequestURL().toString().replace(httpServletRequest.getRequestURI(), "");
        mailService.send(registeringUser, baseUrl);
        responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/user/profile",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveProfile(@RequestBody ProfileDTO profileDTO, Principal principal){

        Profile userProfile = null;
        User loggedUser = userRepository.findByEmail(principal.getName() );
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

        return new ResponseEntity(HttpStatus.OK);

    }


    @RequestMapping(value = "/user/profile",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileDTO> getProfile(Principal principal){

        User loggedUser = userRepository.findByEmail(principal.getName() );
        UserToProfile userToProfile = userToProfileRepository.findByUser(loggedUser);

        if(userToProfile == null){
            return new ResponseEntity( HttpStatus.BAD_REQUEST);
        }

        Profile userProfile = userToProfile.getProfile();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAnalitical(userProfile.isAnalitical() );
        profileDTO.setCreative(userProfile.isCreative() );
        profileDTO.setMultitasking(userProfile.isMultitasking() );
        profileDTO.setOvertimeWork(userProfile.isOvertimeWork() );
        profileDTO.setTeamwork(userProfile.isTeamwork() );
        profileDTO.setWorkExperience(userProfile.getWorkExperience() );


        return new ResponseEntity(profileDTO, HttpStatus.OK);


    }
}
