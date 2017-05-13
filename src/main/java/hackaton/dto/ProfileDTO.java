package hackaton.dto;

public class ProfileDTO {
    private boolean creative, overtimeWork, analitical, multitasking, teamwork;
    private String workExperience;

    public ProfileDTO() {}

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
