package hw9.taxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hw9.taxi.domain.Client;
import hw9.taxi.domain.MessageResult;
import hw9.taxi.exception.ClientException;
import hw9.taxi.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletException;

import static hw9.taxi.service.AuthorizationServiceImpl.String2Long;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@EnableWebMvc
@SessionAttributes("id")
public class CreateClientServlet {
    public static final Logger log = Logger.getLogger(CreateClientServlet.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/createClient.json", method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    MessageResult createClient(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address, Model model)  {
        log.info("CreateClientServlet /createClient.json controller");
        try {
            if (clientService.createClient(name, surname, phone, address)) {
                return new MessageResult("Client '" + name + ", " + surname + "' successfully created.", "green");
            } else {
                throw new ClientException("Failed to create client.");
            }
        } catch (Exception e) {
            return new MessageResult(e.getMessage(), "red");
        }
    }

    @RequestMapping(value = "/registerClient.html", method = RequestMethod.GET)
    public String registerClient() {
        log.info("CreateClientServlet /registerClient.html controller");
        return "registerClient";
    }

    @RequestMapping(value = "/editClient.html", method = RequestMethod.GET)
    public String editClient(@RequestParam("cid") String id,
                             @RequestParam("portionSize") String sPortion,
                             @RequestParam("cnt") String cnt,
                             @RequestParam("totPages") String totPages,
                             Model model)
            throws ServletException {
        log.info("CreateClientServlet /editClient.html controller");
        try {
            ObjectMapper mapper = new ObjectMapper();

            Long clientId = String2Long(id);
            Client client = clientService.read(clientId);
            model.addAttribute("client", mapper.writeValueAsString(client)
                    .replace("'", "&#39;"));
            model.addAttribute("cnt", cnt);
            model.addAttribute("totPages", totPages);
            model.addAttribute("sPortion", sPortion);
            return "editClient";
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateClient.json", method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    MessageResult updateClient(@RequestParam("cid") String id,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address, Model model)  {
        log.info("CreateClientServlet /updateClient.json controller");
        try {
            Long clientId = String2Long(id);
            if (clientService.updateClient(clientId, name, surname, phone,
                    address)) {
                return new MessageResult("Client '" + name + ", " + surname +
                        "' successfully updated.", "green");
            } else {
                throw new ClientException("Failed to update client.");
            }
        } catch (Exception e) {
            return new MessageResult(e.getMessage(), "red");
        }
    }
}
