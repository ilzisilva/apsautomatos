/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apsautomatos2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
//import java.util.Stack;

/**
 *
 * @author tuchinski
 */
public class ApsAutomatos2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //declaração Alfabeto de entrada(retirado do arquivo)
        List<String> alfabetoEntrada = new LinkedList<>();
        
        /**
         *declaração alfabeto Pilha(retirado do arquivo)
         */
        List<String> alfabetoPilha = new LinkedList<>();
        
        //declaração simbolo Epsilon(retirado do arquivo)
        String epsilon = null; 
        
        //declaração Simbolo inicial Pilha(retirado do arquivo)
        String simboloInicialPilha = null;
                
        //declaração conjunto de estados(retirado do arquivo)
        List<String> conjuntoEstados = new LinkedList<>();
        
        //declaração estado Inicial(retirado do arquivo)
        String estInicial = null;
        
        //declaraçãoconj dos estados de aceitação(retirado do arquivo)
        List<String> conjuntoEstadosAceitacao = new LinkedList<>();
        
        //List<Transicao> transicoes = new LinkedList<>();
        // Declaração da variavel transições(retirado do arquivo)
        List<List<String>> transicoes2 = new LinkedList<>();
        //List<String>[0] = estado atual
        //List<String>[1] = Simbolo atual da palavra
        //List<String>[2] = Simbolo atual da pilha
        //List<String>[3] = Novo estado
        //List<String>[4] = Simbolos a empilhar(topo a esquerda e base a direita)
        
        //palavra inicial(a palavra que foi digitada)
        StringBuilder palavra = new StringBuilder("");
        
        //Pilha inicial do automato
        //Stack<Character> pilhaAutomato = new Stack<>();
        
        //Pilha de Automatos(contem o estado atual, a palavra que ainda nao foi processada e a pilha atual do Automato)
        List<Automato> listaAutomatos = new ArrayList<>();
              
        //contador para verificar em qual linha do arquivo está sendo lida
        int cont = 0;
        
        //recebe a linha atual do arquivo
        String line;
        
        //recebe a divisão da linha(é um vetor de string, onde cada posição é um "token")
        String vetor[];
        
        //verifica se o automato foi aceito
        boolean eAceito = false;
        
        try (BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream("/home/tuchinski/Documentos/Curso JAVA/APSAutomatos/teste.txt")))) {
               //enquanto houver linhas disponiveis faça
            while (((line = data.readLine()) != null)) {
                /*esse switch serve para salvar cada dado de cada linha na sua respectiva variável
                  a ordem é: linha 1 - alfabeto de entrada
                             linha 2 - alfabeto da pilha
                             linha 3 - simbolo que representa epsilon
                             linha 4 - simbolo inicial da pilha
                             linha 5 - conjunto de estados
                             linha 6 - estado inicial
                             linha 7 - conjunto dos estados de aceitação
                             linha 8 até o fim do arquivo - transições no formato estado atual,
                             simbolo atual da palavra, simbolo do topo da pilha, novo estado, 
                             novos simbolos a serem empilhados (topo a esquerda, base a direita*/
                switch (cont) {
                    case 0:
                        //divide a linha atual para o vetor de Strings
                        vetor = line.split(" ");
                        int i = 0;
                        for(String s: vetor){
                            alfabetoEntrada.add(line);
                            i++;
                        }
                        //adiciona todas as posiçoes do vetor na lista, utilizando a 
                        //função Arrays.asList(vetor) que serve para fazer o "casting"
                        //alfabetoEntrada.addAll(Arrays.asList(vetor));
                        break;
                    case 1:
                        vetor = line.split(" ");
                        //esse for percorre o vetor, string por string, até o fim
                        // for(tipo da variavel : onde essas variáveis estão)
                        int j = 0;
                        for(String s : vetor){
                            if(!s.isEmpty()){
                                alfabetoPilha.add(line);
                            }
                        }
                        break;
                    case 2:
                        epsilon = line;
                        break;
                    case 3:
                        simboloInicialPilha = line;
                        //System.out.println(simboloInicialPilha);
                        break;
                    case 4:
                        vetor = line.split(" ");
                        conjuntoEstados.addAll(Arrays.asList(vetor));
                        break;
                    case 5:
                        estInicial = line;
                        break;
                    case 6:
                        vetor = line.split(" ");
                        conjuntoEstadosAceitacao.addAll(Arrays.asList(vetor));
                        break;
                    default:
                        vetor = line.split(" ");
                        transicoes2.add((Arrays.asList(vetor)));
                        //Transicao t = new Transicao(vetor[0], vetor[1], vetor[2], vetor[3], vetor[4]);
                        //transicoes.add(t);
                        break;
                }
                cont++;
                
            }
        }
        //se o arquivo não for encontrado
        catch(FileNotFoundException a){
            System.out.println("O arquivo não foi encontrado!");
            System.exit(1);
        }
        
