package br.com.midup.tde_midup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Classe de teste para Score
public class ScoreTest {

    private Score player1Score;
    private Score player2Score;

    @BeforeEach
    public void setUp() {
        player1Score = new Score("Player 1");
        player2Score = new Score("Player 2");
    }

    @Test
    public void testInitialScoreIsZero() {
        assertEquals(0, player1Score.getScore(), "Placar inicial deve ser zero");
        assertEquals(0, player2Score.getScore(), "Placar inicial deve ser zero");
    }

    @Test
    public void testIncrementScore() {
        player1Score.inc();
        assertEquals(1, player1Score.getScore(), "O placar deve ser incrementado corretamente");
        player1Score.inc();
        assertEquals(2, player1Score.getScore(), "O placar deve somar corretamente múltiplos incrementos");
    }

    @Test
    public void testIndependentScores() {
        player1Score.inc();
        player1Score.inc();
        player2Score.inc();

        assertEquals(2, player1Score.getScore(), "Placar do Player 1 deve estar correto");
        assertEquals(1, player2Score.getScore(), "Placar do Player 2 deve estar correto");
    }

}
