package tschool.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tschool.tarasov.dao.TariffDao;
import tschool.tarasov.models.Tariff;

import java.util.List;

@Service
public class TariffService {

    private final TariffDao tariffDao;

    @Autowired
    public TariffService(TariffDao tariffDao) {
        this.tariffDao = tariffDao;
    }

    @Transactional
    public void createTariff(Tariff tariff) {
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
        return tariffDao.getTariffs();
    }



}
