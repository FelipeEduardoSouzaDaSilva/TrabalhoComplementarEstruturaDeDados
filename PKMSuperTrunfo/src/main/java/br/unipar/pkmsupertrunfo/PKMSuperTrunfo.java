/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.unipar.pkmsupertrunfo;

import br.unipar.pkmsupertrunfo.models.JogoPKM;
import javax.swing.JOptionPane;


/**
 *
 * @author 00239689
 */
public class PKMSuperTrunfo {


    public static void main(String[] args) {
        
        JogoPKM j = new JogoPKM();

        boolean op = false;
        Object[] opcoes = {"CADASTRAR POKÉMON", "INICIAR BATALHA", "MOSTRAR POKÉMON", "SAIR"};

        while (op != true) {

            int x = JOptionPane.showOptionDialog(null, "Selecione a ação", "menu iniciar", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes, null);

             
            switch (x) {
                case 0:
                    j.cadastrarPKM();
                    break;
                case 1:
                    j.iniciarBatalha();
                    break;
                case 2:
                    j.mostraPKM();
                    break;
                default:
                    op = true;
                    break;

            }
        }

    }
    
    
}