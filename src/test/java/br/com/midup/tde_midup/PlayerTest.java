package br.com.midup.tde_midup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;
    private double[] limites;
    private final double alturaTela = 600;
    private final double alturaBarra = 100;
    private final double velocidade = 0.5; // pixels por ms

    @BeforeEach
    void setUp() {
        // limites verticais da área útil da quadra: topo = 0, base = alturaTela
        limites = new double[]{0, alturaTela};
        // cria o jogador no meio da tela
        player = new Player(50, alturaTela / 2, 20, alturaBarra, Color.WHITE, "P1", limites, velocidade);
    }

    @Test
    void devePararNoLimiteSuperior() {
        // simula movimento para cima por muito tempo
        long delta = 1000; // ms
        for (int i = 0; i < 20; i++) {
            player.moveUp(delta);
        }

        // posição esperada: y = topo + metade da altura da barra
        double esperado = limites[0] + (alturaBarra / 2);
        assertEquals(esperado, player.getCy(), 0.0001,
                "A barra deve parar exatamente no limite superior (Y = alturaBarra/2)");
    }

    @Test
    void devePararNoLimiteInferior() {
        // simula movimento para baixo por muito tempo
        long delta = 1000; // ms
        for (int i = 0; i < 20; i++) {
            player.moveDown(delta);
        }

        // posição esperada: y = base - metade da altura da barra
        double esperado = limites[1] - (alturaBarra / 2);
        assertEquals(esperado, player.getCy(), 0.0001,
                "A barra deve parar exatamente no limite inferior (Y = alturaTela - alturaBarra/2)");
    }

    @Test
    void deveMoverParaCimaDentroDosLimites() {
        double posicaoInicial = player.getCy();
        long delta = 10;
        player.moveUp(delta);

        assertTrue(player.getCy() < posicaoInicial,
                "A barra deve ter se movido para cima (y deve diminuir)");
    }

    @Test
    void deveMoverParaBaixoDentroDosLimites() {
        double posicaoInicial = player.getCy();
        long delta = 10;
        player.moveDown(delta);

        assertTrue(player.getCy() > posicaoInicial,
                "A barra deve ter se movido para baixo (y deve aumentar)");
    }
}
