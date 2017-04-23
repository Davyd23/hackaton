package hackaton.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Marian on 4/22/2017.
 */

@Entity
@Table(name="posting")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Posting {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recruter")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Administrator administrator;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_nivel_studii")
    private Studies studies;

    @OneToMany(  fetch = FetchType.LAZY, mappedBy = "posting")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<PostingSkills> postingSkills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Studies getStudies() {
        return studies;
    }

    public void setStudies(Studies studies) {
        this.studies = studies;
    }
}
