package mk.finki.ukim.repository;

import mk.finki.ukim.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    private List<Manufacturer> manufacturers;

    public ManufacturerRepository() {
        manufacturers=new ArrayList<>();

        manufacturers.add(new Manufacturer("Manu1","China","Ch"));
        manufacturers.add(new Manufacturer("Manu2","Nethrelands","Nh"));
        manufacturers.add(new Manufacturer("Manu3","Australia","Au"));
        manufacturers.add(new Manufacturer("Manu4","China","Ch"));
        manufacturers.add(new Manufacturer("Manu5","China","Ch"));


    }
    public List<Manufacturer> findAll()
    {
        return manufacturers;
    }

    public Optional<Manufacturer> getManufacturerByID(Long id)
    {
        return manufacturers.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
