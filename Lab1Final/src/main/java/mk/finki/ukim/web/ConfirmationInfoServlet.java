package mk.finki.ukim.web;

import mk.finki.ukim.service.OrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;
import java.io.IOException;

@WebServlet(name = "confirmation-servlet", urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {

    private final OrderService orderService;
    private final SpringTemplateEngine engine;

    public ConfirmationInfoServlet(OrderService orderService, SpringTemplateEngine engine) {
        this.orderService = orderService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext=new WebContext(req,resp,req.getServletContext());
     //   webContext.setVariable("color",orderService.getOrder().getBalloonColor());
    //    webContext.setVariable("size",orderService.getOrder().getBalloonSize());
    //    webContext.setVariable("name",orderService.getOrder().getClientName());
    //    webContext.setVariable("adress",orderService.getOrder().getClientAddress());
        webContext.setVariable("ip",req.getRemoteAddr());
        webContext.setVariable("browser",req.getHeader("User-Agent"));
        webContext.setVariable("order",orderService.getOrder());;
        req.getSession().setAttribute("name",orderService.getOrder().getUsername());
        req.getSession().setAttribute("adress",orderService.getOrder().getUserAddress());

        engine.process("confirmationInfo.html",webContext,resp.getWriter());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
