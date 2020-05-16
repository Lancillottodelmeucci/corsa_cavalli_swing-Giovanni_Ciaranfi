package corsacavalli;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 * la classe che gestisce la singola corsia
 * @author Giovanni Ciaranfi
 */
public class Corsia extends JPanel{
    private int lunghezza;//la lunghezza della corsia
    private int numero;//il numero della corsia
    private Cavallo cavallo;//il cavallo che gareggia nella corsia
    /**
     * il costruttore parametrizzato che definisce la barra del progresso
     * @param l la lunghezza della corsia
     * @param n il numero della corsia
     */
    public Corsia(int l,int n){
        super(null);//layout impostato su null per poter scegliere la posizione degli oggetti
        lunghezza=l;
        numero=n;
        cavallo=new Cavallo("Cavallino"+n,n);
        this.add(cavallo);
        cavallo.setLocation(0,5);
        cavallo.setSize(30,30);//l'immagine è  grande 30x30 pixel*
        /*
        aggiunta dell'immagine dello striscione di fine corsa alla corsia
        */
        JLabel arrivo=new JLabel(new ImageIcon("C:\\Users\\giova\\Desktop\\Internet Explorer Windows documenti_vari_scuola\\arrivocavalli.png"));
        this.add(arrivo);
        arrivo.setBounds(310,1,20,40-1);
        this.setSize(300,40);
    }
    /**
     * il metodo che fa avanzare il cavallo
     */
    public void avanza(){
        int oldx=(int)cavallo.getLocation().getX();//oldx è la posizione precedente sull'asse x
        cavallo.setLocation(oldx+1,5);
    }
    /**
     * 
     * @return il cavallo che gareggia nella corsia
     */
    public Cavallo getCavallo(){
        return(cavallo);
    }
    /**
     * 
     * @param passi il numero di volte che il cavallo deve avanzare
     */
    public void avanza(int passi){
        for(int i=0;i<passi;i++){
            avanza();
            /*
            il tempo di attesa serve per non far "scattare il cavallo", ma a farlo proseguire passo per passo
            */
            try{
            Thread.currentThread().sleep(20);
            }catch(InterruptedException ex){}
        }
    }
    /**
     * il metodo che aggiunge l'etichetta nella corsia con cui indicare la posizione di arrivo del cavallo
     * @param p la posizione nella classifica
     */
    public void mostraArrivo(int p){
        JLabel labPos=new JLabel("Arrivato "+p+"°");
        this.add(labPos);
        labPos.setBounds(5,5,100,30);
    }
    /**
     * 
     * @return il numero della corsia corrente
     */
    public int getNumCorsia(){
        return(numero);
    }
    /**
     * disegna i bordi della corsia nella quale il cavallo si muove
     * @param g l'oggetto grafico predefinito
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawRect(0,0,300+30,40);
    }
}
