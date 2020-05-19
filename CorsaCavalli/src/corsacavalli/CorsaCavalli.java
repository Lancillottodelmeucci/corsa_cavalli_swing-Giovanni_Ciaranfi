package corsacavalli;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.*;

/**
 * la classe main
 * @author Giovanni Ciaranfi
 */
public class CorsaCavalli {

    /**
     * il metodo main che gestisce la creazione della finestra di inizio per permettere di scegliere il numero di corsie
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame ingresso=new JFrame("Corsa cavalli");//creazione frame
        JPanel panCors=new JPanel();//creazione panel
        JPanel panLung=new JPanel();//creazione panel
        JLabel labSli=new JLabel("Scegliere il numero di corsie: ");//creazione slider per la scelta del numero di corsie
        JSlider sli=new JSlider();
        JLabel numCorsie=new JLabel("3");
        JButton btnAvvio=new JButton("Inizia");//bottone per la creazione dell'ippodromo
        Vector lunghezze=new Vector();
        lunghezze.add(100);
        lunghezze.add(150);
        lunghezze.add(200);
        lunghezze.add(250);
        lunghezze.add(300);
        lunghezze.add(400);
        lunghezze.add(500);
        lunghezze.add(700);
        lunghezze.add(800);
        JComboBox lunghezza=new JComboBox(lunghezze);
        
        panCors.add(labSli);
        
        sli.setMinimum(2);
        sli.setMaximum(10);
        sli.setValue(3);
        
        btnAvvio.setBorderPainted(false);
        EventoSliderIniziale actSli=new EventoSliderIniziale(numCorsie, sli);
        EventoBottoneIniziale actBtn=new EventoBottoneIniziale(btnAvvio,ingresso,sli,lunghezza);
        btnAvvio.addActionListener(actBtn);
        sli.addChangeListener(actSli);
        
        JPanel pan=new JPanel();
        panCors.add(sli);
        panCors.add(numCorsie);
        pan.add(panCors);
        panLung.add(new JLabel("Scegli la dimensione della corsia: "));
        lunghezza.setSelectedIndex(4);
        panLung.add(lunghezza);
        pan.add(panLung);
        JPanel panB=new JPanel();
        panB.add(btnAvvio);
        pan.add(panB);
        panB.setPreferredSize(new Dimension(450,50));
        ingresso.add(pan);
        
        ingresso.setSize(500,150);
        ingresso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ingresso.setVisible(true);
        ingresso.setResizable(false);
    }
    
}
