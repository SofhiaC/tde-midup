package br.com.midup.tde_midup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//classe de teste pra ver se a pontuação está funcionando corretamente
public class ScoreTest {
    //instancia os placares dos dois jogadores
    private Score player1Score;
    private Score player2Score;

    //método executado antes de cada teste, garantindo placares zerados e independentes
    @BeforeEach
    public void setUp() {
        player1Score = new Score("Player 1");
        player2Score = new Score("Player 2");
    }

    @Test //verifica se o placar inicial é zero
    public void testInitialScoreIsZero() {
        assertEquals(0, player1Score.getScore(), "Placar inicial deve ser zero");
        assertEquals(0, player2Score.getScore(), "Placar inicial deve ser zero");
    }

    @Test //verifica se o incremento do placar está correto
    public void testIncrementScore() {
        player1Score.inc(); //+1
        assertEquals(1, player1Score.getScore(), "O placar deve ser incrementado corretamente");
        player1Score.inc(); //+1
        assertEquals(2, player1Score.getScore(), "O placar deve somar corretamente múltiplos incrementos");
    }

    @Test //verifica se os placares dos jogadores são independentes
    public void testIndependentScores() {
        player1Score.inc(); //+1 pro player 1
        player1Score.inc(); //+1 pro player 1
        player2Score.inc(); //+1 pro player 2

        assertEquals(2, player1Score.getScore(), "Placar do Player 1 deve estar correto");
        assertEquals(1, player2Score.getScore(), "Placar do Player 2 deve estar correto");
    }

}
