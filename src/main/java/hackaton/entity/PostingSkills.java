package hackaton.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="aptitudini_posting")
public class PostingSkills {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column (name="aptitudine")
    private String skill;

    @Column (name="durata")
    private double time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_posting")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Posting posting;

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

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }
}
