package corsacavalli;

import java.io.IOException;
import javax.imageio.ImageIO;
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
     * @throws java.io.IOException nel caso in cui non trovasse o non riuscisse a caricare l'immagine
     */
    public Cavallo(String n,int num) throws IOException{
        super(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("img/iconacavallo.png"))));
        nome=n;
        this.num=num;
        /*
        la velocità specifica del cavallo ancora non viene usata nella corsa
        */
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
