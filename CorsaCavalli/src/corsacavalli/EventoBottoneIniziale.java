package corsacavalli;

import java.awt.event.*;
import javax.swing.*;

/**
 * la classe che gestisce la creazione dell'ippodromo
 * @author Giovanni Ciaranfi
 */
public class EventoBottoneIniziale implements ActionListener{
    private JButton btn;//il bottone da modificare
    private JFrame frame;//il frame da passare all'ippodromo
    private JSlider slider;//lo slider per il numero di corsie
    /**
     * il costruttore parametrizzato
     * @param b il bottone a cui cambiare testo (funzione da rimuovere)
     * @param f il frame da passare per la crazione dell'ippodromo
     * @param s lo slider da cui prendere il numero di corsie
     */
    public EventoBottoneIniziale(JButton b,JFrame f,JSlider s){
        btn=b;
        frame=f;
        slider=s;
    }
    /**
     * il metodo che cambia nome al pulsante e crea il nuovo ippodromo
     * @param e l'evento
     */
    @Override
    public void actionPerformed(ActionEvent e){
        if(btn.getText().equals("Inizia")){
            btn.setText("Iniziata");
        }
        Ippodromo i=new Ippodromo("Ippodromo Ciaranfi",slider.getValue(),frame);
    }
}
