package corsacavalli;

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
        JPanel pan=new JPanel();//creazione panel
        JLabel labSli=new JLabel("Scegliere il numero di corsie: ");//creazione slider per la scelta del numero di corsie
        pan.add(labSli);
        JSlider sli=new JSlider();
        sli.setMinimum(2);
        sli.setMaximum(10);
        sli.setValue(3);
        JLabel numCorsie=new JLabel("3");
        JButton btnAvvio=new JButton("Inizia");//bottone per la creazione dell'ippodromo
        EventoSliderIniziale actSli=new EventoSliderIniziale(numCorsie, sli);
        EventoBottoneIniziale actBtn=new EventoBottoneIniziale(btnAvvio,ingresso,sli);
        btnAvvio.addActionListener(actBtn);
        sli.addChangeListener(actSli);
        pan.add(sli);
        pan.add(numCorsie);
        pan.add(btnAvvio);
        ingresso.add(pan);
        ingresso.setSize(500,100);
        ingresso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ingresso.setVisible(true);
        ingresso.setResizable(false);
    }
    
}
