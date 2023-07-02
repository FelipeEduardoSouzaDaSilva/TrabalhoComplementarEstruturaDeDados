

package br.unipar.pkmsupertrunfo.models;


public class Pokemon {
    
    private String nome;
    private int forca;
    private int ataque;
    private int defesa;
    private int agilidade;

    public Pokemon(String nome, int forca, int ataque, int defesa, int agilidade) {
        this.nome = nome;
        this.forca = forca;
        this.ataque = ataque;
        this.defesa = defesa;
        this.agilidade = agilidade;
    }

    public Pokemon() {
    }

    

    public String getNome() {
        return nome;
    }

    public int getForca() {
        return forca;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getAgilidade() {
        return agilidade;
    }
    
     @Override
    public String toString() {
        return "Nome: " + nome +
                "\nForça: " + forca +
                "\nAtaque: " + ataque +
                "\nDefesa: " + defesa +
                "\nAgilidade: " + agilidade;
    }
}
