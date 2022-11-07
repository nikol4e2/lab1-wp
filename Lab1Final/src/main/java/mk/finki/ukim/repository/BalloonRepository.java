package mk.finki.ukim.repository;

import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.model.Manufacturer;
import mk.finki.ukim.model.exceptions.BallonAlreadyExistsException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    List<Balloon> balloons=new ArrayList<>();

    public BalloonRepository() {
        balloons.add(new Balloon("ballon 1","ballon one"));
        balloons.add(new Balloon("ballon 2","ballon two"));
        balloons.add(new Balloon("ballon 3","ballon three"));
        balloons.add(new Balloon("ballon 4","ballon four"));
        balloons.add(new Balloon("ballon 5","ballon five"));
        balloons.add(new Balloon("ballon 6","ballon six"));
        balloons.add(new Balloon("ballon 7","ballon seven"));
        balloons.add(new Balloon("ballon 8","ballon egiht"));
        balloons.add(new Balloon("ballon 9","ballon nine"));
        balloons.add(new Balloon("ballon 10","ballon ten"));


    }

    public List<Balloon> findAllBalloons(){
        return balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text)
    {
        return balloons.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }
    public Optional<Balloon> findBallonByID(Long id)
    {
        return balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Balloon addBalloon(Balloon balloon)
    {
        if(findBallonByID(balloon.getId()).isPresent())
        {
            throw new BallonAlreadyExistsException();
        }else
        {
            balloons.add(balloon);
            return balloon;
        }
    }
    public void deleteBalloonById(long id)
    {
        balloons.removeIf(i->i.getId().equals(id));
    }

    public Optional<Balloon> findById(long id)
    {
       return balloons.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public Optional<Balloon> save(String name,String description, Manufacturer manufacturer)
    {
        balloons.removeIf(i->i.getName().equals(name));

        Balloon balloon=new Balloon(name, description,manufacturer);
        balloons.add(balloon);
        return Optional.of(balloon);
    }
}
