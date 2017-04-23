package hackaton.entity2;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name = "studii_efectuate")
public class DoneStudies {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "nivel_invatamant")
    private String studyLevels;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudyLevels() {
        return studyLevels;
    }

    public void setStudyLevels(String studyLevels) {
        this.studyLevels = studyLevels;
    }
}
