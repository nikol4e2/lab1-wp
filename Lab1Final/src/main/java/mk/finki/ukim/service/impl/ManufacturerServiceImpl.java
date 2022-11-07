package mk.finki.ukim.service.impl;

import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.repository.ManufacturerRepository;
import mk.finki.ukim.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> listAll() {
        return manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findManufacturerByID(long id) {
       return manufacturerRepository.getManufacturerByID(id);
    }
}
