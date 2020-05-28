package corsacavalli;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * la classe che gestisce la creazione dell'ippodromo
 * @author Giovanni Ciaranfi
 */
public class EventoBottoneIniziale implements ActionListener{
    private JButton btn;//il bottone da modificare
    private JFrame frame;//il frame da passare all'ippodromo
    private JSlider slider;//lo slider per il numero di corsie
    private JComboBox boxDim;//la combobox per la dimensione delle corsie
    private ArrayList<JTextField> fields;//l'insieme dei field contenenti i nomi degli animali
    private ArrayList nomi;//la lista di nomi degli animali
    private ArrayList<JComboBox<String>> boxAnim;//l'insieme delle select per il tipo di animale
    private ArrayList<Animale> animali;//la lista dei tipi di animali
    /**
     * il costruttore parametrizzato
     * @param b il bottone a cui cambiare testo (funzione da rimuovere)
     * @param f il frame da passare per la crazione dell'ippodromo
     * @param s lo slider da cui prendere il numero di corsie
     * @param box l'elenco a discesa da cui scegliere la dimensione delle corsie
     * @param n l'array contenente i JTextField dei nomi
     * @param a l'array contenente i JComboBoc dei tipi di animali
     */
    public EventoBottoneIniziale(JButton b,JFrame f,JSlider s,JComboBox box,ArrayList<JTextField> n,ArrayList<JComboBox<String>> a){
        /*
        utilizzo componenti "statiche" come slider e combobox per evitare la
        generazione di errori da eventuali input errati da parte dell'utente
        */
        btn=b;
        frame=f;
        slider=s;
        this.boxDim=box;
        nomi=new ArrayList();
        fields=n;
        boxAnim=a;
        animali=new ArrayList<>();
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
        for(int i=0;i<slider.getValue();i++){
            nomi.add(fields.get(i).getText());
            animali.add(new Animale((String)boxAnim.get(i).getSelectedItem()));
        }
        Ippodromo i=new Ippodromo("Ippodromo Ciaranfi",slider.getValue(),frame,(int)boxDim.getSelectedItem(),nomi,animali);
    }
}
