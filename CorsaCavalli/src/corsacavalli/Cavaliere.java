package corsacavalli;

/**
 * la classe che gestisce il cavaliere dell'animale corridore
 * @author Giovanni Ciaranfi
 */
public class Cavaliere {//non ancora propriamente usata
    private String nome;
    private boolean stato;
    /**
     * il costruttore parametrizzato
     * @param n il nome del cavaliere
     */
    public Cavaliere(String n){
        nome=n;
        stato=true;
    }
    /**
     * 
     * @return il nome del cavaliere
     */
    public String getNome(){
        return(nome);
    }
    /**
     * 
     * @return lo stato del cavaliere (false nel caso in cui fosse infortunato o fuori gara)
     */
    public boolean getStato(){
        return(stato);
    }
    /**
     * cambia lo stato del cavaliere, se false diventa true, se ture diventa false
     */
    public void cambiaStato(){
        stato=stato==false;
    }
}
