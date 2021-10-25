package tschool.tarasov.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.TariffDao;
import tschool.tarasov.models.Option;
import tschool.tarasov.models.Tariff;

import java.util.ArrayList;
import java.util.List;

@Service
public class TariffService {

    private final TariffDao tariffDao;
    private final OptionService optionService;

    @Autowired
    public TariffService(TariffDao tariffDao, OptionService optionService) {
        this.tariffDao = tariffDao;
        this.optionService = optionService;
    }

    @Transactional
    public void createTariffWithOptions(Tariff tariff, int[] optionsIds) {
        List<Option> optionList = new ArrayList<>();
        for(int i = 0; i < optionsIds.length; i++){
            optionList.add(optionService.getOptionById((long) optionsIds[i]));
        }
        tariff.setOptionList(optionList);
        tariffDao.createTariff(tariff);
    }

    @Transactional
    public void updateTariff(Tariff tariff) {
        tariffDao.updateTariff(tariff);
    }
    @Transactional
    public Tariff getTariffById(Long id) {
        return tariffDao.getTariffById(id);
    }

    @Transactional
    public List<Tariff> getTariffs() {
        List<Tariff> listTariff =tariffDao.getTariffs();
        for (Tariff tariff: listTariff) {
            Hibernate.initialize(tariff.getOptionList());
            Hibernate.initialize(tariff.getContractList());
        }

        return listTariff;
    }



}
