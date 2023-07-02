package br.unipar.pkmsupertrunfo.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class JogoPKM {

    private List<Pokemon> pokemons;
    private List<Pokemon> jogador1;
    private List<Pokemon> jogador2;

    public JogoPKM() {
        this.pokemons = new ArrayList<>();
        this.jogador1 = new ArrayList<>();
        this.jogador2 = new ArrayList<>();
    }
    
    

    public void cadastrarPKM() {
        String nome = JOptionPane.showInputDialog("Digite o nome do Pokémon:");
        int forca = Integer.parseInt(JOptionPane.showInputDialog("Digite a força do Pokémon:"));
        int ataque = Integer.parseInt(JOptionPane.showInputDialog("Digite o ataque do Pokémon:"));
        int defesa = Integer.parseInt(JOptionPane.showInputDialog("Digite a defesa do Pokémon:"));
        int agilidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a agilidade do Pokémon:"));

        Pokemon pokemon = new Pokemon(nome, forca, ataque, defesa, agilidade);
        pokemons.add(pokemon);
        JOptionPane.showMessageDialog(null, "Pokémon cadastrado com sucesso!");
    }

    public void mostraPKM() {
        StringBuilder output = new StringBuilder();
        for (Pokemon pokemon : pokemons) {
            output.append("Nome: ").append(pokemon.getNome()).append("\n");
            output.append("Força: ").append(pokemon.getForca()).append("\n");
            output.append("Ataque: ").append(pokemon.getAtaque()).append("\n");
            output.append("Defesa: ").append(pokemon.getDefesa()).append("\n");
            output.append("Agilidade: ").append(pokemon.getAgilidade()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, output.toString());
    }
    
    private int buscarPokemon() {
    if (pokemons.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Não há Pokémon disponível para seleção!");
        return -1;
    }

    // Obter o nome do Pokémon a ser buscado
    String nomePokemon = JOptionPane.showInputDialog("Digite o nome do Pokémon:");

    // Realizar busca binária
    int inicio = 0;
    int fim = pokemons.size() - 1;
    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;
        Pokemon pokemon = pokemons.get(meio);
        int comparacao = nomePokemon.compareToIgnoreCase(pokemon.getNome());

        if (comparacao == 0) {
            return meio;
        } else if (comparacao < 0) {
            fim = meio - 1;
        } else {
            inicio = meio + 1;
        }
    }

    JOptionPane.showMessageDialog(null, "Pokémon não encontrado!");
    return -1;
}

    public void iniciarBatalha() {
        if (pokemons.size() < 6) {
            JOptionPane.showMessageDialog(null, "Não há Pokémon suficiente para a batalha!");
            return;
        }

        // Ordenar a lista de pokémons
        Collections.sort(pokemons, (p1, p2) -> p1.getNome().compareToIgnoreCase(p2.getNome()));

        // Selecionar 3 pokémons para cada jogador
        selecionarPokemons(jogador1, "Jogador 1");
        selecionarPokemons(jogador2, "Jogador 2");

        // Exibir os pokémons selecionados para cada jogador
        StringBuilder output = new StringBuilder();
        output.append("Jogador 1:\n");
        for (Pokemon pokemon : jogador1) {
            output.append(pokemon).append("\n\n");
        }
        output.append("Jogador 2:\n");
        for (Pokemon pokemon : jogador2) {
            output.append(pokemon).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());

        // Batalha
        while (!jogador1.isEmpty() && !jogador2.isEmpty()) {
            // Selecionar o pokémon para a rodada
            Pokemon pokemon1 = selecionarPokemon(jogador1, "Jogador 1");
            Pokemon pokemon2 = selecionarPokemon(jogador2, "Jogador 2");

            // Selecionar o atributo de comparação
            String atributo = JOptionPane.showInputDialog("Selecione o atributo para a comparação:\n"
                    + "1 - Força\n"
                    + "2 - Ataque\n"
                    + "3 - Defesa\n"
                    + "4 - Agilidade");

            // Comparar os atributos e determinar o vencedor
            int valor1 = obterValorAtributo(pokemon1, atributo);
            int valor2 = obterValorAtributo(pokemon2, atributo);
            if (valor1 > valor2) {
                JOptionPane.showMessageDialog(null, "Jogador 1 venceu a rodada!");
                jogador2.remove(0);
            } else if (valor2 > valor1) {
                JOptionPane.showMessageDialog(null, "Jogador 2 venceu a rodada!");
                jogador1.remove(0);
            } else {
                JOptionPane.showMessageDialog(null, "Empate! Nenhum Pokémon foi derrotado.");
            }
        }

        // Verificar o vencedor
        if (jogador1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jogador 2 venceu a batalha!");
        } else {
            JOptionPane.showMessageDialog(null, "Jogador 1 venceu a batalha!");
        }
    }
    
    
    private void selecionarPokemons(List<Pokemon> jogador, String nomeJogador) {
        jogador.clear();
        JOptionPane.showMessageDialog(null, "Selecione 3 Pokémon para " + nomeJogador + ":");

        for (int i = 0; i < 3; i++) {
            int indice = buscarPokemon();
            jogador.add(pokemons.get(indice));
            pokemons.remove(indice);
        }
    }

    private Pokemon selecionarPokemon(List<Pokemon> jogador, String nomeJogador) {
        JOptionPane.showMessageDialog(null, nomeJogador + ", selecione o Pokémon para a rodada:");

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < jogador.size(); i++) {
            output.append(i + 1).append(" - ").append(jogador.get(i).getNome()).append("\n");
        }

        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, output.toString())) - 1;
        return jogador.get(opcao);
    }

    private int obterValorAtributo(Pokemon pkm, String at) {
        switch (at) {
            case "1":
                return pkm.getForca();
            case "2":
                return pkm.getAtaque();
            case "3":
                return pkm.getDefesa();
            case "4":
                return pkm.getAgilidade();
            default:
                return 0;
        }
    }

}
