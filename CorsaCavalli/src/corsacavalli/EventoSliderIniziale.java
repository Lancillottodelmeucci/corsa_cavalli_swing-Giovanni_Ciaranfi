package corsacavalli;

import javax.swing.*;
import javax.swing.event.*;

/**
 * la classe che gestisce il cambiamento del valore dello slider
 * @author Giovanni Ciaranfi
 */
public class EventoSliderIniziale implements ChangeListener{
    private JLabel numCorsie;//l'etichetta su cui scrivere il valore dello slider
    private JSlider slider;//lo slider da cui prendere il valore
    /**
     * il costruttore parametrizzato
     * @param p l'etichetta sulla quale mostrare il valore
     * @param s lo slider che viene modificato
     */
    public EventoSliderIniziale(JLabel p,JSlider s){
        numCorsie=p;
        slider=s;
    }
    /**
     * il metodo che mostra il valore dello slider nell'etichetta
     * @param e l'evento
     */
    @Override
    public void stateChanged(ChangeEvent e){
        numCorsie.setText(""+slider.getValue());
    }
}
