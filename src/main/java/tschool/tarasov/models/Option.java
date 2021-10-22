package tschool.tarasov.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "option")
public class Option {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "connection_cost")
    private Long connectionCost;

    @ManyToMany(mappedBy = "optionList")
    private List<Tariff> tariffList;

    @ManyToMany(mappedBy = "chosenOptionList")
    private List<Contract> contractList;

    public Option() { }

    public Option(Long id, String name, Long price, Long connectionCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.connectionCost = connectionCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getConnectionCost() {
        return connectionCost;
    }

    public void setConnectionCost(Long connectionCost) {
        this.connectionCost = connectionCost;
    }

}
