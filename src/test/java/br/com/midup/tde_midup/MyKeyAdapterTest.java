package br.com.midup.tde_midup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe MyKeyAdapter.
 * Verifica o comportamento dos métodos de controle de teclas.
 */
public class MyKeyAdapterTest {

    private MyKeyAdapter keyAdapter;

    @BeforeEach
    void setUp() {
        keyAdapter = new MyKeyAdapter();
    }

    @Test
    void deveRetornarIndiceCorretoParaCodigoValido() {
        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_UP); // 38
        assertTrue(index >= 0, "O índice deve ser válido para a tecla 'Seta para cima'.");
    }

    @Test
    void deveRetornarMenosUmParaCodigoInvalido() {
        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_F1); // não está na lista
        assertEquals(-1, index, "Deve retornar -1 para tecla não mapeada.");
    }

    @Test
    void deveMarcarTeclaComoPressionada() {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_UP, ' ');
        keyAdapter.keyPressed(evento);

        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_UP);
        assertTrue(keyAdapter.isKeyPressed(index), "A tecla deve estar marcada como pressionada.");
    }

    @Test
    void deveDesmarcarTeclaAposSoltar() throws InterruptedException {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_UP, ' ');

        // Pressiona e solta
        keyAdapter.keyPressed(evento);
        keyAdapter.keyReleased(evento);

        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_UP);

        // Espera um pouco mais do que 5ms
        Thread.sleep(10);

        assertFalse(keyAdapter.isKeyPressed(index), "A tecla deve ser considerada solta após o tempo limite de 5ms.");
    }

    @Test
    void deveManterTeclaPressionadaPorPoucoTempoAposRelease() {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_UP, ' ');

        keyAdapter.keyPressed(evento);
        keyAdapter.keyReleased(evento);

        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_UP);

        // Verifica imediatamente (menos de 5ms após o release)
        assertTrue(keyAdapter.isKeyPressed(index),
                "A tecla deve continuar sendo considerada pressionada nos 5ms após o release.");
    }

    @Test
    void deveLidarComTeclaInvalidaSemErro() {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_F12, ' ');

        assertDoesNotThrow(() -> {
            keyAdapter.keyPressed(evento);
            keyAdapter.keyReleased(evento);
        }, "O método deve ignorar teclas inválidas sem lançar exceções.");
    }
}
