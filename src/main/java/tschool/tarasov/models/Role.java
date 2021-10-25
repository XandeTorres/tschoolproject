package tschool.tarasov.models;

import tschool.tarasov.models.users.Employee;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

}
