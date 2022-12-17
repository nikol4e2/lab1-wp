package mk.finki.ukim.web.controller;

import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.exceptions.ManufacturerDoesntExistException;
import mk.finki.ukim.service.BalloonService;
import mk.finki.ukim.service.ManufacturerService;
import mk.finki.ukim.service.OrderService;
import mk.finki.ukim.service.impl.BallonServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


}
