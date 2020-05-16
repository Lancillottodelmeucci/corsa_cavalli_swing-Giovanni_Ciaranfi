package corsacavalli;

import javax.swing.*;

/**
 * la classe del cavallo
 * @author Giovanni Ciaranfi
 */
public class Cavallo  extends JLabel{
    private String nome;//il nome del cavallo
    private int num;//il numero di corsia del cavallo
    private int velocita;//la velocità di avanzamento del cavallo
    /**
     * il costruttore parametrizzato, che imposta la velocità del cavallo la sua icona
     * @param n il nome del cavallo
     * @param num il numero di corsia del cavallo
     */
    public Cavallo(String n,int num){
        super(new ImageIcon("C:\\Users\\giova\\Desktop\\Internet Explorer Windows documenti_vari_scuola\\trojan (1).png"));
        nome=n;
        this.num=num;
        velocita=(int)(Math.random()*10+1);//creo una velocità tra 1 e 10 per rendere più reale la corsa
    }
    /**
     * 
     * @return il valore della velocità del cavallo
     */
    public int getVelocita(){
        return(velocita);
    }
}
