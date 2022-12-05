package mk.finki.ukim.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {
    String name;
    String description;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Manufacturer manufacturer;

    public Balloon(String name) {
        this.name = name;
    }

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
        id=(long)(Math.random()*1000);
    }
    public Balloon(String name,String description,Manufacturer manufacturer)
    {
        this.name = name;
        this.description = description;

        this.manufacturer=manufacturer;
    }



    public Balloon() {

    }
}
