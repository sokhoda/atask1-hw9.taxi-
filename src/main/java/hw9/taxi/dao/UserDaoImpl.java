package hw9.taxi.dao;

import hw9.taxi.domain.User;
import hw9.taxi.exception.AuthenticationException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by s_okhoda on 03.03.2016.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{
    private static Logger log = Logger.getLogger(UserDao.class);

    @Autowired
    private SessionFactory factory;

    public UserDaoImpl() {

    }

    @Override
    public Long create(User user) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (User) session.get(User.class, id);
    }

    @Override
    public boolean update(User user) {
        if (user == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(user);
        return true;
    }

    @Override
    public boolean delete(User user) {
        if (user == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(user);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkLogin(User user) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from User u where u.login = :login")
                .setParameter("login", user.getLogin());
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkLoginPass(String login, String pass) throws
            HibernateException, AuthenticationException {
        if (login.trim().length() == 0 || pass.trim().length() == 0) {
            throw new AuthenticationException("Login or pass can not have " +
                    "zero length.");
        }
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from User u where u.login = :login" +
                " and u.pass = :pass")
                .setParameter("login", login)
                .setParameter("pass", pass);
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

}
