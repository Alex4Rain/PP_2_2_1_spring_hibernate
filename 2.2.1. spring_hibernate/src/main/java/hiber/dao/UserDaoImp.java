package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override

   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }
   @Override
   public User getUserByCar(String model, int series) {
      for (Car car : this.listCars()) {
         if (car.getSeries() == series && car.getModel().equals(model)) {
            Query query = sessionFactory.getCurrentSession().createQuery("from User where id = :car_id");
            query.setParameter("car_id", car.getId());
            return (User) query.getResultList().get(0);
         }
      }
      throw new RuntimeException("Car with this model and series didn't exist in database");
   }

}
