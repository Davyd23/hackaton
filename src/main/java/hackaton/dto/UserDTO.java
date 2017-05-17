package hackaton.dto;


import hackaton.entity.Role;
import hackaton.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String nume, prenume, email, password, rePassword;
    private boolean candidate, recruiter;
    private List<RoleDTO> authorities;

    public UserDTO(){

    }

    public UserDTO(User user) {
        this.nume = user.getNume();
        this.prenume = user.getPrenume();
        this.email = user.getEmail();
        this.password = null;
        this.rePassword = null;
        authorities = new ArrayList<RoleDTO>();

        for(Role role : user.getRoles()){
            authorities.add(new RoleDTO(role));
        }

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public boolean isCandidate() {
        return candidate;
    }

    public void setCandidate(boolean candidate) {
        this.candidate = candidate;
    }

    public boolean isRecruiter() {
        return recruiter;
    }

    public void setRecruiter(boolean recruiter) {
        this.recruiter = recruiter;
    }

    public List<RoleDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<RoleDTO> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(RoleDTO authority){
        if(authorities == null){
            authorities = new ArrayList<RoleDTO>();
        }
        authorities.add(authority);
    }
}
