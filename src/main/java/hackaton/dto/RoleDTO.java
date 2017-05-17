package hackaton.dto;


import hackaton.entity.Role;

public class RoleDTO {
    private String role;

    public RoleDTO() {

    }

    public RoleDTO(Role role) {
        this.role = role.getRole();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
