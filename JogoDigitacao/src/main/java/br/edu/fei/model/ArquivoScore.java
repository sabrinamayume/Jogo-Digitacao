/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * 
 * @author sabri
 */

/**
 * 
 * Ao finalizar o programa, um arquivo deve armazenar o score máximo da quantidade de frases corretas.
 */
public class ArquivoScore {

    private String caminhoArquivo;

    public ArquivoScore(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public int lerScoreMaximo() {
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));

            String linha = leitor.readLine();

            leitor.close();

            if (linha == null || linha.trim().isEmpty()) {
                return 0;
            }

            return Integer.parseInt(linha);

        } catch (IOException | NumberFormatException erro) {
            return 0;
        }
    }

    public void salvarScoreMaximo(int score) throws IOException {
        FileWriter escritor = new FileWriter(caminhoArquivo);

        escritor.write(String.valueOf(score));

        escritor.close();
    }
}