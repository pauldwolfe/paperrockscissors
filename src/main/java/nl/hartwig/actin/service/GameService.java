package nl.hartwig.actin.service;

import nl.hartwig.actin.model.Player;

import java.util.Optional;

@FunctionalInterface
public interface GameService {

    Optional<Player> start();

}
