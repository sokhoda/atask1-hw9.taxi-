package hw9.taxi.controller;

import hw9.taxi.domain.Client;
import hw9.taxi.exception.ClientException;
import hw9.taxi.service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

import java.util.List;

import static hw9.taxi.service.AuthorizationServiceImpl.String2Integer;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/16/15
 */
@Controller
@SessionAttributes("id")
public class PortionClientServlet {
    public static final Logger log = Logger.getLogger(PortionClientServlet.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients.html", method = RequestMethod.GET)
    public String clients(
            @RequestParam("listClientsByPortionPortion") String sPortion,
            Model model) throws ServletException {
        log.info("/clients.html PortionClientServlet");
        try {
            Integer sPortionC = String2Integer(sPortion);
            Integer totPagesC = clientService.getClientsTotPages(sPortionC);
            model.addAttribute("sPortion", sPortionC);
            model.addAttribute("cnt", 1);
            model.addAttribute("totPages", totPagesC);
            return "clients";
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "/showClientsByPortion.json", method = RequestMethod
            .GET,  produces = "application/json")
    public @ResponseBody List<Client> showClientsByPortion(
            @RequestParam("portionSize") String sPortion,
            @RequestParam("cnt") String cnt,
            Model model) throws ServletException {
        log.info("/showClientsByPortion.json PortionClientServlet");
        try {
            Integer sPortionC = String2Integer(sPortion);
            int cntC = Integer.parseInt(cnt);
            List<Client> clientList = clientService.showClientsByPortion
                    (sPortionC, cntC);
            return clientList;
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

    @RequestMapping(value = "/showCurrentClientPortion.html", method =
            RequestMethod.GET)
    public String showCurrentClientPortion(
            @RequestParam("portionSize") String sPortion,
            @RequestParam("cnt") String cnt,
            @RequestParam("totPages") String totPages,
            Model model) throws ServletException {
        log.info("/showCurrentClientPortion.html PortionClientServlet");
        try {
            Integer sPortionC = String2Integer(sPortion);
            int cntC = Integer.parseInt(cnt);
            int totPagesC = Integer.parseInt(totPages);
            List clientList = clientService.showClientsByPortion(sPortionC,
                    cntC);

            model.addAttribute("cnt", cntC);
            model.addAttribute("totPages", totPagesC);
            model.addAttribute("clientList", clientList);
            model.addAttribute("sPortion", sPortionC);
            return "clients";
        }
        catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }

}
