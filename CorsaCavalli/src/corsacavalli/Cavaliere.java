/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

/**
 *
 * @author giova
 */
public class Cavaliere {
    private String nome;
    private boolean stato;
    public Cavaliere(String n){
        nome=n;
        stato=true;
    }
    public String getNome(){
        return(nome);
    }
    public boolean getStato(){
        return(stato);
    }
    public void cambiaStato(){
        stato=stato==false;
    }
}
