package mk.finki.ukim.repository.impl;

import mk.finki.ukim.bootstrap.DataHolder;
import mk.finki.ukim.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {




    public List<Manufacturer> findAll()
    {
        return DataHolder.manufacturers;
    }

    public Optional<Manufacturer> getManufacturerByID(Long id)
    {
        return DataHolder.manufacturers.stream().filter(i->i.getId().equals(id)).findFirst();
    }
}
