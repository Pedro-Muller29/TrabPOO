import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

public class Tabuleiro extends JPanel {
    private static final int MAXLIN = 7;
    private static final int MAXCOL = 10;
    private ElementoBasico[][] celulas;
    private Image imagemFundo;

    public Tabuleiro() {
        super();
        // Cria o conjunto de células vazia e as insere no layout
        celulas = new ElementoBasico[MAXLIN][MAXCOL];
        this.setLayout(new GridLayout(MAXLIN, MAXCOL));
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {
                celulas[i][j] = new Fundo("Fundo[" + i + "][" + j + "]", i, j, this);
                this.add(celulas[i][j]);
            }
        }

    }

    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Janela.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            System.exit(0);
            return null;
        }
    }

    public static int getMaxlin() {
        return MAXLIN;
    }

    public static int getMaxcol() {
        return MAXCOL;
    }

    public boolean posicaoValida(int lin, int col) {
        if ((lin < 0) || (col < 0) ||
                (lin >= MAXLIN) || (col >= MAXCOL)) {
            return false;
        } else {
            return true;
        }
    }

    // Retorna referencia em determinada posição
    public ElementoBasico getElementoNaPosicao(int lin, int col) {
        if (!posicaoValida(lin, col)) {
            return null;
        }
        return celulas[lin][col];
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        imagemFundo = new ImageIcon("C:/Users/lucaa/OneDrive - PUCRS - BR/Swing/Swing-01/src/fundo.jpg").getImage();
        // Desenhar a imagem de fundo no painel
        g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
    }

    public ElementoBasico insereElemento(ElementoBasico elemento) {
        int lin = elemento.getLin();
        int col = elemento.getCol();
        if (!posicaoValida(lin, col)) {
            throw new IllegalArgumentException("Posicao invalida:" + lin + " ," + col);
        }
        ElementoBasico elementoAnterior = celulas[lin][col];
        celulas[lin][col] = elemento;
        return elementoAnterior;
    }

    public void atualizaVisualizacao() {
        // Atualiza o conteúdo do JPanel (ver algo melhor)
        this.removeAll(); // erase everything from your JPanel
        this.revalidate();
        this.repaint();// I always do these steps after I modify my JPanel
        for (int i = 0; i < MAXLIN; i++) {
            for (int j = 0; j < MAXCOL; j++) {
                this.add(celulas[i][j]);
            }
        }
    }
}
