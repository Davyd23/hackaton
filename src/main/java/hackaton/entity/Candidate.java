package hackaton.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="candidate2")
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

/*    @Column (name="sex")
    private char sex;*/

    @Column (name="telefon")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_nivel_invatamant")
    private Studies studies;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
    private List<Jobs> jobs;*/

/*    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
    private List<Studies> studies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "candidate")
    private List<Skills> skills;*/

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

    /*public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }*/

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

 /*   public List<Jobs> getJobs() {
        return jobs;
    }

    public void setJobs(List<Jobs> jobs) {
        this.jobs = jobs;
    }*/

/*    public List<Studies> getStudies() {
        return studies;
    }

    public void setStudies(List<Studies> studies) {
        this.studies = studies;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }*/

    public Studies getStudies() {
        return studies;
    }

    public void setStudies(Studies studies) {
        this.studies = studies;
    }
}
