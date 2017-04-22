package hackaton.entity;

import javax.persistence.*;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="aptitudini")
public class Skills {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column (name="skill")
    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat")
    private Candidate candidate;

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
}
