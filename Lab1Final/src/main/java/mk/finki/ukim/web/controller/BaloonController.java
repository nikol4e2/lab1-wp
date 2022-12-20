package mk.finki.ukim.web.controller;

import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.User;
import mk.finki.ukim.model.exceptions.ManufacturerDoesntExistException;
import mk.finki.ukim.service.BalloonService;
import mk.finki.ukim.service.ManufacturerService;
import mk.finki.ukim.service.OrderService;
import mk.finki.ukim.service.impl.BallonServiceImpl;
import org.h2.engine.Mode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller

public class BaloonController {

    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;
    private final OrderService orderService;

    public BaloonController(BalloonService balloonService,ManufacturerService manufacturerService,OrderService orderService) {
        this.balloonService = balloonService;
        this.manufacturerService=manufacturerService;
        this.orderService=orderService;
    }

    @GetMapping("/balloons")
    public String getBallonPage(@RequestParam(required = false) String error, Model model)
    {
            model.addAttribute("balloons",balloonService.listAll());
            model.addAttribute("manufacturers",manufacturerService.listAll());
            return "listBallons.html";
    }

    @GetMapping("/balloons/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addBalloonPage(Model model)
    {
        List<Manufacturer> manufacturers=manufacturerService.listAll();
        model.addAttribute("manufacturers",manufacturers);
        return "add-balloon";
    }
    @PostMapping("/balloons/edit-form/{id}")
    public String editBalloonPage(@PathVariable Long id,Model model)
    {
        if(balloonService.findByID(id).isPresent()){
            Balloon balloon=balloonService.findByID(id).get();
            model.addAttribute("balloon",balloon);
            List<Manufacturer> manufacturers=manufacturerService.listAll();
            model.addAttribute("manufacturers",manufacturers);

            return "add-balloon";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @PostMapping("/balloons/add")
    public String saveBallon(@RequestParam String name,@RequestParam String description, @RequestParam Long manufacturerID)
    {
            if(manufacturerService.findManufacturerByID(manufacturerID).isPresent())
            {
                    Manufacturer manufacturer=manufacturerService.findManufacturerByID(manufacturerID).get();
                    balloonService.saveBalloon(name,description,manufacturerID);
                    return "redirect:/balloons";

            }
            return "redirect:/balloons?error=ManufacturerDoesntExist";



    }
    @DeleteMapping("/balloons/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBalloon(@PathVariable Long id)
    {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/orders")
    public String getOrdersPage(Model model)
    {
        model.addAttribute("orders",orderService.getAllOrders());
        model.addAttribute("activeOrder",orderService.getOrder());
        return "userOrders";
    }


    @GetMapping("/access_denied")
    public String accessDeniedPage(Model model)
    {
        return "access_denied";
    }

    @GetMapping("")
    public String getHomePage(Model model)
    {
        model.addAttribute("balloons",balloonService.listAll());
        return "listBallons";
    }

    @PostMapping("")
    public String choseBalloonColor(@RequestParam String color, HttpServletRequest request)
    {
        orderService.placeOrder(color,"prazno","prazno");
        request.getSession().setAttribute("color",color);
       // User u=(User)request.getSession().getAttribute("activeUser");
      //  u.addNewCart();
        return "redirect:/selectBalloon";

    }

    @GetMapping("/selectBalloon")
    public String getSelectBallonPage(HttpServletRequest request,Model model)
    {
        model.addAttribute("order",orderService.getOrder());
        model.addAttribute("color",request.getSession().getAttribute("color"));
        return "selectBalloonSize";
    }

    @PostMapping("/selectBalloon")
    public String selectBalloon(@RequestParam String size,HttpServletRequest request)
    {
        orderService.getOrder().setBalloonSize(size);
        return "redirect:/BalloonOrder";
    }

    @GetMapping("/BalloonOrder")
    public String getBalloonOrderPage(Model model,HttpServletRequest request)
    {
        request.getSession().setAttribute("size",orderService.getOrder().getBalloonSize());

        return "deliveryInfo";
    }

    @PostMapping("/BalloonOrder")
    public String inputClientInformation(@RequestParam String clientName,@RequestParam String clientAddress,HttpServletRequest request)
    {
        orderService.getOrder().setUserAddress(clientAddress);
        orderService.getOrder().setUsername(clientName);
        request.getSession().setAttribute("address",clientAddress);

        request.getSession().setAttribute("name",clientName);
        return "redirect:/ConfirmationInfo";
    }


    @GetMapping("/ConfirmationInfo")
    public String getConfirmationInfoPage(HttpServletRequest request)
    {
        request.getSession().setAttribute("ip",request.getRemoteAddr());
        request.getSession().setAttribute("browser",request.getHeader("User-Agent"));
        return "confirmationInfo.html";
    }


}