//        for(int i=0;i<palavra.length();i++){
//            Character c = palavra.charAt(i);
//            String ch = c.toString();
//            if(!alfabetoEntrada.contains(ch)){
//                System.out.println("A palavra contem simbolo(s) que não faz parte do alfabeto");
//                System.exit(1);
//            }
//        }
        
        Automato a = new Automato(palavra, estInicial, new StringBuilder(simboloInicialPilha));
        listaAutomatos.add(a);
        
        int elementoLista = 0;
        
        while(!listaAutomatos.isEmpty()){
            Automato atual = listaAutomatos.get(elementoLista);
            if(atual.getPalavraNaoProcessada().length() == 0 | atual.getPalavraNaoProcessada().toString().equals(epsilon)){
                if(atual.pilha.length() == 0){
                    System.out.println("Deu certo!!");
                    eAceito = true;
                    break;
                }else if(conjuntoEstadosAceitacao.contains(atual.getEstAtual())){
                    System.out.println("deu certo!!!");
                    eAceito = true;
                    break;
                }
            }
            
            for(List<String> ls : transicoes2){
                if(atual.getPalavraNaoProcessada().length() == 0){
                    atual.setPalavraNaoProcessada(new StringBuilder(epsilon));
                }
                //verifica se os estados batem
    /*1*/       if(ls.get(0).equals(atual.getEstAtual())){
                    //verifica se a o simbolo(palavra) da transição é o msm do automato
    /*2*/           if(atual.getPalavraNaoProcessada().substring(0, 1).equals(ls.get(1)) & (atual.getPalavraNaoProcessada().substring(0,1).equals(epsilon) == false)){
    /*3*/               if(atual.pilha.substring(0, 1).equals(ls.get(2))){
                            //muda o estado atual
                            //atual.setEstAtual(ls.get(3));
                            atual.setAvancou(true);
                            //cria uma nova palavra e modifica no Automato atual
                            StringBuilder novaPalavra = new StringBuilder(atual.getPalavraNaoProcessada());
                            novaPalavra.deleteCharAt(0);
                            //atual.setPalavraNaoProcessada(new StringBuilder(novaPalavra));
                            //desempilha
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            novaPilha.deleteCharAt(0);
                            //empilha se o simbolo a empilhar for diferente de epsilon
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(novaPalavra, ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                        }
    /*3*/               if(ls.get(2).equals(epsilon)){
                            //atual.setEstAtual(ls.get(3));
                            //atual.setAvancou(true);
                            StringBuilder novaPalavra = new StringBuilder(atual.getPalavraNaoProcessada());
                            novaPalavra.deleteCharAt(0);
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(novaPalavra, ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                            //atual.setPalavraNaoProcessada(novaPalavra);
                        }
                    }
    /*2*/           if(ls.get(1).equals(epsilon)){
    /*3*/               if(ls.get(2).equals(atual.getTopoPilha())){
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            novaPilha.deleteCharAt(0);
                            atual.setAvancou(true);
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(atual.getPalavraNaoProcessada(), ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                        }
    /*3*/               if(ls.get(2).equals(epsilon)){
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            atual.setAvancou(true);
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(atual.getPalavraNaoProcessada(), ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                        }
                    }
    
                }
            }
            
            if(atual.getAvancou() == false){
                Automato remove = listaAutomatos.remove(0);
                System.out.println("Automato Removido: ");
                remove.printAutomato();
                System.out.println("");
            }
                        
            if(!listaAutomatos.isEmpty()){
                elementoLista = (elementoLista+1) % listaAutomatos.size();
            }
        }
        
        if(eAceito){
            System.out.println("deu certo caraio");
        }else{
            System.out.println("nao deu certo caraio");
        }
        
    }
    
}

        //impressão das variaveis obtidas do arquivo
//        System.out.print("Alfabeto de entrada: " + alfabetoEntrada);        
//        System.out.print("\nAlfabeto da Pilha: " + alfabetoPilha);        
//        System.out.println("\nSimbolo Epsilon: " + epsilon);
//        System.out.println("Simbolo inicial pilha: " + simboloInicialPilha);
//        System.out.print("Conjunto de estados: " + conjuntoEstados);        
//        System.out.println("\nEstado inicial: " + estInicial);        
//        System.out.println("Conjunto de estados de aceitação: " + conjuntoEstadosAceitacao + "\n\n");
//        int num = 0;
//        //for(Transicao t : transicoes){
//        for(List<String> t : transicoes2){
//            System.out.println("Transicao " + num + ": ");
//            
//            System.out.println("Estado Atual: " + t.get(0));
//            System.out.println("Simbolo atual da palavra: " + t.get(1));
//            System.out.println("Simbolo atual da pilha: " + t.get(2));
//            System.out.println("Novo Estado: " + t.get(3));
//            System.out.println("Simbolo a empilhar: " + t.get(4));
//            System.out.println("\n\n\n");
//            num++;
//        }