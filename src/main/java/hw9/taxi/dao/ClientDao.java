package hw9.taxi.dao;

import hw9.taxi.domain.Client;
import org.hibernate.HibernateException;

import java.util.List;

/**
 * Created by s_okhoda on 03.03.2016.
 */
public interface ClientDao {
    Long create(Client cpu);
    Client read(Long id);
    boolean update(Client client);
    boolean delete(Client client);
    boolean checkExist(Client client) throws HibernateException;
    boolean checkExistExceptId(Client client, Long clientID) throws HibernateException;
    List findAll();
}
