package nl.hartwig.actin.service;

import lombok.Getter;
import nl.hartwig.actin.model.ComputerPlayer;
import nl.hartwig.actin.model.Player;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Service
public class AuditService {

    @Getter
    private final Map<Integer, String> summaryMap = new ConcurrentHashMap<>();
    private final AtomicInteger roundNum = new AtomicInteger(0);

    public Consumer<Player> audit = player -> {
        if (Objects.isNull(player)) {
            summaryMap.put(roundNum.incrementAndGet(), "It was a draw.");
        } else {
            summaryMap.put(roundNum.incrementAndGet(),
                    String.format("Player: %s won with winMove: %s",
                            player.getName(),
                            player.getLastMove()));
        }
    };

    public void summarize() {
        summaryMap.forEach((key, value) -> System.out.printf("Round %d: %s%n", key, value));
        long numberOfComputerWins = summaryMap.values()
                .stream()
                .filter(x -> x.contains(ComputerPlayer.getComputerName()))
                .count();
        long numberOfDraws = summaryMap.values()
                .stream()
                .filter(x -> x.contains("draw"))
                .count();
        long numberOfUserWins = summaryMap.size() - numberOfDraws - numberOfComputerWins;
        System.out.println("Number of rounds user wins: " + numberOfUserWins);
        System.out.println("Number of rounds computer wins: " + numberOfComputerWins);
        System.out.println("Number of rounds draw: " + numberOfDraws);
        System.out.printf("=========================== YOU %s =============================\n\n",
                ((numberOfUserWins > numberOfComputerWins) ? "WON. CONGRATS !" : "LOST. TRY AGAIN"));
    }
}
