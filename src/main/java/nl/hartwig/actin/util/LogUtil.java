package nl.hartwig.actin.util;

import nl.hartwig.actin.model.ComputerPlayer;
import nl.hartwig.actin.model.Player;

public final class LogUtil {

    public static void whatIsYourMove() {
        System.out.println("\nPlease input your next move Rock(r), Paper(p) or Scissors(s), or Quit(q) if you want to quit:");
    }

    public static void areYouReady() {
        System.out.println("\nPlease enter your name:");
    }

    public static void logPlayerNameAndMove(final String name, final String move) {
        System.out.printf("\nplayer's name: %s chooses %s", name, move);
    }

    public static void congratsWinner(Player winner) {
        System.out.printf("\n%s this round.\n", (winner.getName().equalsIgnoreCase(ComputerPlayer.getComputerName()))
                ? "Sorry You LOSE !!!" : "Congrats you WIN !!!");
    }

    public static void itsDraw() {
        System.out.println("\nIt's a DRAW !!!");
    }

    public static void reportSummaryAndExit() {
        System.out.println("""
                ======================================================================
                Game ends, it was a good game !
                Summary:
                """);
    }

    private LogUtil() {

    }
}
