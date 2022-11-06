package mk.finki.ukim.model;

import lombok.Data;

@Data
public class Balloon {
    String name;
    String description;

    public Balloon(String name) {
        this.name = name;
    }

    public Balloon(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
