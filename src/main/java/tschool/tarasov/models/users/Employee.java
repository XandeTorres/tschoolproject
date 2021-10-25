package tschool.tarasov.models.users;

import tschool.tarasov.models.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Employee extends User{

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
