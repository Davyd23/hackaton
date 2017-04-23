package hackaton.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="candidate")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Candidate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="nume")
    private String name;

    @Column(name="prenume")
    private String surname;

    @Column (name="email")
    private String mail;

    @Column (name="password")
    private String password;

    @Column (name="telefon")
    private String phone;

    @OneToOne
    @JoinColumn(name = "fk_nivel_invatamant")
    private DoneStudies studies;

    @OneToMany(  fetch = FetchType.LAZY, mappedBy = "candidate")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<Jobs> jobsList;

    @OneToMany(  fetch = FetchType.LAZY, mappedBy = "candidate")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    List<Skills> skillsList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DoneStudies getStudies() {
        return studies;
    }

    public void setStudies(DoneStudies studies) {
        this.studies = studies;
    }

    public List<Jobs> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Jobs> jobsList) {
        this.jobsList = jobsList;
    }

    public List<Skills> getSkillsList() {
        return skillsList;
    }

    public void setSkillsList(List<Skills> skillsList) {
        this.skillsList = skillsList;
    }
}
