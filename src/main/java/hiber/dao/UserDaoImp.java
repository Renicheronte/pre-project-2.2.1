package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    @Override
    public List<User> getUsersList() {
        try {
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
            return query.getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }


    @Override
    public List<User> getUsersByCarModelSeries(Car car) {
        try {
            return sessionFactory.getCurrentSession().createQuery(
                            "from User user where user.car.model = :model and user.car.series = :series", User.class)
                    .setParameter("model", car.getModel())
                    .setParameter("series", car.getSeries())
                    .getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }
}
