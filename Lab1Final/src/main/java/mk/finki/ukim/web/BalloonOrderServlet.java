package mk.finki.ukim.web;

import mk.finki.ukim.model.Order;
import mk.finki.ukim.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "balloon-order", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine engine;

    public BalloonOrderServlet(OrderService orderService, SpringTemplateEngine engine) {
        this.orderService = orderService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("size",orderService.getOrder().getBalloonSize());
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
        webContext.setVariable("size",orderService.getOrder().getBalloonSize());
        webContext.setVariable("color",orderService.getOrder().getBalloonColor());
        webContext.setVariable("order",orderService.getOrder());
        engine.process("deliveryInfo.html",webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("clientName");
        String adress=req.getParameter("clientAddress");
        orderService.getOrder().setClientAddress(adress);
        orderService.getOrder().setClientName(name);
        req.getSession().setAttribute("adress",adress);
        req.getSession().setAttribute("name",name);
        resp.sendRedirect("/ConfirmationInfo");
    }
}
