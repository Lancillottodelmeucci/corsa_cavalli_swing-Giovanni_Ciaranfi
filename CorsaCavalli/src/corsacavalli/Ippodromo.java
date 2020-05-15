package corsacavalli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
    /**
     * il costruttore parametrizzato che definisce il layout dell'ippodromo
     * @param n il nome dell'ippodromo
     * @param num il numero di corsie
     * @param f il frame in cui deve mostrarsi l'ippodromo
     */
    public Ippodromo(String n,int num,JFrame f) {
        JPanel intestazione=new JPanel(new FlowLayout(FlowLayout.RIGHT));//pannello per la gestione della gara
        JPanel pista=new JPanel();//pannello che contiene le corsie (pannelli divis perchè aventi layout diversi)
        corsie=new ArrayList<Corsia>();
        classifica=new ArrayList<Corsia>();
        ippodromo=f;
        lungCorsie=300;
        pan=new JPanel(new FlowLayout(FlowLayout.LEFT));
        /*JLabel nome=new JLabel(n);
        nome.setHorizontalAlignment(JLabel.LEFT);
        intestazione.add(nome);*/
        ippodromo.setTitle(n);
        pista.setLayout(new GridLayout(num+1,1));
        creaCorsie(num);
        JButton avvia=new JButton("Avvia corsa");
        EventoBottoneAvviaCorsa actAvvio=new EventoBottoneAvviaCorsa(this,avvia);
        avvia.addActionListener(actAvvio);
        intestazione.add(avvia);
        disponiCorsie(pista);
        intestazione.setPreferredSize(new Dimension(480,40));
        pan.add(intestazione);
        pan.add(pista);
        ippodromo.setContentPane(pan);
        indice=0;
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
        JLabel numCorsia;
        for(Corsia c:corsie){
            numCorsia=new JLabel("C"+c.getNumCorsia());
            numCorsia.setPreferredSize(new Dimension(30,30));
            //p.add(numCorsia);
            p.add(c);
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
        for(int i=0;i<lungCorsie;i++){
            c.avanza();
            if(c.getValue()==lungCorsie){
                break;
            }
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException ex){}
        }
        /*
        aggiunta alla classifica ad ogni arrivo alla fine della corsia
        */
        synchronized(classifica){
            classifica.add(c);
        }
        c.setString("Arrivato "+(classifica.indexOf(c)+1)+"°");
    }
    /**
     * 
     * @return il numero di corsie dell'ippodromo
     */
    public int getNumCorsie(){
        return(corsie.size());
    }
}
