    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author sabri
 */
public class Jogo {

    private ArrayList<Frase> frases;
    private int indiceAtual;
    private int score;

    public Jogo() {
        frases = new ArrayList<>();
        indiceAtual = 0;
        score = 0;
    }

    public void carregarFrases() throws IOException {
        var entrada = getClass().getClassLoader().getResourceAsStream("frases.txt");

        if (entrada == null) {
            throw new IOException("Arquivo frases.txt não encontrado em src/main/resources");
        }

        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(entrada, StandardCharsets.UTF_8))) {

            String linha;

            while ((linha = leitor.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    frases.add(new Frase(linha.trim()));
                }
            }
        }
    }

    public Frase getFraseAtual() {
        if (indiceAtual < frases.size()) {
            return frases.get(indiceAtual);
        }

        return null;
    }

    public boolean verificarFrase(String fraseDigitada) {
        Frase fraseAtual = getFraseAtual();

        if (fraseAtual != null) {
            boolean acertou = fraseAtual.comparar(fraseDigitada);

            if (acertou) {
                score++;
            }

            indiceAtual++;

            return acertou;
        }

        return false;
    }

    public boolean terminou() {
        return indiceAtual >= frases.size();
    }

    public int getScore() {
        return score;
    }

    public int getTotalFrases() {
        return frases.size();
    }

    public int carregarScoreMaximo(String caminhoArquivo) {
        File arquivo = new File(caminhoArquivo);

        if (!arquivo.exists()) {
            return 0;
        }

        try (BufferedReader leitor = Files.newBufferedReader(
                Path.of(caminhoArquivo),
                StandardCharsets.UTF_8)) {

            String linha = leitor.readLine();

            if (linha != null) {
                return Integer.parseInt(linha.trim());
            }

        } catch (IOException | NumberFormatException e) {
            return 0;
        }

        return 0;
    }

    public void salvarScoreMaximo(String caminhoArquivo) {
        int scoreMaximo = carregarScoreMaximo(caminhoArquivo);

        if (score > scoreMaximo) {
            try (BufferedWriter escritor = Files.newBufferedWriter(
                    Path.of(caminhoArquivo),
                    StandardCharsets.UTF_8)) {

                escritor.write(String.valueOf(score));

            } catch (IOException e) {
                System.out.println("Erro ao salvar score máximo.");
            }
        }
    }
}