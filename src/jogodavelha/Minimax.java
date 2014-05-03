/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jogodavelha;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Minimax {
    /**
     * Calcula o Minimax e devolve qual seria a proxima jogada.
     * @param Tabuleiro tabuleiro
     * @return Tabuleiro
     */
     public Tabuleiro minimaxDecision(Tabuleiro tabuleiro) {
        int melhor = MaxValue(tabuleiro);
        ArrayList<Tabuleiro> filhos = tabuleiro.getTodosFilhos();
        for (Tabuleiro filho : filhos) {
            //filho.mostra();
            //System.out.println("");
            if (filho.getValor() == melhor) {
                return filho;
            }
        }
        return null;
    }

    private int MinValue(Tabuleiro tabuleiro) {
        if (tabuleiro.isTerminal()) {
            tabuleiro.setValor(tabuleiro.getResultado());
            return tabuleiro.getValor();
        } else {
            tabuleiro.setValor(Integer.MAX_VALUE);
            tabuleiro.setJogador(Jogador.Min);
            ArrayList<Tabuleiro> filhos = tabuleiro.getFilhos(tabuleiro);
            for (Tabuleiro filho : filhos) {
                tabuleiro.setValor(Math.min(tabuleiro.getValor(), MaxValue(filho)));
            }
            return tabuleiro.getValor();
        }
    }

    private int MaxValue(Tabuleiro tabuleiro) {
        if (tabuleiro.isTerminal()) {
            tabuleiro.setValor(tabuleiro.getResultado());
            return tabuleiro.getValor();
        } else {
            tabuleiro.setValor(Integer.MIN_VALUE);
            tabuleiro.setJogador(Jogador.Max);
            ArrayList<Tabuleiro> filhos = tabuleiro.getFilhos(tabuleiro);
            for (Tabuleiro filho : filhos) {
                tabuleiro.setValor(Math.max(tabuleiro.getValor(), MinValue(filho)));
            }
            return tabuleiro.getValor();
        }
    }
}
