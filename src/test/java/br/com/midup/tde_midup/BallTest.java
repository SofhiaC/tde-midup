package br.com.midup.tde_midup;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BallTest{

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

    
    @Test
    public void testRebateNoTeto() {
        Ball bola = new Ball(100, 1, 10, 10, Color.RED, 0.5);

        // Força direção Y para cima (negativa)
        setPrivateField(bola, "directionY", -0.5);

        // Simula colisão com o teto
        bola.onWallCollision("Top");

        double novaDirecaoY = getPrivateField(bola, "directionY");
        assertTrue(novaDirecaoY > 0, "A bola deve inverter e mover-se para baixo após colisão com o teto.");
    }


    private void setPrivateField(Ball bola, String fieldName, double value) {
        try {
            var field = Ball.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(bola, value);
        } catch (Exception e) {
            fail("Erro ao ajustar o campo privado " + fieldName + ": " + e.getMessage());
        }
    }

    private double getPrivateField(Ball bola, String fieldName) {
        try {
            var field = Ball.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getDouble(bola);
        } catch (Exception e) {
            fail("Erro ao ler o campo privado " + fieldName + ": " + e.getMessage());
            return 0;
        }
    }


    @Test
    public void testRebateNoChao() {
        Ball bola = new Ball(100, 1, 10, 10, Color.RED, 0.5);

        setPrivateField(bola, "directionY", 0.5);

        bola.onWallCollision("Bottom");

        double novaDirecaoY = getPrivateField(bola, "directionY");
        assertTrue(novaDirecaoY < 0, "A bola deve inverter e mover-se para cima após colisão com o chão.");
    }

    private void setPrivateField(Ball bola, String fieldName, double value) {
        try {
            var field = Ball.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(bola, value);
        } catch (Exception e) {
            fail("Erro ao ajustar o campo privado " + fieldName + ": " + e.getMessage());
        }
    }

    private double getPrivateField(Ball bola, String fieldName) {
        try {
            var field = Ball.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getDouble(bola);
        } catch (Exception e) {
            fail("Erro ao ler o campo privado " + fieldName + ": " + e.getMessage());
            return 0;
        }
    }
}


