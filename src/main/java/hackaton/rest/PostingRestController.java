package hackaton.rest;

import hackaton.dto.PostingDTO;
import hackaton.dto.ProfileDTO;
import hackaton.entity.*;
import hackaton.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingRestController {

    @Autowired
    private PostingToProfileRepository postingToProfileRepository;
    @Autowired
    private UserToProfileRepository userToProfileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostingRepository postingRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@RequestBody PostingDTO postingDTO, Principal principal){

        PostingToProfile postingToProfile = postingToProfileRepository.findByPostingUUID(postingDTO.getUuid() );
        if(postingToProfile == null){
            postingToProfile = new PostingToProfile();
            postingToProfile.setProfile(new Profile());
            postingToProfile.setPosting(new Posting());

            User loggedUser = userRepository.findByEmail(principal.getName() );
            postingToProfile.getPosting().setUser(loggedUser);
        }
        Posting posting = postingToProfile.getPosting();
        posting.setCompanyName(postingDTO.getCompanyName());
        posting.setDescription(postingDTO.getDescription());
        posting.setUuid(postingDTO.getUuid() );
        posting.setTitle(postingDTO.getTitle() );

        Profile profile = postingToProfile.getProfile();
        profile.setAnalitical(postingDTO.getProfile().isAnalitical() );
        profile.setCreative(postingDTO.getProfile().isCreative() );
        profile.setMultitasking(postingDTO.getProfile().isMultitasking() );
        profile.setOvertimeWork(postingDTO.getProfile().isOvertimeWork() );
        profile.setTeamwork(postingDTO.getProfile().isTeamwork() );
        profile.setWorkExperience(postingDTO.getProfile().getWorkExperience() );

        postingToProfileRepository.save(postingToProfile);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostingDTO> getAll(Principal principal){
        List<PostingToProfile> postingToProfileList = postingToProfileRepository.findAllForEmail(principal.getName() );
        List<PostingDTO> postingDTOList = new ArrayList<PostingDTO>();

        for(PostingToProfile postingToProfile : postingToProfileList ){
            Posting posting = postingToProfile.getPosting();
            ProfileDTO profileDTO = new ProfileDTO(postingToProfile.getProfile());

            PostingDTO postingDTO = new PostingDTO(profileDTO, posting.getTitle(), posting.getDescription(), posting.getCompanyName(), posting.getUuid());
            postingDTOList.add(postingDTO);
        }

        return postingDTOList;
    }

    @RequestMapping(value = "/delete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@RequestBody List<PostingDTO> postingDTOs, Principal principal){
        User loggedUser = userRepository.findByEmail(principal.getName());

        for(PostingDTO postingDTO : postingDTOs) {
            PostingToProfile postingToProfile = postingToProfileRepository.findByPostingUUID(postingDTO.getUuid());
            if (postingToProfile.equals(null)) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }

            Posting posting = postingToProfile.getPosting();
            Profile profile = postingToProfile.getProfile();

            if (posting.getUser().equals(loggedUser)) {
                postingToProfileRepository.delete(postingToProfile);
                postingRepository.delete(posting);
                profileRepository.delete(profile);
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/matching",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostingDTO> getAllMatches(Principal principal){
        UserToProfile userToProfile = userToProfileRepository.findByEmail(principal.getName() );
        Profile currentUserProfile = userToProfile.getProfile();

        List<Posting> postingList = postingToProfileRepository.findAllPostingsForProfile(currentUserProfile.isCreative(),
                currentUserProfile.isOvertimeWork(), currentUserProfile.isAnalitical(), currentUserProfile.isMultitasking(),
                currentUserProfile.isTeamwork(), currentUserProfile.getWorkExperience() );
        List<PostingDTO> postingDTOList = new ArrayList<PostingDTO>();
        for(Posting posting : postingList){
            postingDTOList.add(new PostingDTO(new ProfileDTO(currentUserProfile),
                    posting.getTitle(), posting.getDescription(), posting.getCompanyName(), posting.getUuid()));
        }

        return postingDTOList;
    }
}
