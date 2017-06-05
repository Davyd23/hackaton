package hackaton.service;

import hackaton.dto.PostingDTO;
import hackaton.dto.ProfileDTO;
import hackaton.entity.*;
import hackaton.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostingService {
    @Autowired
    private PostingToProfileRepository postingToProfileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostingRepository postingRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private UserToProfileRepository userToProfileRepository;
    @Autowired
    private CandidateToPostingRepository candidateToPostingRepository;

    public void save(PostingDTO postingDTO, Principal principal){
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
    }

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

    public boolean delete(List<PostingDTO> postingDTOs, Principal principal){
        User loggedUser = userRepository.findByEmail(principal.getName());
        boolean success = false;

        for(PostingDTO postingDTO : postingDTOs) {
            PostingToProfile postingToProfile = postingToProfileRepository.findByPostingUUID(postingDTO.getUuid());
            if (postingToProfile.equals(null)) {
                return success;
            }

            Posting posting = postingToProfile.getPosting();
            Profile profile = postingToProfile.getProfile();

            List<CandidateToPosting> candidatesToPosting = candidateToPostingRepository.findAllForPosting(posting);

            if (posting.getUser().equals(loggedUser)) {
//                int deleteRows = candidateToPostingRepository.deleteAllForPosting(posting);
                if(candidatesToPosting != null) {
                    candidateToPostingRepository.delete(candidatesToPosting);
                }
                postingToProfileRepository.delete(postingToProfile);
                postingRepository.delete(posting);
                profileRepository.delete(profile);
            }
        }

        success = true;
        return success;
    }

    public List<PostingDTO> getAllMatches(Principal principal){
        UserToProfile userToProfile = userToProfileRepository.findByEmail(principal.getName() );
        Profile currentUserProfile = userToProfile.getProfile();

        List<Posting> postings = candidateToPostingRepository.getAllAppliedToPostingsByUser(userToProfile.getUser() );

        List<Posting> postingList = postingToProfileRepository.findAllPostingsForProfile(currentUserProfile.isCreative(),
                currentUserProfile.isOvertimeWork(), currentUserProfile.isAnalitical(), currentUserProfile.isMultitasking(),
                currentUserProfile.isTeamwork(), currentUserProfile.getWorkExperience() );
        List<PostingDTO> postingDTOList = new ArrayList<PostingDTO>();
        for(Posting posting : postingList){
            PostingDTO postingDTO = new PostingDTO(new ProfileDTO(currentUserProfile),
                    posting.getTitle(), posting.getDescription(), posting.getCompanyName(), posting.getUuid());
            if(postings.contains(posting) ){
                postingDTO.setApplied(true);
            }

            postingDTOList.add(postingDTO);
        }



        return postingDTOList;
    }

    public void apply(PostingDTO postingDTO, Principal principal){

        Posting posting = postingRepository.findByUuid(postingDTO.getUuid());
        User loggedUser = userRepository.findByEmail(principal.getName());

        CandidateToPosting candidateToPosting = new CandidateToPosting();
        candidateToPosting.setUser(loggedUser);
        candidateToPosting.setPosting(posting);

        candidateToPostingRepository.save(candidateToPosting);
    }
}
