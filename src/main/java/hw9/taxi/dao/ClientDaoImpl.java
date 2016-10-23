package hw9.taxi.dao;

import hw9.taxi.domain.Client;
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
@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
    private static Logger log = Logger.getLogger(ClientDao.class);

    @Autowired
    private SessionFactory factory;

    public ClientDaoImpl() {

    }

    @Override
    public Long create(Client client) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Client) session.get(Client.class, id);
    }

    @Override
    public boolean update(Client client) {
        if (client == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(client);
        return true;
    }

    @Override
    public boolean delete(Client client) {
        if (client == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(client);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExist(Client client) throws HibernateException {
        Session session = factory.getCurrentSession();

        Query query = session.createQuery("from Client c where c.name = :name and " +
                "c.surname = :surname and c.phone = :phone")
                .setParameter("name", client.getName())
                .setParameter("surname", client.getSurname())
                .setParameter("phone", client.getPhone());
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkExistExceptId(Client client, Long clientID) throws HibernateException {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Client c where c.name = :name and " +
                "c.surname = :surname and c.phone = :phone and c.id <> :clientID")
                .setParameter("name", client.getName())
                .setParameter("surname", client.getSurname())
                .setParameter("phone", client.getPhone())
                .setParameter("clientID", clientID);
        return (query.list().size() > 0 ? true : false);
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Client");
        return query.list();
    }
}
