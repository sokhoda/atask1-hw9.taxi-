package hw9.taxi.service;

import hw9.taxi.exception.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by s_okhoda on 03.03.2016.
 */
@Transactional
@Service
public interface AuthorizationService {
    boolean register(String login, String id, String pass) throws
            AuthenticationException;
    boolean checkLoginPass(String login, String pass) throws
            AuthenticationException;
}
