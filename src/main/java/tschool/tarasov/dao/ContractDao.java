package tschool.tarasov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tschool.tarasov.models.Contract;


import javax.persistence.Query;
import java.util.List;


@Repository
public class ContractDao {

    private final SessionFactory sessionFactory;
    @Autowired
    public ContractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createContract(Contract contract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(contract);
    }
    public void updateContract(Contract contract) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(contract);
    }

    public Contract getContractById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Contract contract = (Contract) session.load(Contract.class, id);
        return contract;
    }

    public Contract getContractByNumber(String number) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query =session.createQuery("FROM Contract WHERE number = :number").setParameter("number", number);
        Contract contract = (Contract) query.getSingleResult();
        return contract;
    }

    @SuppressWarnings("unchecked")
    public List<Contract> getContracts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Contract> listContracts = session.createQuery("from Contract ").list();
        //logger.info("Getting list of Customers successfully");
        return listContracts;
    }
}
