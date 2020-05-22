package corsacavalli;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

/**
 * la classe che gestisce il cambiamento del valore dello slider
 * @author Giovanni Ciaranfi
 */
public class EventoSliderIniziale implements ChangeListener{
    private JLabel numCorsie;//l'etichetta su cui scrivere il valore dello slider
    private JSlider slider;//lo slider da cui prendere il valore
    private ArrayList<JTextField> nomi;
    private JPanel panel;
    private JFrame frame;
    /**
     * il costruttore parametrizzato
     * @param p l'etichetta sulla quale mostrare il valore
     * @param s lo slider che viene modificato
     */
    public EventoSliderIniziale(JLabel p,JSlider s,ArrayList<JTextField> n,JPanel pan,JFrame f){
        numCorsie=p;
        slider=s;
        nomi=n;
        panel=pan;
        frame=f;
    }
    /**
     * il metodo che mostra il valore dello slider nell'etichetta
     * @param e l'evento
     */
    @Override
    public void stateChanged(ChangeEvent e){
        numCorsie.setText(""+slider.getValue());
        ordinaCorsie(slider.getValue());
    }
    private void ordinaCorsie(int n){
        for(JTextField f:nomi){
            if(!f.isVisible()&&nomi.indexOf(f)<n){
                f.setVisible(true);
            }
            if(f.isVisible()&&nomi.indexOf(f)>=n){
                f.setVisible(false);
            }
        }
        panel.setPreferredSize(new Dimension(450,25*n));
        frame.setSize(500,150+30*n);
    }
}
