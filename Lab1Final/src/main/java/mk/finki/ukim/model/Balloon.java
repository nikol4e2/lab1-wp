package mk.finki.ukim.model;

import lombok.Data;

@Data
public class Balloon {
    String name;
    String description;
    Long id;
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
        id=(long)(Math.random()*1000);
        this.manufacturer=manufacturer;
    }



}
