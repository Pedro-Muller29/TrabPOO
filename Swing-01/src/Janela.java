import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Janela extends JFrame implements ActionListener {
    private Tabuleiro tabuleiro;
    private Personagem personagem;

    public Janela() {
        super();

        // Define os componentes da tela
        tabuleiro = new Tabuleiro();

        int width = 400;
        int height = 300;

        JPanel botoesDirecao = new JPanel(new FlowLayout());
        JButton butDir = new JButton("Direita");
        butDir.addActionListener(this);
        JButton butEsq = new JButton("Esquerda");
        butEsq.addActionListener(this);
        JButton butCima = new JButton("Acima");
        butCima.addActionListener(this);
        JButton butBaixo = new JButton("Abaixo");
        butBaixo.addActionListener(this);
        botoesDirecao.add(butEsq);
        botoesDirecao.add(butDir);
        botoesDirecao.add(butCima);
        botoesDirecao.add(butBaixo);
        /////////////////// DE ACAO DO TECLADO
        /////////////////// butDir
        KeyStroke rightKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);

        ActionMap actionMapDir = butDir.getActionMap();
        actionMapDir.put("clickButton", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                butDir.doClick();
            }
        });

        InputMap inputMapDir = butDir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapDir.put(rightKeyStroke, "clickButton");

        butDir.requestFocus();
        /////////////////// butEsq
        KeyStroke leftKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);

        ActionMap actionMapEsq = butEsq.getActionMap();
        actionMapEsq.put("clickButton", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                butEsq.doClick();
            }
        });

        InputMap inputMapEsq = butEsq.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapEsq.put(leftKeyStroke, "clickButton");

        butEsq.requestFocus();

        /////////////////// butCima
        KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);

        ActionMap actionMapUp = butCima.getActionMap();
        actionMapUp.put("clickButton", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                butCima.doClick();
            }
        });

        InputMap inputMapUp = butCima.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapUp.put(upKeyStroke, "clickButton");

        butCima.requestFocus();

        /////////////////// butBaixo

        KeyStroke downKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);

        ActionMap actionMapDown = butBaixo.getActionMap();
        actionMapDown.put("clickButton", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                butBaixo.doClick();
            }
        });

        InputMap inputMapDown = butBaixo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMapDown.put(downKeyStroke, "clickButton");

        butBaixo.requestFocus();
        /////////////////// FIM DOS SETS DE ACAO DO TECLADO

        JPanel painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.PAGE_AXIS));
        painelGeral.add(tabuleiro);
        painelGeral.add(botoesDirecao);

        // Insere os personagens no tabuleiro
        personagem = new Personagem("Rato", "Ratin.png", 2, 0, tabuleiro);
        ElementoBasico anterior = tabuleiro.insereElemento(personagem);
        personagem.setAnterior(anterior);

        Pista pista1 = new Pista("Pista15", 15, 2, 4, tabuleiro);
        tabuleiro.insereElemento(pista1);
        Pista pista2 = new Pista("Pista22", 22, 0, 2, tabuleiro);
        tabuleiro.insereElemento(pista2);
        Eca eca = new Eca("Gato", "Gato.png", 6, 7, tabuleiro);
        tabuleiro.insereElemento(eca);

        // Exibe a janela
        this.add(painelGeral);

        this.setTitle("Jogo Demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        tabuleiro.atualizaVisualizacao();

        this.setSize(width, height);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton but = (JButton) arg0.getSource();
        if (but.getText().equals("Direita")) {
            personagem.moveDireita();
        }
        if (but.getText().equals("Esquerda")) {
            personagem.moveEsquerda();
        }
        if (but.getText().equals("Acima")) {
            personagem.moveCima();
        }
        if (but.getText().equals("Abaixo")) {
            personagem.moveBaixo();
        }
        tabuleiro.atualizaVisualizacao();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Janela();
            }
        });
    }
}