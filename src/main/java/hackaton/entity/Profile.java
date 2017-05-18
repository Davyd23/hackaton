package hackaton.entity;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "creative")
    private boolean creative;

    @Column(name = "overtime_work")
    private boolean overtimeWork;

    @Column(name = "analitical")
    private boolean analitical;

    @Column(name = "multitasking")
    private boolean multitasking;

    @Column(name = "teamwork")
    private boolean teamwork;

    @Column(name = "work_experience")
    private String workExperience;

    public Profile() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCreative() {
        return creative;
    }

    public void setCreative(boolean creative) {
        this.creative = creative;
    }

    public boolean isOvertimeWork() {
        return overtimeWork;
    }

    public void setOvertimeWork(boolean overtimeWork) {
        this.overtimeWork = overtimeWork;
    }

    public boolean isAnalitical() {
        return analitical;
    }

    public void setAnalitical(boolean analitical) {
        this.analitical = analitical;
    }

    public boolean isMultitasking() {
        return multitasking;
    }

    public void setMultitasking(boolean multitasking) {
        this.multitasking = multitasking;
    }

    public boolean isTeamwork() {
        return teamwork;
    }

    public void setTeamwork(boolean teamwork) {
        this.teamwork = teamwork;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }
}
