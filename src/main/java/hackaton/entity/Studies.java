package hackaton.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="studii")
public class Studies {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="universitate")
    private String university;

    @Column(name="specializare")
    private String specialization;

    @Column(name="start")
    private Date startDate;

    @Column(name="stop")
    private Date stopDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat")
    private Candidate candidate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
