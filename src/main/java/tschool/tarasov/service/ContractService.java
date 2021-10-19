package tschool.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.ContractDao;
import tschool.tarasov.models.Contract;


import java.util.List;

@Service
public class ContractService {

    private final ContractDao contractDao;

    @Autowired
    public ContractService(ContractDao contractDao) {
        this.contractDao = contractDao;
    }

    @Transactional
    public void createContract(Contract contract) {
        this.contractDao.createContract(contract);
    }

    @Transactional
    public Contract getContractById(Long id) {
        return this.contractDao.getContractById(id);
    }

    @Transactional
    public Contract getContractByNumber(String number) {
        return this.contractDao.getContractByNumber(number);
    }

    @Transactional
    public List<Contract> listContracts() {

        return this.contractDao.getContracts();
    }
}
