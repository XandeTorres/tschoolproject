package tschool.tarasov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tschool.tarasov.models.Role;
import tschool.tarasov.models.users.Employee;

import javax.persistence.Query;

@Repository
public class UserAuthDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserAuthDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Employee getUserbyUserName(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query =session.createQuery("FROM Employee WHERE email = :email").setParameter("email", username);
        Employee employee = (Employee) query.getSingleResult();
        return employee;
    }

    public Role getRole(String username) {
        Employee employee = this.getUserbyUserName(username);
        return employee.getRole();
    }


}
