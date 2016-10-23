package hw9.taxi.controller;

import hw9.taxi.domain.Client;
import hw9.taxi.exception.OrderException;
import hw9.taxi.service.ClientService;
import hw9.taxi.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static hw9.taxi.service.AuthorizationServiceImpl.String2Double;
import static hw9.taxi.service.AuthorizationServiceImpl.String2Long;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@SessionAttributes("id")
public class CreateOrderController {
    public static final Logger log = Logger.getLogger(CreateOrderController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    private final Long CLIENT_INI_ID = 5L;


    @RequestMapping(value = "/selectAllClients.json", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody List<Client> selectAllClients() {
        return  clientService.findAll();
    }

    @RequestMapping(value = "/registerOrder.html", method = RequestMethod.GET)
    public String registerOrder(HttpServletResponse response, Model model) throws IOException {
        log.info("CreateOrderController /registerOrder.html");
        model.addAttribute("selectedClientId" , CLIENT_INI_ID);
        return "createOrder";
    }

    @RequestMapping(value = "/selectClient.json", method = RequestMethod.GET,
            produces = "application/json")
    public @ResponseBody Client selectClient(
            @RequestParam ("id") String clientID) {
        return clientService.read(String2Long(clientID));
    }

    @RequestMapping(value = "/createOrder.html", method = RequestMethod.GET,  produces = "application/json")
    public @ResponseBody String createOrder(
            @RequestParam ("clientID") String clientID,
            @RequestParam ("amount") String amount,
            @RequestParam ("addressFrom") String addressFrom,
            @RequestParam ("addressTo") String addressTo, Model model) throws
            OrderException{

//        log.info("CreateOrderController /createOrder.json");
//        try {
//            if (clientService.createClient(name, surname, phone, address)) {
//                return new MessageResult("Client '" + name + ", " + surname + "' successfully created.", "green");
//            } else {
//                throw new ClientException("Failed to create client.");
//            }
//        } catch (Exception e) {
//            return new MessageResult(e.getMessage(), "red");
//        }

        Client client = clientService.read(String2Long(clientID));
        if (orderService.createOrder(client, String2Double(amount), addressFrom,
                addressTo)){
            return "Order '" + client.getName() + ", " + amount + ", " + addressFrom
                    + ", " + addressTo + "' successfully created.";
        }
        else {
            return "Failed to create order.";
        }
    }


}
