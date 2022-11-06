package mk.finki.ukim.web;

import mk.finki.ukim.service.BalloonService;
import mk.finki.ukim.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "balloon-list-servlet",urlPatterns = "")
public class BalloonListServlet extends HttpServlet {

    private final BalloonService balloonService;
    private final SpringTemplateEngine engine;
    private final OrderService orderService;

    public BalloonListServlet(BalloonService balloonService, SpringTemplateEngine engine, OrderService orderService) {
        this.balloonService = balloonService;
        this.engine = engine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext=new WebContext(req,resp, req.getServletContext());
        webContext.setVariable("balloons",balloonService.listAll());
        engine.process("listBallons.html",webContext,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String color=req.getParameter("color");
        orderService.placeOrder(color,"prazno","prazna");

        req.getSession().setAttribute("color",color);

        resp.sendRedirect("/selectBalloon");

    }
}
