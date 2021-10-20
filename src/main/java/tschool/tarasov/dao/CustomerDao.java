package tschool.tarasov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tschool.tarasov.models.users.Customer;

import java.util.List;

@Repository
public class CustomerDao {

    //private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);

    private final SessionFactory sessionFactory;
    @Autowired
    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
        //logger.info("Customer was saved to DB successfully" + customer.getPassport());

    }
    public Customer persistCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
        return customer;
    }


    public void updateCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
        //logger.info("Customer was updated successfully. Passport: " + customer.getPassport());
    }

    public Customer getCustomerById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer customer = (Customer) session.load(Customer.class, id);
       // logger.info("Customer was loaded. Id: " + customer.getId());
        return customer;
    }
    @SuppressWarnings("unchecked")
    public List<Customer> getCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Customer> listCustomers = session.createQuery("from Customer").list();
        //logger.info("Getting list of Customers successfully");
        return listCustomers;
    }








}
