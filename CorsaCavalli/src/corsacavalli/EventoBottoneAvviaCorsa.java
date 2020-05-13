package corsacavalli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;

/**
 * la classe che gestisce l'avvio della corsa e l'avvio dei thread
 * @author Giovanni Ciaranfi
 */
public class EventoBottoneAvviaCorsa implements ActionListener{
    private Ippodromo ippodromo;//l'ippodromo runnable con cui creare i thread
    private JButton btn;//il bottone di avvio da disabilitare
    /**
     * il costruttore parametrizzato
     * @param i l'ippodromo nel quale viene avviata la corsa
     * @param b il bottone che avvia la corsa, che deve essere disabilitato
     */
    public EventoBottoneAvviaCorsa(Ippodromo i,JButton b){
        ippodromo=i;
        btn=b;
    }
    /**
     * il metodo che disabilita il bottone di inizio, e avvia la corsa
     * @param e l'azione
     */
    @Override
    public void actionPerformed(ActionEvent e){
        btn.setEnabled(false);
        Vector<Thread> thread=new Vector<Thread>();
        for(int i=0;i<ippodromo.getNumCorsie();i++){
            thread.add(new Thread(ippodromo));
        }
        for(Thread t:thread){
            t.start();
        }
    }
}
