package hackaton.dto;

import hackaton.entity.Jobs;
import hackaton.entity.Studies;

import java.util.List;

/**
 * Created by Roberta on 4/22/2017.
 */
public class CandidatDTO {

    private long id;

    private String name;

    private String surname;

    private String mail;

    private String password;

/*    @Column (name="sex")
    private char sex;*/


    private String phone;

    private Studies studies;

    private List<Jobs> jobs;

    private List<SkillsDTO> skills;

    public CandidatDTO(long id, String name, String surname, String mail, String password, String phone, Studies studies, List<Jobs> jobs) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.studies = studies;
        this.jobs = jobs;
    }


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

    public List<Jobs> getJobs() {
        return jobs;
    }

    public void setJobs(List<Jobs> jobs) {
        this.jobs = jobs;
    }
}
