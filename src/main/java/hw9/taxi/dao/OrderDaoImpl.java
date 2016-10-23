package hw9.taxi.dao;

import hw9.taxi.domain.Order;
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
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {
    private static Logger log = Logger.getLogger(OrderDao.class);

    @Autowired
    private SessionFactory factory;

    public OrderDaoImpl() {

    }

    @Override
    public Long create(Order order) throws HibernateException {
        Session session = factory.getCurrentSession();
        return (Long) session.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order read(Long id) {
        if (id == null) {
            return null;
        }
        Session session = factory.getCurrentSession();
        return (Order) session.get(Order.class, id);
    }

    @Override
    public boolean update(Order order) {
        if (order == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.update(order);
        return true;
    }

    @Override
    public boolean delete(Order order) {
        if (order == null){
            return false;
        }
        Session session = factory.getCurrentSession();
        session.delete(order);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List findAll() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Order");
        return query.list();
    }

}
