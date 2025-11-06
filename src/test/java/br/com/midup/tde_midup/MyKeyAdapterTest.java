package br.com.midup.tde_midup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
//essa classe vai tratar do controle das teclas, cuidando dos momentos em que as teclas são precionadas e soltas tbm 
public class MyKeyAdapterTest {

    private MyKeyAdapter keyAdapter;

    @BeforeEach 
    //faz com que seja excutado toda vez q um teste diferente seja executado, ele vai ccriar uma instância de "MyKeyAdapter" pra não haver ambientes de execução mais confiáveis/limpos. 
    void setUp() {
        keyAdapter = new MyKeyAdapter();
    }

    @Test //testa o retorno de getIndexFromKeyCode para indice válido
    void deveRetornarIndiceCorretoParaCodigoValido() {
        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_A); //65
        assertTrue(index >= 0, "O índice deve ser válido para a tecla 'A'.");
        System.out.println("indice retornado: " + index); //private int[] codes = new int[]{38, 40, 37, 39, 17, 27, 65, 90, 75, 77, 32};
    }

    @Test //testa o retorno de getIndexFromKeyCode para indice invalido
    void deveRetornarMenosUmParaCodigoInvalido() {
        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_F1); //tecla f1 não está o array
        assertEquals(-1, index, "Deve retornar -1 para tecla não mapeada.");//só retorna se falhar
    }

    @Test //teste de tecla marcada como pressionada quando o keyPressed é chamado
    void deveMarcarTeclaComoPressionada() {
        //criação de evento de tecla pressionada pra simulação
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_A, ' ');
        //chama o evento
        keyAdapter.keyPressed(evento);

        //pega o indice da tecla pressionada e ve seu estado
        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_A); //simulação com A 
        assertTrue(keyAdapter.isKeyPressed(index), "A tecla deve estar marcada como pressionada.");

        System.out.println("Tecla pressionada: " + evento.getKeyChar() + " (index: " + index + ")");
    }

    @Test //testa se a tecla é desmarcada após o keyReleased e o tempo de 5ms
    void deveDesmarcarTeclaAposSoltar() throws InterruptedException {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_A, ' ');

        // simula o pressiona e solta da tecla
        keyAdapter.keyPressed(evento);
        keyAdapter.keyReleased(evento);

        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_A);

        //aguarda um pouco mais do que 5ms pra garantir a ultrapassagem do tempo limite (5ms)
        Thread.sleep(10);

        //verifica o estado da tecla novamente, tem que estar solta
        assertFalse(keyAdapter.isKeyPressed(index), "A tecla deve ser considerada solta após o tempo limite de 5ms.");
    }

    @Test //testa se a tecla continua marcada como pressionada por até 5ms após soltar
    void deveManterTeclaPressionadaPorPoucoTempoAposRelease() {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_A, ' ');

        //simula o pressiona e solta
        keyAdapter.keyPressed(evento);
        keyAdapter.keyReleased(evento);

        int index = keyAdapter.getIndexFromKeyCode(KeyEvent.VK_A);

        //vrifica imediatamente (menos de 5ms após o release)
        assertTrue(keyAdapter.isKeyPressed(index),
                "A tecla deve continuar sendo considerada pressionada nos 5ms após o release.");
    }

    @Test //testa o comportamento com uma tecla invalida, sem lançar erros mesmo que a tecla nao eseja mapeada na lista 
    void deveLidarComTeclaInvalidaSemErro() {
        KeyEvent evento = new KeyEvent(new java.awt.Canvas(), 0, 0, 0, KeyEvent.VK_F12, ' ');

        //diz q é pra ignorar teclas inválidas sem lançar exceções
        assertDoesNotThrow(() -> {
            keyAdapter.keyPressed(evento);
            keyAdapter.keyReleased(evento);
        }, "O método deve ignorar teclas inválidas sem lançar exceções.");
    }
}
