package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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

    public User getUserByCar(String model, int series) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User WHERE car.series = :series AND car.model = :model";
        Query query = session.createQuery(hql);
        query.setParameter("series", series);
        query.setParameter("model", model);
        query.setMaxResults(1);

        return (User) query.getSingleResult();
    }

}
