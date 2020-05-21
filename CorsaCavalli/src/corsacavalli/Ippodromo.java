package corsacavalli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JButton btnRiavvia;
    private Vector<Thread> thread;
    
    /**
     * il costruttore parametrizzato che definisce il layout dell'ippodromo
     * @param n il nome dell'ippodromo
     * @param num il numero di corsie
     * @param f il frame in cui deve mostrarsi l'ippodromo
     */
    public Ippodromo(String n,int num,JFrame f,int l) {
        JPanel intestazione=new JPanel(new FlowLayout(FlowLayout.RIGHT));//pannello per la gestione della gara
        JPanel pista=new JPanel();//pannello che contiene le corsie (pannelli divis perchè aventi layout diversi)
        corsie=new ArrayList<Corsia>();
        classifica=new ArrayList<Corsia>();
        ippodromo=f;
        lungCorsie=l;
        pan=new JPanel(new FlowLayout(FlowLayout.LEFT));
        ippodromo.setTitle(n);
        pista.setLayout(new GridLayout(num+1,1));
        creaCorsie(num);
        
        btnRiavvia=new JButton("Riavvia");
        EventoBottoneRiavviaCorsa actRiavvio=new EventoBottoneRiavviaCorsa(this);
        btnRiavvia.addActionListener(actRiavvio);
//        intestazione.add(btnRiavvia);
        
        btnAPC=new JButton("Avvia corsa");
        btnAPC.setPreferredSize(new Dimension(150,25));
        btnAPC.setBorderPainted(false);
        EventoBottoneAvviaCorsa actAvvio=new EventoBottoneAvviaCorsa(this,btnAPC);
        btnAPC.addActionListener(actAvvio);
        intestazione.add(btnAPC);
        
        disponiCorsie(pista);
        intestazione.setPreferredSize(new Dimension(lungCorsie+100,40));
        pista.setPreferredSize(new Dimension(lungCorsie+100,corsie.size()*60));
        pan.add(intestazione);
        pan.add(pista);
        ippodromo.setContentPane(pan);
        /*
        imposto la dimensione della finestra in base alle necessità
        */
        ippodromo.setSize(lungCorsie+150,(int)intestazione.getSize().getHeight()+(int)pista.getSize().getHeight()+10);
        indice=0;
        statoCorsa=true;
    }
    /**
     * il metodo che crea l'array di corsie
     * @param n il numero di corsie da creare
     */
    private void creaCorsie(int n){
        for(int i=0;i<n;i++){
            Corsia c=new Corsia(lungCorsie,i+1);
            corsie.add(c);
        }
    }
    /**
     * il metodo che aggiunge le corsie all'ippodromo
     */
    private void disponiCorsie(JPanel p){
        for(Corsia c:corsie){
            p.add(c);
        }
    }
    public void cambiaStatoCorsa(){
        statoCorsa=statoCorsa==false;
    }
    public void avvioCorsa(){
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
    public void riprendiCorsa(){
        
    }
    public void riavvioCorsa(){
        Ippodromo ippo=new Ippodromo("Ippodromo Ciaranfi",corsie.size(),ippodromo,lungCorsie);
//        cambiaStatoCorsa();
//        for(Thread t:thread){
//            t.interrupt();
//            t=new Thread(this);
//        }
//        for(int i=0;i<corsie.size();i++){
//            Corsia c=new Corsia(lungCorsie,i+1);
//            corsie.set(i,c);
//        }
//        for(Thread t:thread){
//            t.start();
//        }
//        avvioCorsa();
    }
    public void pausaCorsa(){
        final Object LOCK=new Object();
        for(Thread t:thread){
            try {
                synchronized(LOCK){
                    t.wait();
                }
            } catch (InterruptedException ex) {}
        }
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
        for(int i=0;i<lungCorsie;i++){
            while(!statoCorsa){
                System.out.print("");
//                try {
//                    Thread.currentThread().wait();
//                }
//                catch (InterruptedException ex) {}
            }
            avanzamento=(int)(Math.random()*10+1);
            if(c.getCavallo().getLocation().getX()+avanzamento>=lungCorsie){
                c.avanza(lungCorsie-(int)c.getCavallo().getLocation().getX());
                break;
            }
            else
                c.avanza(avanzamento);//l'avanzamento viene fatto con differenti velocità per aumentare la realtà
            try{
                Thread.sleep(50);
            }
            catch(InterruptedException ex){}
        }
        /*
        aggiunta alla classifica ad ogni arrivo di un cavallo alla fine della
        corsia e inserimento della label che mostra la posizione
        */
        synchronized(classifica){
            classifica.add(c);
            c.mostraArrivo(classifica.indexOf(c)+1);
            c.impostaPodio(classifica.indexOf(c)+1);
            if(classifica.size()==corsie.size()){
                btnAPC.setText("Terminata");
                btnAPC.setEnabled(false);
                btnAPC.setBackground(new Color(255,209,220));
            }
        }
    }
    /**
     * 
     * @return il numero di corsie dell'ippodromo
     */
    public int getNumCorsie(){
        return(corsie.size());
    }
}
