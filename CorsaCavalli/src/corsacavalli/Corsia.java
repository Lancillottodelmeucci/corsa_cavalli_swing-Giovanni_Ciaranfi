package corsacavalli;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.*;

/**
 * la classe che gestisce la singola corsia
 * @author Giovanni Ciaranfi
 */
public class Corsia extends JPanel{
    private int lunghezza;//la lunghezza della corsia
    private int numero;//il numero della corsia (ancora non usato)
    private Cavallo cavallo;//il cavallo che gareggia nella corsia
    /**
     * il costruttore parametrizzato che definisce il pannello della corsia
     * @param l la lunghezza della corsia
     * @param n il numero della corsia
     * @param nom il nome del cavallo
     * @param a l'animale che gareggia nella corsia
     */
    public Corsia(int l,int n,String nom,Animale a){
        super(null);//layout impostato su null per poter scegliere la posizione degli oggetti
        lunghezza=l;
        numero=n;
        try{
            cavallo=new Cavallo(nom,n,a);
        }
        catch (IOException ex){}
        this.add(cavallo);
        cavallo.setLocation(0,5);
        cavallo.setSize(30,30);//l'immagine è grande 30x30 pixel*
        /*
        aggiunta dell'immagine dello striscione di fine corsa alla corsia
        */
        JLabel arrivo=null;
        try{
            arrivo=new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("img/arrivocavalli.png"))));
        }
        catch (IOException ex){}
        this.add(arrivo);
        arrivo.setBounds(lunghezza+10,1,20,40-1);
        this.setSize(350,40);
    }
    /**
     * il metodo che se mostra la classifica all'interno del podio nel caso in cui la posizione sia tra i primi tre
     * @param p la posizione nella classifica del cavallo
     */
    public void impostaPodio(int p){
        if(p>3){
            return;
        }
        String nomeImg=p+"posto.png";
        JLabel podio=null;
        try{
            podio=new JLabel(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("img/"+nomeImg))));
        }
        catch (IOException ex) {}
        this.add(podio);
        podio.setBounds(lunghezza+10,0,80,40);
    }
    /**
     * il metodo che fa avanzare il cavallo
     */
    public void avanza(){
        int oldx=(int)cavallo.getLocation().getX();//oldx è la posizione precedente sull'asse x
        cavallo.setLocation(oldx+1,5);
        cavallo.setVisible(true);
    }
    /**
     * 
     * @return il cavallo che gareggia nella corsia
     */
    public Cavallo getCavallo(){
        return(cavallo);
    }
    /**
     * il metodo che fa avanzare il cavallo di tot volte
     * @param passi il numero di volte che il cavallo deve avanzare
     */
    public void avanza(int passi){
        for(int i=0;i<passi;i++){
            avanza();
        }
    }
    public void mostraFine(){
//        cavallo.iconaFuorigara();//this.remove(cavallo);
//        cavallo.setBounds(0,0,0,0);//sostituto della rimozione
        cavallo.fuorigara();
        cavallo.setBounds(105,5,30,30);
        JLabel labPos=new JLabel("Fuorigara");
        this.add(labPos);
        labPos.setBounds(5,5,100,30);
        try {
            cavallo.aggiornaVelocita(0);
        }
        catch (IOException ex){}
    }
    /**
     * il metodo che aggiunge l'etichetta nella corsia con cui indicare la posizione di arrivo del cavallo
     * @param p la posizione nella classifica
     */
    public void mostraArrivo(int p){
        JLabel labPos=new JLabel("Arrivato "+p+"°");
        this.add(labPos);
        labPos.setBounds(5,5,100,30);
        try {
            cavallo.aggiornaVelocita(p);
        }
        catch (IOException ex){}
    }
    /**
     * disegna i bordi della corsia nella quale il cavallo si muove
     * @param g l'oggetto grafico predefinito
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        try{//in base alla specie dell'animale la pista cambia (erba/acqua/aria?)
            g.drawImage(ImageIO.read(ClassLoader.getSystemResource(cavallo.getAnimale().getSpecie().equalsIgnoreCase("acquatica")?"img/acqua.png":"img/erba.png")),0,0,lunghezza+30,40,null);
        }
        catch(IOException e){}
        g.drawRect(0,0,lunghezza+30,40);
    }
    /**
     * 
     * @return le informazioni della corsia
     */
    @Override
    public String toString(){
        String ret="Corsia "+numero+", cavallo "+cavallo.getNome();
        return(ret);
    }
}
