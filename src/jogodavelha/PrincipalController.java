/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo
 */
public class PrincipalController implements Initializable {

    @FXML
    Button L1C1, L1C2, L1C3, L2C1, L2C2, L2C3, L3C1, L3C2, L3C3;
    @FXML
    Label estatistica;

    private int ganhou = 0, pedeu = 0, empate = 0;
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Minimax minimax = new Minimax();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init();
    }

    @FXML
    public void init() {
        tabuleiro = new Tabuleiro();
        setTabuleiro(tabuleiro);
        tabuleiro.setJogador(Jogador.Min); // Minha vez de Jogar
        estatistica.setText("Ganhou: " + ganhou + " Perdeu: " + pedeu + " Empatou: " + empate);

    }

    @FXML
    public void marcaX(ActionEvent me) {
        if (tabuleiro.getJogador().equals(Jogador.Min)) {
            //Eu Joguei
            Button celula = (Button) me.getSource();
            if (celula.getText() == null || "".equals(celula.getText())) {
                celula.setText("X");
                tabuleiro.setX(Integer.parseInt(celula.getId().charAt(1) + "") - 1, Integer.parseInt(celula.getId().charAt(3) + "") - 1);

//Calculo o Minimax
                tabuleiro.setJogador(Jogador.Max);
                tabuleiro = minimax.minimaxDecision(tabuleiro);

//Computador Joga
                setTabuleiro(tabuleiro);
                tabuleiro.setJogador(Jogador.Min);

//Mostra Resultado caso acabou o Jogo
                if (tabuleiro.isTerminal()) {
                    JOptionPane.showMessageDialog(null, "Você ganhou? " + tabuleiro.perdeu() + "\nVocê perdeu? " + tabuleiro.ganhou() + "\nDeu empate? " + tabuleiro.empate());
                    if (tabuleiro.ganhou()) {
                        pedeu++;
                    }
                    if (tabuleiro.perdeu()) {
                        ganhou++;
                    }
                    if (tabuleiro.empate()) {
                        empate++;
                    }
                    this.init();
                }

            } else {
                if (celula.getText().equals("X")) {
                    JOptionPane.showMessageDialog(null, "Você já jogou ai, presta atenção!");
                } else {
                    JOptionPane.showMessageDialog(null, "Eu já joguei ai, presta atenção!");
                }
            }
        } else {
            System.out.println("Nao é sua vez ainda espere!");
        }
    }

    private void setTabuleiro(Tabuleiro tab) {
        L1C1.setText(tab.getXY(0, 0));
        L1C2.setText(tab.getXY(0, 1));
        L1C3.setText(tab.getXY(0, 2));

        L2C1.setText(tab.getXY(1, 0));
        L2C2.setText(tab.getXY(1, 1));
        L2C3.setText(tab.getXY(1, 2));

        L3C1.setText(tab.getXY(2, 0));
        L3C2.setText(tab.getXY(2, 1));
        L3C3.setText(tab.getXY(2, 2));
    }

}
