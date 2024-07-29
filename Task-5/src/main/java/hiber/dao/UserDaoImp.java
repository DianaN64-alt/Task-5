package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }


    @Override
    @Transactional
    public List<User> getUserCar(String model, int series) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u left join fetch u.car where u.car.model = :model and u.car.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<User> users = query.list();

        return users;
    }
}





