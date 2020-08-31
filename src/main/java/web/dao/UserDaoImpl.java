package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> userList = entityManager.createQuery("from User").getResultList();
        entityManager.close();
        return userList;
    }

    @Override
    public void add(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(int id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            if (user != null){
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так.");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void edit(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


   /* @Override
    public void setUserInfoById(String firstName, String lastName, int age, String email, int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE User u set u.firstName = :firstName, u.lastName = :lastName, u.email = :email, u.age = :age WHERE u.id = :id")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("email", email)
                .setParameter("age", age)
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }*/

    @Override
    public User getById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, new Integer(id));
        entityManager.getTransaction().commit();
        entityManager.close();
        return user;
    }


}
