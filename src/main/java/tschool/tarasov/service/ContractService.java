package tschool.tarasov.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.ContractDao;
import tschool.tarasov.models.Contract;
import tschool.tarasov.models.Option;
import tschool.tarasov.models.Tariff;


import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService {

    private final ContractDao contractDao;
    private final OptionService optionService;
    private final TariffService tariffService;

    @Autowired
    public ContractService(ContractDao contractDao, OptionService optionService, TariffService tariffService) {
        this.contractDao = contractDao;
        this.optionService = optionService;
        this.tariffService = tariffService;
    }

    @Transactional
    public void createContract(Contract contract, int chosenTariffId, int[] chosenOptionsIds) {
        List<Option> chosenOptionList = new ArrayList<>();
        for(int i = 0; i < chosenOptionsIds.length; i++){
            chosenOptionList.add(optionService.getOptionById((long) chosenOptionsIds[i]));
        }
        contract.setChosenOptionList(chosenOptionList);
        contract.setTariff(tariffService.getTariffById((long) chosenTariffId));
        contractDao.createContract(contract);
    }

    @Transactional
    public void updateContract(Contract contract) {
        this.contractDao.updateContract(contract);
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
        List<Contract> listContracts =contractDao.getContracts();
        for (Contract contract: listContracts) {
            Hibernate.initialize(contract.getChosenOptionList());
            Hibernate.initialize(contract.getTariff());
        }

        return listContracts;
    }
}
