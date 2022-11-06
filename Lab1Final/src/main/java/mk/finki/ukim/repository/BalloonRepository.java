package mk.finki.ukim.repository;

import mk.finki.ukim.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
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
}
