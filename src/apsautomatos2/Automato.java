/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsautomatos2;

//import java.util.Stack;

/**
 *
 * @author tuchinski
 */
public class Automato {
     
    private StringBuilder palavraNaoProcessada;
    public StringBuilder pilha = new StringBuilder();
    private String estAtual;

    /**
     * 
     * @param palavraNaoProcessada A palavra que ainda não foi processada pelo autômato
     * @param estAtual O estado que o atuômato se encontra
     * @param pilha A pilha atual do autômato topo na direita e base na esquerda
     */
    
    public Automato(StringBuilder palavraNaoProcessada, String estAtual,StringBuilder pilha) {
        this.palavraNaoProcessada = palavraNaoProcessada;
        this.estAtual = estAtual;
        this.pilha = pilha;
       
    }

    public StringBuilder getPalavraNaoProcessada() {
        return palavraNaoProcessada;
    }

    public void setPalavraNaoProcessada(StringBuilder palavraNaoProcessada) {
        this.palavraNaoProcessada = palavraNaoProcessada;
    }

    public String getEstAtual() {
        return estAtual;
    }

    public void setEstAtual(String estAtual) {
        this.estAtual = estAtual;
    }
    /**
     * Imprime um autômato
     */
    public void printAutomato(){
        System.out.println("Estado atual: " + this.estAtual);
        System.out.println("Palavra não processada: " + this.palavraNaoProcessada);
        System.out.println("Pilha: " + this.pilha);
    }

    
    public String getSimboloPalavra(){
        return this.palavraNaoProcessada.toString();
    }
    
    public String getTopoPilha(){
        return this.pilha.toString();
    }
    
    
}
