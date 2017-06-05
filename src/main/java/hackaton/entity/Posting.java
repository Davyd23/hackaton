package hackaton.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posting")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_user", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL)
    private List<CandidateToPosting> candidateToPostingList;

    public Posting() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CandidateToPosting> getCandidateToPostingList() {
        return candidateToPostingList;
    }

    public void setCandidateToPostingList(List<CandidateToPosting> candidateToPostingList) {
        this.candidateToPostingList = candidateToPostingList;
    }
}
