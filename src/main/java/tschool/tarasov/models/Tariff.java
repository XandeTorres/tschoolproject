package tschool.tarasov.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL)
    private List<Contract> contractList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tariff_option",
            joinColumns = @JoinColumn(name = "tariff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id"))
    private List<Option> optionList;

    public Tariff() { }

    public Tariff(Long id ,String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public void addContract(Contract contract) {
        contract.setTariff(this);
        contractList = new ArrayList<>();
        contractList.add(contract);
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
