package hackaton.entity;

import javax.persistence.*;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="studii_efectuate")
public class DoneStudies {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column (name="nivel_invatamant")
    private String studiesLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat")
    private Candidate candidate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudiesLevel() {
        return studiesLevel;
    }

    public void setStudiesLevel(String studiesLevel) {
        this.studiesLevel = studiesLevel;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}

