package tschool.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.CustomerDao;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.users.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;
    private final ContractService contractService;

    @Autowired
    public CustomerService(CustomerDao customerDao, ContractService contractService) {
        this.customerDao = customerDao;
        this.contractService = contractService;
    }


    @Transactional
    public void createCustomer(Customer customer, String number) {
        //new contract number addition
        Contract contract = contractService.getContractByNumber(number);
        List<Contract> contractList = new ArrayList<>();
        contractList.add(contract);
        customer.setContractList(contractList);

        Customer savedCustomer = customerDao.persistCustomer(customer);
        contract.setCustomer(customer);
        contractService.updateContract(contract);


    }

    @Transactional
    public void updateCustomer(Customer customer) {
        this.customerDao.updateCustomer(customer);
    }

    @Transactional
    public Customer getCustomerById(Long id) {
        return this.customerDao.getCustomerById(id);
    }

    @Transactional
    public List<Customer> listCustomers() {

        return this.customerDao.getCustomers();
    }

}
