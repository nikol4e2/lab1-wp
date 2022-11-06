package mk.finki.ukim.web;

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

@WebServlet(name="select-ballon-size", urlPatterns = "/selectBalloon")
public class SelectBalloonServlet extends HttpServlet {

    private SpringTemplateEngine templateEngine;
    private OrderService orderService;

    public SelectBalloonServlet(SpringTemplateEngine templateEngine, OrderService orderService) {
        this.templateEngine = templateEngine;
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());

        context.setVariable("order",orderService.getOrder());
        context.setVariable("color",req.getSession().getAttribute("color"));
        templateEngine.process("selectBalloonSize.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String size=req.getParameter("size");
        orderService.getOrder().setBalloonSize(size);
        resp.sendRedirect("/BalloonOrder");
    }
}
