package mk.finki.ukim.service.impl;


import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.exceptions.ManufacturerDoesntExistException;
import mk.finki.ukim.repository.jpa.BalloonRepository;
import mk.finki.ukim.service.BalloonService;
import mk.finki.ukim.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BallonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerService manufacturerService;

    public BallonServiceImpl(BalloonRepository balloonRepository, ManufacturerService service) {
        this.balloonRepository = balloonRepository;
        this.manufacturerService=service;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text,text);
    }

    @Override
    public Balloon addBalloon(Balloon balloon) {
        return balloonRepository.save(balloon);
    }

    @Override
    public void deleteById(long id) {
        balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findByID(Long id)
    {
        return balloonRepository.findById(id);
    }

    @Override
    public  Optional<Balloon> saveBalloon(String name, String description, Long manufacturerid) {
        Manufacturer manufacturer=manufacturerService.findManufacturerByID(manufacturerid).orElseThrow(()->new ManufacturerDoesntExistException());
            return Optional.of(balloonRepository.save(new Balloon(name,description,manufacturer)));

    }
}
