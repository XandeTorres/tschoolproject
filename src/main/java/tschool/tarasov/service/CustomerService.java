package tschool.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.CustomerDao;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.users.Customer;

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
        Contract contract = contractService.getContractByNumber(number);
        customer.addContract(contract);
        customerDao.persistCustomer(customer);
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
