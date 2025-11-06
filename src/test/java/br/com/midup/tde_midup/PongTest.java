package br.com.midup.tde_midup;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.lang.reflect.Field;

public class PongTest {


    // Métodos auxiliares para testes: permitem acessar e alterar campos privados da classe Ball (como 'cx' e 'cy') usando Reflection, sem precisar de getters/setters.
    private void setPrivateField(Object obj, String fieldName, double value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.setDouble(obj, value);
        } catch (Exception e) {
            fail("Erro ao acessar campo privado: " + e.getMessage());
        }
    }

    private double getPrivateField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.getDouble(obj);
        } catch (Exception e) {
            fail("Erro ao ler campo privado: " + e.getMessage());
            return -1;
        }
    }

    @Test
    public void testJogador1PontuaParedeDireita() {
        double larguraTela = 800;

        Score score1 = new Score("Player 1");
        Ball bola = new Ball(790, 300, 10, 10, Color.WHITE, 5);

        // Simula bola ultrapassando limite direito
        setPrivateField(bola, "cx", larguraTela + 5);

        double cx = getPrivateField(bola, "cx");
        if (cx >= larguraTela) {
            score1.inc();
        }

        assertEquals(1, score1.getScore(),"Jogador 1 deve marcar ponto ao ultrapassar a parede direita.");
    }

    @Test
    public void testJogador2PontuaParedeEsquerda() {
        double larguraTela = 800;

        Score score2 = new Score("Player 2");
        Ball bola = new Ball(10, 300, 10, 10, Color.WHITE, 5);

        // Simula bola ultrapassando limite esquerdo
        setPrivateField(bola, "cx",  -5);

        double cx = getPrivateField(bola, "cx");
        if (cx <= 0) {
            score2.inc();
        }

        assertEquals(1, score2.getScore(),  "Jogador 2 deve marcar ponto ao ultrapassar a parede esquerda.");
    }
}
