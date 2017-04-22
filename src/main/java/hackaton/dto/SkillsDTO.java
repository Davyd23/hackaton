package hackaton.dto;

import hackaton.entity.Candidate;

/**
 * Created by Roberta on 4/22/2017.
 */
public class SkillsDTO {

    private long id;

    private String skill;

    private Candidate candidate;

    private double experienta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public double getExperienta() {
        return experienta;
    }

    public void setExperienta(double experienta) {
        this.experienta = experienta;
    }
}
