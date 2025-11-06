package br.com.midup.tde_midup;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import staticorg.junit.jupiter.api.Assertions.assertEquals;

public class BallTest {

     @Test
    void deveRebaterAposColisao(){

        double[] limites = {0, 500};

        Player jogador1 = new Player(100, 250, 20, 100, Color.BLUE, "Player 1", limites, 0.2);
        Ball bola = new Ball(110, 200, 10, 10, Color.WHITE, 0.1);

        boolean colidiu = bola.checkCollision(jogador1);
        assertTrue(colidiu, "A bola deve colidir com o jogador");

        bola .onPlayerCollision("jogador1");

        assertEquals(Math.abs(bola.getSpeed()), bola.getSpeed(), "Após colisão, a bola deve ser movida para direita;");

    }
    
}
