package corsacavalli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * la classeche gestisce l'ippodromo
 * @author Giovanni Ciaranfi
 */
public class Ippodromo implements Runnable{
    private int lungCorsie;//la lunghezza delle corsie
    private ArrayList<Corsia> corsie;//la lista delle corsie create
    private JFrame ippodromo;//il frame che conterrà il pannello
    private JPanel pan;//il pannelo che conterrà l'ippodromo e le corsie
    private int indice;//l'indice (usato con la lista corsie) per l'assegnazione della corsia ai singoli thread
    private ArrayList<Corsia> classifica;//la lista delle corsie secondo la classifica di arrivo
    private boolean statoCorsa;//indica lo stato della corsa, true se è in corso, false se è sospesa
    private JButton btnAPC;//bottone avvio, pausa e continua
    private Vector<Thread> thread;//l'insieme dei thread che muovono i cavalli
    private ArrayList nomi;//la lista dei nomi degli animali corridori
    private ArrayList<Animale> animali;//la lista degli
    private ArrayList<Corsia> infortunati;
    private ArrayList<Corsia> corsieInGara;
    private Vector ippoClone;
    /**
     * il costruttore parametrizzato che definisce il layout dell'ippodromo
     * @param n il nome dell'ippodromo
     * @param num il numero di corsie
     * @param f il frame in cui deve mostrarsi l'ippodromo
     * @param l la lunghezza delle corsie
     * @param nomi l'array dei nomi dei cavalli per la corsa corrente
     * @param animali l'array contenente i tipi di animale che gareggiano
     */
    public Ippodromo(String n,int num,JFrame f,int l,ArrayList nomi,ArrayList animali) {
        ippoClone=new Vector();
//        ippoClone.add(n);ippoClone.add(num);ippoClone.add(f);ippoClone.add(l);ippoClone.add(nomi);ippoClone.add(animali);
        /*
        nascondo momentaneamente il frame dell'ippodromo fino al suo caricamento
        per evitare di vedere lag neri durante il caricamento
        */
        JFrame c=iniziaCaricamento(f);
        JPanel intestazione=new JPanel(new FlowLayout(FlowLayout.RIGHT));//pannello per la gestione della gara
        JPanel pista=new JPanel();//pannello che contiene le corsie (pannelli divis perchè aventi layout diversi)
        corsie=new ArrayList<Corsia>();
        classifica=new ArrayList<Corsia>();
        infortunati=new ArrayList<Corsia>();
        ippodromo=f;
        lungCorsie=l;
        this.nomi=nomi;
        this.animali=animali;
        pan=new JPanel(new FlowLayout(FlowLayout.LEFT));
        ippodromo.setTitle(n);
        pista.setLayout(new GridLayout(num+1,1));
        creaCorsie(num);
        /*
        inserimento del bottone di avvio, pausa e ripresa della corsa
        */
        btnAPC=new JButton("Avvia corsa");
        btnAPC.setPreferredSize(new Dimension(150,25));
        btnAPC.setBorderPainted(false);
        EventoBottoneAvviaCorsa actAvvio=new EventoBottoneAvviaCorsa(this,btnAPC);
        btnAPC.addActionListener(actAvvio);
        JButton btnRel=null;
        try {
            btnRel = new JButton(new ImageIcon(ImageIO.read(ClassLoader.getSystemResource("img/reload.png"))));
        }
        catch (IOException ex) {}
        btnRel.setPreferredSize(new Dimension(50,25));
        btnRel.setBorderPainted(false);
        btnRel.addActionListener(new EventoBottoneRiavvio(this,ippodromo));
        intestazione.add(btnRel);
        intestazione.add(btnAPC);
        /*
        disposizione delle corsie
        */
        disponiCorsie(pista);
        intestazione.setPreferredSize(new Dimension(lungCorsie+100,40));
        pista.setPreferredSize(new Dimension(lungCorsie+100,corsie.size()*50+30));
        pan.add(intestazione);
        pan.add(pista);
        ippodromo.setContentPane(pan);
        /*
        imposto la dimensione della finestra in base alle necessità
        */
        ippodromo.setSize(lungCorsie+150,(int)intestazione.getSize().getHeight()+(int)pista.getSize().getHeight()+10);
        indice=0;
        statoCorsa=false;//la corsia inizialmente non è avviata
        fineCaricamento(c);
    }
    /**
     * il metodo che utilizzo per nascondere l'ippodromo e mostrare un frame semplice, per evitare che vi sia l'impressione di chiusura del programma
     * @param f il frame dell'ippodromo
     * @return il frame di avviso caricamento
     */
    private JFrame iniziaCaricamento(JFrame f){
        f.setVisible(false);
        JFrame caricamento=new JFrame("Caricamento dell'Ippodromo");
        caricamento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        caricamento.setSize(500,0);
        caricamento.setVisible(true);
        try {
            Thread.currentThread().sleep(100);
        }
        catch (InterruptedException ex) {}
        return(caricamento);
    }
    /**
     * il metodo che nasconde il frame di caricamento e mostra nuovamente quello dell'ippodromo
     * @param f il frame del caricamento
     */
    private void fineCaricamento(JFrame f){
        ippodromo.setVisible(true);
        f.setVisible(false);
    }
    /**
     * il metodo che crea e inserisce le corsie in un array
     * @param n il numero di corsie da creare
     */
    private void creaCorsie(int n){
        for(int i=0;i<n;i++){
            Corsia c=new Corsia(lungCorsie,i+1,nomi.get(i).toString(),animali.get(i));
            corsie.add(c);
        }
        corsieInGara=new ArrayList<>(corsie);
    }
    /**
     * il metodo che aggiunge le corsie all'ippodromo
     * @param p il pannello della pista sul quale disporre le corsie
     */
    private void disponiCorsie(JPanel p){
        for(Corsia c:corsie){
            p.add(c);
        }
    }
    /**
     * il metodo per cambiare lo stato della corsa
     */
    public void cambiaStatoCorsa(){
        statoCorsa=statoCorsa==false;
    }
    /**
     * il metodo che crea e avvia i thread per poter muovere i cavalli
     */
    public void avvioCorsa(){
        cambiaStatoCorsa();
        thread=new Vector<Thread>();
        for(int i=0;i<getNumCorsie();i++){
            thread.add(new Thread(this));
        }
        for(Thread t:thread){
            t.start();
        }
        btnAPC.setText("Pausa");
        btnAPC.setBackground(new Color(193,234,186));
    }
    /**
     * il metodo che gestisce l'avanzamento del cavallo in ogni singola corsia e ne determina la classifica
     */
    @Override
    public void run(){
        /*
        assegnazione di ogni corsia ai thread in esecuzione, blocco sincronizzato per evitare conflitti
        */
        Corsia c;
        synchronized(pan){
            c=corsie.get(indice);
            indice++;
        }
        /*
        il ciclo viene ripetuto tante volte quanto è lunga la corsia perchè così
        anche se il cavallo dovesse avanzare solo di uno riuscirebbe ad arrivare
        alla fine. Nel caso in cui avanzasse più velocemente, e arrivasse al
        massimo consentito prima, la condizione vera termina il ciclo
        */
        int avanzamento;
        int infortunio;
        for(int i=0;i<lungCorsie;i++){
            while(!statoCorsa){
                System.out.print("");
            }
            infortunio=(int)(Math.random()*50001);
            switch(infortunio){
                case 0://infortunio del cavallo (fine corsa)
                    /*
                    migliorare la leggibilità mettendo il codice dell'infortunio
                    in un metodo, esistono solo più casi separati per un
                    eventuale cambiamento nell'avviso (personalizzabile passando
                    una stringa)
                    */
                    infortunati.add(c);
                    corsieInGara.remove(c);
                    ifFinita(c);
                    c.mostraFine();
                    return;//Thread.currentThread().interrupt();
                    //break;
                case 2500:
                case 5000://caduta del cavaliere (attesa breve)
                    try{
                        Thread.sleep(1850);
                    }
                    catch(InterruptedException ex){}
                    break;
                case 17500://altro
                    break;
                case 32000:
                case 33000://caduta dell'animale (attesa lunga)
                    try{
                        Thread.sleep(3500);
                    }
                    catch(InterruptedException ex){}
                    break;
                case 41500://altro
                    break;
                case 50000://infortunio del cavaliere (fine corsa)
                    infortunati.add(c);
                    corsieInGara.remove(c);
                    ifFinita(c);
                    c.mostraFine();
                    return;//Thread.currentThread().interrupt();
                    //break;
            }
            avanzamento=c.getCavallo().getVelocita()+(int)(Math.random()*3);//alla velocità del cavallo viene aggiunto un int random tra 0 e 2
            if(c.getCavallo().getLocation().getX()+avanzamento>=lungCorsie){
                c.avanza(lungCorsie-(int)c.getCavallo().getLocation().getX());
                break;
            }
            else{
                c.avanza(avanzamento);
            }
            try{
                Thread.sleep(150);
            }
            catch(InterruptedException ex){}
        }
        /*
        aggiunta alla classifica ad ogni arrivo di un cavallo alla fine della
        corsia e inserimento della label che mostra la posizione
        */
//        synchronized(classifica){
//            classifica.add(c);
//            c.mostraArrivo(classifica.indexOf(c)+1);
//            c.impostaPodio(classifica.indexOf(c)+1);
//            /*
//            nel caso in cui la classifica sia di dimensione uguale alla lista
//            delle corsie significa che anche l'ultimo cavallo è arrivato a destinazione
//            */
//            if(classifica.size()==corsieInGara.size()){
//                btnAPC.setText("Mostra classifica");
//                btnAPC.setBackground(new Color(255,209,220));
//            }
            ifFinita(c);
//        }
    }
    public synchronized void ifFinita(Corsia c){
        if(corsieInGara.contains(c)){
            classifica.add(c);
            c.mostraArrivo(classifica.indexOf(c)+1);
            c.impostaPodio(classifica.indexOf(c)+1);
        }
        /*
        nel caso in cui la classifica sia di dimensione uguale alla lista
        delle corsie significa che anche l'ultimo cavallo è arrivato a destinazione
        */
        if(classifica.size()==corsieInGara.size()){
            btnAPC.setText("Mostra classifica");
            btnAPC.setBackground(new Color(255,209,220));
        }
    }
    /**
     * 
     * @return il numero di corsie dell'ippodromo
     */
    public int getNumCorsie(){
        return(corsie.size());
    }
    /**
     * 
     * @return la classifica della corsa
     */
    public ArrayList<Corsia> getClassifica(){
        return(classifica);
    }
    public ArrayList<Corsia> getFuoriGara(){
        return(infortunati);
    }
    /**
     * il metodo che nasconde il frame dell'ippodromo
     */
    public void nascondi(){
        ippodromo.setVisible(false);
    }
    public Vector getIppodromo(){
        return(ippoClone);
    }
}
