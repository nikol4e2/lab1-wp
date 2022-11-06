package mk.finki.ukim.service.impl;


import mk.finki.ukim.model.Balloon;
import mk.finki.ukim.repository.BalloonRepository;
import mk.finki.ukim.service.BalloonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BallonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;

    public BallonServiceImpl(BalloonRepository balloonRepository) {
        this.balloonRepository = balloonRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }
}
