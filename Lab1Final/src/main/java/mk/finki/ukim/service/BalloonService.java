package mk.finki.ukim.service;

import mk.finki.ukim.model.Balloon;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BalloonService {

     List<Balloon> listAll();
     List<Balloon> searchByNameOrDescription(String text);

}
