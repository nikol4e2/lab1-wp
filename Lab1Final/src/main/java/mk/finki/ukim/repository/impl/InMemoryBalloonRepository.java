package mk.finki.ukim.repository.impl;

import mk.finki.ukim.bootstrap.DataHolder;
import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.exceptions.BallonAlreadyExistsException;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {



    public InMemoryBalloonRepository() {


    }

    public List<Balloon> findAllBalloons(){
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text)
    {
        return DataHolder.balloons.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }
    public Optional<Balloon> findBallonByID(Long id)
    {
        return DataHolder.balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Balloon addBalloon(Balloon balloon)
    {
        if(findBallonByID(balloon.getId()).isPresent())
        {
            throw new BallonAlreadyExistsException();
        }else
        {
            DataHolder.balloons.add(balloon);
            return balloon;
        }
    }
    public void deleteBalloonById(long id)
    {
        DataHolder.balloons.removeIf(i->i.getId().equals(id));
    }

    public Optional<Balloon> findById(long id)
    {
       return DataHolder.balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(String name,String description, Manufacturer manufacturer)
    {
        DataHolder.balloons.removeIf(i->i.getName().equals(name));

        Balloon balloon=new Balloon(name, description,manufacturer);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }
}
