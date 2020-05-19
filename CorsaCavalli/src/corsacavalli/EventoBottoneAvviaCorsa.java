package corsacavalli;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 * la classe che gestisce l'avvio della corsa, la la pausa e la ripresa
 * @author Giovanni Ciaranfi
 */
public class EventoBottoneAvviaCorsa implements ActionListener{
    private Ippodromo ippodromo;//l'ippodromo runnable con cui creare i thread
    private JButton btn;//il bottone di avvio da disabilitare
//    private Vector<Thread> thread;
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
//        btn.setEnabled(false);
        if(btn.getText().equals("Pausa")){
            btn.setText("Riprendi");
            btn.setBackground(new Color(255,209,220));
            ippodromo.cambiaStatoCorsa();
//            ippodromo.pausaCorsa();
        }
        else if(btn.getText().equals("Riprendi")){
            btn.setText("Pausa");
            btn.setBackground(new Color(193,234,186));
            ippodromo.cambiaStatoCorsa();
//            ippodromo.riprendiCorsa();
        }
        else{
            ippodromo.avvioCorsa();
        }
    }
}
