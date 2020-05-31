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
    private ArrayList<JTextField> nomi;//l'insieme dei field contenenti i nomi degli animali
    private JPanel panel;//il pannello di cui cambiare la dimensione
    private JFrame frame;//il frame di cui cambiare la dimensione
    private ArrayList<JComboBox<String>> animali;//l'insieme delle select contententi il tipo di animale
    /**
     * il costruttore parametrizzato
     * @param p l'etichetta sulla quale mostrare il valore
     * @param s lo slider che viene modificato
     * @param n l'insieme dei JTextField contenenti i nomi degli animali
     * @param pan il pannello a cui cambiare la dimensione
     * @param f il frame a cui cambiare la dimensione
     * @param a l'insieme delle JComboBox contententi il tipo di animale
     */
    public EventoSliderIniziale(JLabel p,JSlider s,ArrayList<JTextField> n,JPanel pan,JFrame f,ArrayList<JComboBox<String>> a){
        numCorsie=p;
        slider=s;
        nomi=n;
        panel=pan;
        frame=f;
        animali=a;
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
    /**
     * il metodo che mostra o nasconde gli animali a seconda della quantità di corsie scelte
     * @param n il numero di corsie da mostrare
     */
    private void ordinaCorsie(int n){
        /*
        metodo da ottimizzare: utilizzare un ciclo solo per modificare
        contemporaneamente fielde select (visibili/invisibili)
        */
        for(JTextField f:nomi){
            if(!f.isVisible()&&nomi.indexOf(f)<n){
                f.setVisible(true);
            }
            if(f.isVisible()&&nomi.indexOf(f)>=n){
                f.setVisible(false);
            }
        }
        for(JComboBox f:animali){
            if(!f.isVisible()&&nomi.indexOf(f)<n){
                f.setVisible(true);
            }
            if(f.isVisible()&&nomi.indexOf(f)>=n){
                f.setVisible(false);
            }
        }
        panel.setPreferredSize(new Dimension(550,30*n));
        frame.setSize(600,180+30*n);
    }
}
