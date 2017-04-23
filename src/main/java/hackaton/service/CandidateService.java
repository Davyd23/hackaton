package hackaton.service;

import hackaton.constants.UserConstant;
import hackaton.dto.SkillsDTO;
import hackaton.entity.*;
import hackaton.repository.CandidateRepository;
import hackaton.repository.PostingSkillsRepository;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PostingSkillsRepository postingSkillsRepository;

    public List<Posting> getPostingsInRelevantOrder(){
        Candidate candidate = candidateRepository.getWithMail(UserConstant.getLoggedUser().getEmail());

        List<String> skillList = new ArrayList<String>();
        List<SkillsDTO> skillsDTOs = new ArrayList<SkillsDTO>();

        for(Skills skill: candidate.getSkillsList()){
            skillList.add(skill.getSkill() );

            SkillsDTO skillsDTO = new SkillsDTO();
            skillsDTO.setCandidate(skill.getCandidate());
            skillsDTO.setId(skill.getId());
            skill.setSkill(skill.getSkill());

            skillsDTOs.add(skillsDTO);
        }
        List<PostingSkills> postingList = postingSkillsRepository.getAllPostingsForSkills(skillList);

        List<Posting> postings = new ArrayList<Posting>();
        for(PostingSkills postingSkill: postingList){
            postings.add(postingSkill.getPosting());
        }

        Set<Posting> s = new HashSet<Posting>();
        s.addAll(postings);
        postings = new ArrayList<Posting>();
        postings.addAll(s);

        for(Jobs job : candidate.getJobsList()){
            DateTime startDate = new DateTime(job.getStartDate());
            DateTime endDate = new DateTime(job.getStopDate());

            double durata = (Months.monthsBetween(startDate, endDate).getMonths() )  /12;

            for(ExperienceSkills experienceSkill: job.getExperienceSkillsList()){
                int skillIndex = skillList.indexOf(experienceSkill.getSkills().getSkill() );

                if(skillIndex>-1){
                    SkillsDTO skillsDTO = skillsDTOs.get(skillIndex);
                    skillsDTO.setExperienta(skillsDTO.getExperienta() + durata);
                }
            }
        }

        List<Posting> orderedPostings = getOderedPostingsByMatching(skillsDTOs, postings);
        return null;
    }

    private List<Posting> getOderedPostingsByMatching(List<SkillsDTO> skillsDTO, List<Posting> postings){
            return null;
    }
}
