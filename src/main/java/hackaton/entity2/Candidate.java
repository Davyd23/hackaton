package hackaton.entity2;

import javax.persistence.*;

@Entity
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "nume")
    private String name;

    @Column(name = "prenume")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String password;

    @Column(name = "telefon")
    private String phone;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    /*public Integer getStudiesLevel() {
        return studiesLevel;
    }

    public void setStudiesLevel(Integer studiesLevel) {
        this.studiesLevel = studiesLevel;
    }*/
}
