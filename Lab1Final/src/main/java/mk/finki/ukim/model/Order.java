package mk.finki.ukim.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Order {
    String balloonColor ;
    String balloonSize;
    String username;
    String userAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    public Order(String balloonColor, String balloonSize) {
        this.balloonColor = balloonColor;
        this.balloonSize = balloonSize;


    }

    public Order(String balloonColor)
    {
        this.balloonColor = balloonColor;
        balloonSize="small";
        username="";
        userAddress="";


    }

    public Order() {

    }
}
