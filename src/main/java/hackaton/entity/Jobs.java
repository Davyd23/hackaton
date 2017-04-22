package hackaton.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="jobs")
public class Jobs {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column (name="companie")
    private String company;

    @Column (name="start")
    private Date startDate;

    @Column (name="stop")
    private Date stopDate;

    @Column (name="pozitie")
    private String position;

    @Column (name="descriere")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidat")
    private Candidate candidate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
