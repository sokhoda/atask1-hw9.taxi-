package hw9.taxi.dao;

import hw9.taxi.domain.User;
import hw9.taxi.exception.AuthenticationException;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by s_okhoda on 03.03.2016.
 */
public interface UserDao {
    Long create(User user);
    User read(Long id);
    boolean update(User user);
    boolean delete(User user);
    boolean checkLogin(User user) throws HibernateException;
    boolean checkLoginPass(String login, String pass) throws
            HibernateException, AuthenticationException;
    List findAll();
}
