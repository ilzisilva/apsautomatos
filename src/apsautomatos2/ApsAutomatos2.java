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

/**
 *
 * @author Leonardo Mendonça Tuchinski
 * @since 2017
 */


public class ApsAutomatos2 {
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    boolean verificaAceitacao(Automato a, String epsilon, List<String> conjuntoEstadosAceitacao){
        if(a.getPalavraNaoProcessada().length() == 0 | a.getPalavraNaoProcessada().toString().equals(epsilon)){
                if(a.pilha.length() == 0 | a.pilha.toString().equals(epsilon)){
                    return true;
                }else if(conjuntoEstadosAceitacao.contains(a.getEstAtual())){
                    return true;
                }
        }
        return false;
    }
    
    public static void main(String[] args) throws IOException {
        int numComputacoes = 0;
        
        ApsAutomatos2 classePrincipal = new ApsAutomatos2();
                
        
//        if(args.length < 2){
//            System.out.println("Quantidade de argumentos invalida");
//            System.out.println("Para executar digite: java -jar apsAutomatos2.jar nomeDoArquivo.txt palavraParaSerProcessada");
//            System.exit(0);
//        }
//        String arquivo = args[0];
        String arquivo = "/home/tuchinski/Documentos/LFA/2_aula_pnda_v2.txt";
        
        
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
        StringBuilder palavra = new StringBuilder("a");
//        StringBuilder palavra = new StringBuilder(args[1]);
        
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
        
        try (BufferedReader data = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)))) {
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
                            alfabetoEntrada.add(s);
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
                                alfabetoPilha.add(s);
                            }
                        }
                        break;
                    case 2:
                        epsilon = line;
                        break;
                    case 3:
                        simboloInicialPilha = line;
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
        
        //verifica se os alfabetos não contem o simbolo que representa epsilon
        if(alfabetoEntrada.contains(epsilon) | alfabetoPilha.contains(epsilon)){
            System.err.println("O alfabeto de entrada ou o alfabeto de pilha contém o simbolo que representa epsilon ");
            System.exit(0);
        }
                
        Automato a = new Automato(palavra, estInicial, new StringBuilder(simboloInicialPilha));
        listaAutomatos.add(a);
        
        int elementoLista = 0;
        
        while(!listaAutomatos.isEmpty()){
            Automato atual = listaAutomatos.get(elementoLista);
            if(atual.getPalavraNaoProcessada().length() == 0 | atual.getPalavraNaoProcessada().toString().equals(epsilon)){
                if(atual.pilha.length() == 0 | atual.pilha.toString().equals(epsilon)){
                    System.out.println("1");
                    eAceito = true;
                    System.out.println("quantidade de computações : " + numComputacoes);
                    break;
                }else if(conjuntoEstadosAceitacao.contains(atual.getEstAtual())){
                    System.out.println("1");
                    eAceito = true;
                    break;
                }
            }
            
            for(List<String> ls : transicoes2){
                if(atual.getPalavraNaoProcessada().length() == 0){
                    atual.setPalavraNaoProcessada(new StringBuilder(epsilon));
                }
                
                if(atual.pilha.length() == 0){
                    if(ls.get(0).equals(atual.getEstAtual())){
                        if(atual.getPalavraNaoProcessada().substring(0, 1).equals(ls.get(1)) & (atual.getPalavraNaoProcessada().substring(0,1).equals(epsilon) == false)){
                            if(ls.get(2).equals(epsilon)){
                            StringBuilder novaPalavra = new StringBuilder(atual.getPalavraNaoProcessada());
                            novaPalavra.deleteCharAt(0);
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(novaPalavra, ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                            }
                        }
                        if(ls.get(1).equals(epsilon)){
                            if(ls.get(2).equals(epsilon)){
                            StringBuilder novaPilha = new StringBuilder(atual.pilha);
                            if(!ls.get(4).equals(epsilon)){
                                novaPilha.insert(0, new StringBuilder(ls.get(4)));
                            }
                            Automato novo = new Automato(atual.getPalavraNaoProcessada(), ls.get(3), novaPilha);
                            listaAutomatos.add(novo);
                            }
                        }
                    }
                }
                else{                
                    //verifica se os estados batem
        /*1*/       if(ls.get(0).equals(atual.getEstAtual())){
                        //verifica se a o simbolo(palavra) da transição é o msm do automato
        /*2*/           if(atual.getPalavraNaoProcessada().substring(0, 1).equals(ls.get(1)) & (atual.getPalavraNaoProcessada().substring(0,1).equals(epsilon) == false)){
        /*3*/               if(atual.pilha.substring(0, 1).equals(ls.get(2))){
                                //muda o estado atual
                                //atual.setEstAtual(ls.get(3));
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
        /*3*/               if(ls.get(2).charAt(0) == atual.pilha.charAt(0)){
                                StringBuilder novaPilha = new StringBuilder(atual.pilha);
                                novaPilha.deleteCharAt(0);
                                if(!ls.get(4).equals(epsilon)){
                                    novaPilha.insert(0, new StringBuilder(ls.get(4)));
                                }
                                Automato novo = new Automato(atual.getPalavraNaoProcessada(), ls.get(3), novaPilha);
                                listaAutomatos.add(novo);
                            }
        /*3*/               if(ls.get(2).equals(epsilon)){
                                StringBuilder novaPilha = new StringBuilder(atual.pilha);
                                if(!ls.get(4).equals(epsilon)){
                                    novaPilha.insert(0, new StringBuilder(ls.get(4)));
                                }
                                Automato novo = new Automato(atual.getPalavraNaoProcessada(), ls.get(3), novaPilha);
                                listaAutomatos.add(novo);
                            }
                        }
                    }
                }
                numComputacoes++;
            }
                        
            listaAutomatos.remove(atual);
                        
            if(!listaAutomatos.isEmpty()){
                elementoLista = (elementoLista+1) % listaAutomatos.size();
            }
        }
        
        if(!eAceito){
            System.out.println("0");
        }
        
    }
    
}