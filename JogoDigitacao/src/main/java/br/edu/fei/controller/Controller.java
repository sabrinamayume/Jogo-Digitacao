/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.controller;


import br.edu.fei.model.ArquivoFrases;
import br.edu.fei.model.Frase;
import br.edu.fei.model.ArquivoScore;
import br.edu.fei.view.TelaDigitacao;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sabri
 */
public class Controller {

    private TelaDigitacao tela;
    private ArrayList<Frase> frases;
    private int indiceFraseAtual;
    private int score;
    private ArquivoScore arquivoScore;

    public Controller(TelaDigitacao tela) {
        this.tela = tela;
        this.indiceFraseAtual = 0;
        this.score = 0;

        carregarArquivos();
        configurarEventos();
        iniciarJogo();
    }

    private void carregarArquivos() {
        try {
            ArquivoFrases arquivoFrases = new ArquivoFrases("frases.txt");
            frases = arquivoFrases.lerFrases();

            arquivoScore = new ArquivoScore("score.txt");

        } catch (IOException erro) {
            JOptionPane.showMessageDialog(
                    tela,
                    "Erro ao ler o arquivo de frases: " + erro.getMessage()
            );

            frases = new ArrayList<>();
        }
    }

    private void configurarEventos() {

        tela.getTxtDigitacao().getInputMap().put(
                javax.swing.KeyStroke.getKeyStroke("ENTER"),
                "confirmar"
        );

        tela.getTxtDigitacao().getActionMap().put("confirmar", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                confirmarFrase();
            }
        });

        tela.getBtnConfirmar().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                verificarTextoAoPassarMouse();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tela.mudarCorTexto(Color.BLACK);
            }
        });

        tela.getTxtDigitacao().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE
                        || evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
                    evt.consume();
                }
            }
        });
    }

    private void iniciarJogo() {
        if (frases.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Nenhuma frase foi encontrada.");
            tela.dispose();
            return;
        }

        mostrarFraseAtual();
    }

    private void mostrarFraseAtual() {
        Frase fraseAtual = frases.get(indiceFraseAtual);
        tela.mostrarFrase(fraseAtual.getTexto());
        tela.limparCampo();
        tela.mudarCorTexto(Color.BLACK);
        tela.focarCampo();
    }

    public void confirmarFrase() {
        Frase fraseAtual = frases.get(indiceFraseAtual);
        String textoDigitado = tela.getTextoDigitado();

        boolean acertou = fraseAtual.comparar(textoDigitado);

        if (acertou) {
            score++;
            JOptionPane.showMessageDialog(tela, "Correto!");
        } else {
            JOptionPane.showMessageDialog(tela, "Errado!");
        }

        indiceFraseAtual++;

        if (indiceFraseAtual < frases.size()) {
            mostrarFraseAtual();
        } else {
            finalizarJogo();
        }
    }

    private void verificarTextoAoPassarMouse() {
        Frase fraseAtual = frases.get(indiceFraseAtual);
        String textoDigitado = tela.getTextoDigitado();

        if (fraseAtual.comparar(textoDigitado)) {
            tela.mudarCorTexto(Color.GREEN);
        } else {
            tela.mudarCorTexto(Color.RED);
        }
    }

    private void finalizarJogo() {
        int scoreMaximo = arquivoScore.lerScoreMaximo();

        if (score > scoreMaximo) {
            scoreMaximo = score;

            try {
                arquivoScore.salvarScoreMaximo(scoreMaximo);
            } catch (IOException erro) {
                JOptionPane.showMessageDialog(
                        tela,
                        "Erro ao salvar o score máximo: " + erro.getMessage()
                );
            }
        }

        JOptionPane.showMessageDialog(
                tela,
                "Fim de jogo!\n"
                + "Seu score: " + score + "\n"
                + "Score máximo: " + scoreMaximo
        );

        tela.dispose();
    }

}
