package hackaton.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marian on 4/22/2017.
 */


@Entity
@Table(name="administrator")
public class Administrator {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="nume")
    private String name;

    @Column(name="prenume")
    private String surname;

    @Column (name="mail")
    private String mail;

    @Column (name="companie")
    private String company;

    @Column (name="password")
    private String password;

    @Column (name="sex")
    private char sex;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrator")
    private List<Posting> postings;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public List<Posting> getPostings() {
        return postings;
    }

    public void setPostings(List<Posting> postings) {
        this.postings = postings;
    }
}
