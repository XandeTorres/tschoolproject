package tschool.tarasov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tschool.tarasov.models.Tariff;


import java.util.List;

@Repository
public class TariffDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public TariffDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createTariff(Tariff tariff) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(tariff);
    }

    public void updateTariff(Tariff tariff) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(tariff);
    }

    public Tariff getTariffById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        // logger.info("Customer was loaded. Id: " + customer.getId());
        return (Tariff) session.load(Tariff.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Tariff> getTariffs() {
        Session session = this.sessionFactory.getCurrentSession();
        //logger.info("Getting list of Customers successfully");
        return session.createQuery("from Tariff ").list();
    }

}
