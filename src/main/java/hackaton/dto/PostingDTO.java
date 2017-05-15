package hackaton.dto;


public class PostingDTO {
    private ProfileDTO profile;
    private String title, description, companyName, uuid;

    public ProfileDTO getProfile() {
        return profile;
    }

    public PostingDTO() {
    }

    public PostingDTO(ProfileDTO profile, String title, String description, String companyName, String uuid) {
        this.profile = profile;
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.uuid = uuid;
    }

    public void setProfile(ProfileDTO profile) {
        this.profile = profile;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
