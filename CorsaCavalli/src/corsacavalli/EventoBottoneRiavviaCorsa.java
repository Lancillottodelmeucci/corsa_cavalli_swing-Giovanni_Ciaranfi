/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSlider;

/**
 *
 * @author giova
 */
public class EventoBottoneRiavviaCorsa implements ActionListener{
    private Ippodromo ippodromo;
    /**
     * il costruttore parametrizzato
     * @param b il bottone a cui cambiare testo (funzione da rimuovere)
     * @param f il frame da passare per la crazione dell'ippodromo
     * @param s lo slider da cui prendere il numero di corsie
     */
    public EventoBottoneRiavviaCorsa(Ippodromo i){
        ippodromo=i;
    }
    /**
     * il metodo che cambia nome al pulsante e crea il nuovo ippodromo
     * @param e l'evento
     */
    @Override
    public void actionPerformed(ActionEvent e){
        //
        ippodromo.riavvioCorsa();
    }
}
