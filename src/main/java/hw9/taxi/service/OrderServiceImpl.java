package hw9.taxi.service;


import hw9.taxi.domain.Client;
import hw9.taxi.exception.OrderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by s_okhoda on 03.03.2016.
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    public OrderServiceImpl() {
    }

    @Override
    public boolean createOrder(Client client, Double amount, String addressFrom, String addressTo) throws OrderException {
        return false;
    }

    @Override
    public void editOrder(Long id, Client client, String amount, String addressFrom, String addressTo) {

    }
}
