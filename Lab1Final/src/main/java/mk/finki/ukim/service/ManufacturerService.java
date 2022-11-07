package mk.finki.ukim.service;

import mk.finki.ukim.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> listAll();

    Optional<Manufacturer> findManufacturerByID(long id);
}
