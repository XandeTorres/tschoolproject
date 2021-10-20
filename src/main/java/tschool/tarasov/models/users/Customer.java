package tschool.tarasov.models.users;

import tschool.tarasov.models.Contract;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {

    @Column(name = "dateofbirth")
    private Date dateofbirth;
    @Column(name = "passport")
    private String passport;
    @Column(name = "address")
    private String address;

    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "id", referencedColumnName = "contract_id")

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    //@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<Contract> contractList;


    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(List<Contract> contractList) {
        this.contractList = contractList;
    }

    public void addContract(Contract contract) {
        contract.setCustomer(this);
        contractList = new ArrayList<>();
        contractList.add(contract);
    }


}
