/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 *
 * @author sabri
 */

/**
 * Cada frase deve ser lida do arquivo e armazenada em um ArrayList de objetos do tipo Frase
 */
public class ArquivoFrases {

    private String caminhoArquivo;

    public ArquivoFrases(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
    
    public ArrayList<Frase> lerFrases() throws IOException {
        ArrayList<Frase> frases = new ArrayList<>();

        BufferedReader leitor = Files.newBufferedReader(
                Paths.get(caminhoArquivo),
                StandardCharsets.UTF_8
        );

        String linha;

        while ((linha = leitor.readLine()) != null) {
            if (!linha.trim().isEmpty()) {
                Frase frase = new Frase(linha);
                frases.add(frase);
            }
        }

        leitor.close();

        return frases;
    }
}