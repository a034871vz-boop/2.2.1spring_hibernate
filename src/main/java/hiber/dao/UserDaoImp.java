package hiber.dao;

import hiber.model.User;
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
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public void clear(){
      sessionFactory.getCurrentSession().
              createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
   }

   @Override
   public void removeUserById(long id) {
      sessionFactory.getCurrentSession()
              .createSQLQuery("DELETE FROM USERS WHERE id = :?")
              .setParameter("?", id).executeUpdate();
   }

   @Override
   public User getUserByCar(int series) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("From User as u where u.car.series =: series")
              .setParameter("series", series).getSingleResult();
   }

}
