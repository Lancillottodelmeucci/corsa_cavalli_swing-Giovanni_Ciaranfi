package corsacavalli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * la classe del cavallo
 * @author Giovanni Ciaranfi
 */
public class Cavallo  extends JLabel{//da cambiare in AnimaleCorridore
    private String nome;//il nome del cavallo
    private int num;//il numero di corsia del cavallo
    private int velocita;//la velocità di avanzamento del cavallo
    private File f;//il file dell'icona del cavallo
    private Animale animale;//il tipo di animale che deve essere il "Cavallo"
    /**
     * il costruttore parametrizzato, che imposta la velocità del cavallo la sua icona
     * @param n il nome del cavallo
     * @param num il numero di corsia del cavallo
     * @param a l'animale che gareggia nella corsia
     * @throws java.io.IOException nel caso in cui non trovasse o non riuscisse a caricare l'immagine
     */
    public Cavallo(String n,int num,Animale a) throws IOException{
        super(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("img/icone_animali/"+a.getAnimale()+".png"))));
        nome=n;
        this.num=num;
        animale=a;
        velocita=animale.getVelocita();//prendo la velocità da quella dell'animale che è predefinito
        f=new File("src/cavalli/"+nome+".txt");
        if(f.exists()){
            Scanner s = new Scanner(f);
            String line = s.nextLine();
            String vString=line.substring(line.indexOf("<v>")+3,line.indexOf("</v>"));
            velocita=Integer.parseInt(vString);
        }
        else if(f.createNewFile()){
            FileWriter fW = new FileWriter(f);
            fW.write("<v>"+velocita+"</v>");
            fW.close();
        }
        else{
            System.out.println("Some error occurred");
        }
    }
    /**
     * il metodo che aggiorna la velocità del cavallo sul file con una media tra
     * quella esistente e quella meritata nella gara svolta
     * @param p la velocità meritata durante la corsa
     * @throws IOException nel caso di problemi con il file
     */
    public void aggiornaVelocita(int p) throws IOException{
        int newVel=Math.round((velocita+(10-p+1))/2);
        FileWriter fW = new FileWriter(f,false);
        fW.write("<v>"+newVel+"</v>");
        fW.close();
    }
    /**
     * 
     * @return il valore della velocità del cavallo
     */
    public int getVelocita(){
        return(velocita);
    }
    /**
     * 
     * @return il nome del cavallo
     */
    public String getNome(){
        return(nome);
    }
    /**
     * 
     * @return l'animale che gareggia nella corsia
     */
    public Animale getAnimale(){
        return(animale);
    }
}
