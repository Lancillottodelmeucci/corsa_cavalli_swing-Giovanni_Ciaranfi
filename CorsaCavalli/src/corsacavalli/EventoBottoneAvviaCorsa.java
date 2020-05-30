package corsacavalli;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * la classe che gestisce l'avvio della corsa, la la pausa e la ripresa
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
     * il metodo che in base allo stato della corsa gestisce l'avvio, la pausa e la ripresa della stessa
     * @param e l'azione
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(btn.getText().equals("Pausa")){//se viene premuto quando la corsa è iniziat e in corso di svolgimento
            btn.setText("Riprendi");
            btn.setBackground(new Color(255,209,220));
            ippodromo.cambiaStatoCorsa();
        }
        else if(btn.getText().equals("Riprendi")){//se viene premuto quando la corsa è in pausa
            btn.setText("Pausa");
            btn.setBackground(new Color(193,234,186));
            ippodromo.cambiaStatoCorsa();
        }
        else if(btn.getText().equals("Mostra classifica")){
            ArrayList<Corsia> inGara=ippodromo.getClassifica();
            ArrayList<Corsia> fuoriGara=ippodromo.getFuoriGara();
            ippodromo.nascondi();
            TabelloneClassifica cla_ssifica=new TabelloneClassifica(inGara,fuoriGara);
        }
        else{//quando viene premuto la prima volta per avviare la corsa
            ippodromo.avvioCorsa();
        }
    }
}
