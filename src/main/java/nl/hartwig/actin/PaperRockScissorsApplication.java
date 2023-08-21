package nl.hartwig.actin;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.hartwig.actin.exception.ExitCommandException;
import nl.hartwig.actin.model.Player;
import nl.hartwig.actin.service.AuditService;
import nl.hartwig.actin.service.GameService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class PaperRockScissorsApplication {

    private final GameService prsGame;
    private final AuditService auditService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PaperRockScissorsApplication.class, args);
        context.close();
    }

    @PostConstruct
    public void play() {
        while (true) {
            try {
                Optional<Player> winner = prsGame.start();
                auditService.audit.accept(winner.orElse(null));
            } catch (final ExitCommandException exit) {
                log.info("Game finished. Goodbye.");
                return;
            } catch (final Exception e) {
                log.error(e.getMessage());
            }
        }
    }

}
