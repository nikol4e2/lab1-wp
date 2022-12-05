package mk.finki.ukim.bootstrap;

import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons=new ArrayList<>();
    public static List<Manufacturer> manufacturers=new ArrayList<>();
    public static List<Order> orders=new ArrayList<>();



    @PostConstruct
    public void init()
    {
        balloons.add(new Balloon("balloon 1","balloon one"));
        balloons.add(new Balloon("balloon 2","balloon two"));
        balloons.add(new Balloon("balloon 3","balloon three"));
        balloons.add(new Balloon("balloon 4","balloon four"));
        balloons.add(new Balloon("balloon 5","balloon five"));
        balloons.add(new Balloon("balloon 6","balloon six"));
        balloons.add(new Balloon("balloon 7","balloon seven"));
        balloons.add(new Balloon("balloon 8","balloon eight"));
        balloons.add(new Balloon("balloon 9","balloon nine"));
        balloons.add(new Balloon("balloon 10","balloon ten"));


        manufacturers.add(new Manufacturer("Manu1","China","Ch"));
        manufacturers.add(new Manufacturer("Manu2","Nethrelands","Nh"));
        manufacturers.add(new Manufacturer("Manu3","Australia","Au"));
        manufacturers.add(new Manufacturer("Manu4","China","Ch"));
        manufacturers.add(new Manufacturer("Manu5","China","Ch"));
    }
}
