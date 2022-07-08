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

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }
   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override

   public List<User> listUsers() {
      @SuppressWarnings("unchecked")
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }
   @Override

   public List<Car> listCars() {
      @SuppressWarnings("unchecked")
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("FROM Car");
      return query.getResultList();
   }
   @Override
   public User getUserByCar(String model, int series) {
      @SuppressWarnings("unchecked")
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User AS u INNER JOIN FETCH u.car AS car WHERE car.model = :car_model AND car.series = :car_series");
      query.setParameter("car_model", model);
      query.setParameter("car_series", series);
      return query.getResultList().get(0);
   }

}
