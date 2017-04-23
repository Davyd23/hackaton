package hackaton.entity;

import javax.persistence.*;

@Entity
@Table(name="studii_efectuate")
public class DoneStudies {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name="nivel_invatamant")
    private String studiesLevel;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudiesLevel() {
        return studiesLevel;
    }

    public void setStudiesLevel(String studiesLevel) {
        this.studiesLevel = studiesLevel;
    }
}

