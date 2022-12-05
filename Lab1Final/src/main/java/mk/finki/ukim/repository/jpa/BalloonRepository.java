package mk.finki.ukim.repository.jpa;

import mk.finki.ukim.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon,Long> {

    List<Balloon> findAllByNameOrDescription(String name,String description);
}
