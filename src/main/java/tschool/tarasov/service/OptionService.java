package tschool.tarasov.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.OptionDao;
import tschool.tarasov.models.Option;
import tschool.tarasov.models.Tariff;

import java.util.List;

@Service
public class OptionService {

    private final OptionDao optionDao;

    @Autowired
    public OptionService(OptionDao optionDao) {
        this.optionDao = optionDao;
    }
    @Transactional
    public void createOption(Option option) {
        optionDao.createOption(option);
    }

    @Transactional
    public void updateOption(Option option) {
        optionDao.updateOption(option);
    }

    @Transactional
    public Option getOptionById(Long id) {
        return optionDao.getOptionById(id);
    }

    @Transactional
    public List<Option> getOptions() {
        List<Option> listOption =optionDao.getOptions();
        for (Option option: listOption) {
            Hibernate.initialize(option.getTariffList());
            Hibernate.initialize(option.getContractList());
        }

        return listOption;
    }

}
