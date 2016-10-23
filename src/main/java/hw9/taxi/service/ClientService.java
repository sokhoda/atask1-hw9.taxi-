package hw9.taxi.service;

import hw9.taxi.domain.Client;
import hw9.taxi.exception.ClientException;

import java.util.*;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public interface ClientService {

    boolean createClient(String name, String surname, String phone, String
            address) throws ClientException;
    public boolean updateClient(Long id, String name, String surname, String
            phone, String address) throws ClientException;
    Client read(Long id);
    Integer getClientsTotPages(int size) throws ClientException;
    List showClientsByPortion(int portionSize, int cnt) throws ClientException;
    List showClientsGtSum(int sum);
    List showClientsLastMonth();

    List<Client> findAll();

}
