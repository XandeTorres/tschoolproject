package tschool.tarasov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tschool.tarasov.models.Option;

import java.util.List;

@Repository
public class OptionDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OptionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createOption(Option option) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(option);

    }

    public void updateOption(Option option) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(option);

    }

    public Option getOptionById(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Option.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Option> getOptions() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Option ").list();
    }

}
