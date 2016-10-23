package hw9.taxi.service;

import hw9.taxi.dao.UserDao;
import hw9.taxi.domain.User;
import hw9.taxi.exception.AuthenticationException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by s_okhoda on 03.03.2016.
 */
@Transactional
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    @Autowired
    private SessionFactory factory;

    @Autowired
    private UserDao userDao;

    public AuthorizationServiceImpl() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Override
    public boolean register(String login, String id, String pass) throws AuthenticationException {
        User user = new User(login, id, pass);
        if (userDao.checkLogin(user)){
            throw new AuthenticationException("User with login='" + login +
                    "' exists in DB.");
        }
        else {
            userDao.create(user);
            return true;
        }
    }

    @Override
    public boolean checkLoginPass(String login, String pass) throws AuthenticationException {
        return userDao.checkLoginPass(login, pass);
    }

    public static Date String2Date(String dateStr)
            throws
            ParseException {
        if (dateStr == null || dateStr.length() == 0){
            return  null;
        }
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse(dateStr);
        return date;
    }

    public static Integer String2Integer(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0;
        }
        return Integer.parseInt(str);
    }

    public static Long String2Long(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0L;
        }
        return Long.parseLong(str);
    }

    public static Double String2Double(String str) throws NumberFormatException{
        if (str == null) {
            return null;
        }
        if (str.length() == 0){
            return 0.0;
        }
        return Double.parseDouble(str);
    }
}
