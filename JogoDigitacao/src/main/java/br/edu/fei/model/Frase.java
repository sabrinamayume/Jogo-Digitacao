/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.fei.model;

/**
 *
 * @author sabri
 */

/**
 * A classe Frase deve ter somente uma string e um método booleano que compara a string que é atributo com outra string passada como parâmetro.
 */
public class Frase {

    private String texto;

    public Frase(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public boolean comparar(String textoDigitado) {
        return this.texto.equals(textoDigitado);
    }
}