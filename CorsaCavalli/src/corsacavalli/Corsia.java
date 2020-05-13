package corsacavalli;

import java.awt.Dimension;
import javax.swing.*;

/**
 * la classe che gestisce la singola corsia
 * @author Giovanni Ciaranfi
 */
public class Corsia extends JProgressBar{
    private int lunghezza;//la lunghezza della corsia
    private int numero;//il numero della corsia
    private Cavallo cavallo;//il cavallo che gareggia nella corsia
    /**
     * il costruttore parametrizzato che definisce la barra del progresso
     * @param l la lunghezza della corsia
     * @param n il numero della corsia
     */
    public Corsia(int l,int n){
        super();
        lunghezza=l;
        this.setMinimum(0);//imposto il vlore minimo a 0
        this.setMaximum(l);//imposto il valore massimo alla lunghezza della corsia
        this.setValue(0);//"posiziona" il cavallo all'inizio
        this.setString("0m");
        this.setStringPainted(true);
        numero=n;
        cavallo=new Cavallo("Cavallino"+n,n);
        this.setPreferredSize(new Dimension(440,30));
    }
    /**
     * il metodo che gestisce l'avanzamento nella corsia in base alla velocità del cavallo
     */
    public void avanza(){
        int p=this.getValue();
        this.setValue(p+cavallo.getVelocita());//aumenta della velocità del cavallo
        this.setString(this.getValue()+"m");//aggiunta dell'annotazione metrica
    }
    public int getNumCorsia(){
        return(numero);
    }
}
