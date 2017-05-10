package hackaton.entity;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="email", referencedColumnName = "email", nullable = false)
    private User user;

    @Column (name="role")
    private String role;

    public Role() {

    }

    public Role(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
