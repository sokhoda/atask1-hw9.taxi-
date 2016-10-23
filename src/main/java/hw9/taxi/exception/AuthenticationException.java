package hw9.taxi.exception;

/**
 * Created by s_okhoda on 20.01.2016.
 */
public class AuthenticationException extends  Exception{
    public AuthenticationException(String message) {
        super(message);
    }

    public String getMessage(){
        return "AuthenticationException: " + super.getMessage();
    }

}
