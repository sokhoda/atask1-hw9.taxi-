package hw9.taxi.service;

import hw9.taxi.dao.ClientDao;
import hw9.taxi.domain.Client;
import hw9.taxi.exception.ClientException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by s_okhoda on 20.01.2016.
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private SessionFactory factory;
    @Autowired
    private ClientDao clientDao;

    public ClientServiceImpl() {
    }

    @Override
    public boolean createClient(String name, String surname, String phone, String address) throws ClientException {
        Session session = factory.getCurrentSession();
        Client client = new Client(name, surname, phone, address);
        if (clientDao.checkExist(client)){
            throw new ClientException("Client \n" + client + "\n exists in DB" +
                    ".");
        }
        else {
            clientDao.create(client);
            return true;
        }

    }

    @Override
    public Client read(Long id) {
        return clientDao.read(id);
    }


    @Override
    public boolean updateClient(Long id, String name, String surname, String
            phone, String address) throws ClientException {
        Session session = factory.getCurrentSession();
        Client client = new Client(name, surname, phone, address);
        if (clientDao.checkExistExceptId(client, id)){
            throw new ClientException("Client \n" + client + "\n exists in DB" +
                    ".");
        }
        else {
            Client client1 = clientDao.read(id);
            client1.setName(name);
            client1.setSurname(surname);
            client1.setPhone(phone);
            client1.setAddress(address);
            clientDao.update(client1);
            return true;
        }
    }

    @Override
    public Integer getClientsTotPages(int portionSize)  throws ClientException {
        if (portionSize <= 0) {
            throw new ClientException("Negative portion size.");
        }
        List clients = (List<Client>)clientDao.findAll();
        return  (clients.size() == 0 ? 1 :(int) Math.ceil
                (clients.size() / (double)portionSize));
    }

    @Override
    public List showClientsByPortion(int portionSize, int cnt) throws ClientException {
        if (portionSize <= 0) {
            throw new ClientException("Negative portion size.");
        }
        Session session = factory.getCurrentSession();
            Query query = session.createQuery("from Client");
            query.setFirstResult((cnt - 1) * portionSize);
            query.setMaxResults(portionSize);
            return query.list();
    }

    @Override
    public List showClientsGtSum(int sum) {
        return null;
    }

    @Override
    public List showClientsLastMonth() {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return  (List<Client>) clientDao.findAll();
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }
}
