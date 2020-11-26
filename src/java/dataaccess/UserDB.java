package dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;

public class UserDB {

    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }

    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    public User getByUUID(String resetPasswordUuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            User user = (User) em.createNamedQuery("User.findByResetPasswordUuid")
            .setParameter("resetPasswordUuid", resetPasswordUuid).getSingleResult();      
            return user;
        } finally {
            em.close();
        }
    }
}
