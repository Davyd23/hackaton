package hackaton.entity;

import javax.persistence.*;

@Entity
@Table(name = "candidate_to_posting")
public class CandidateToPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name="fk_user", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name="fk_posting", referencedColumnName = "id", nullable = false)
    private Posting posting;

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

    public Posting getPosting() {
        return posting;
    }

    public void setPosting(Posting posting) {
        this.posting = posting;
    }
}
