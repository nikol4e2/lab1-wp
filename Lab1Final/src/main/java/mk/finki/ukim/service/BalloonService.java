package mk.finki.ukim.service;

import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BalloonService {

     List<Balloon> listAll();
     List<Balloon> searchByNameOrDescription(String text);
     Balloon addBalloon(Balloon balloon);
      void deleteById(long id);
      Optional<Balloon> findByID(Long id);
      Optional<Balloon> saveBalloon(String name, String description, Long manufacturerid);


}
