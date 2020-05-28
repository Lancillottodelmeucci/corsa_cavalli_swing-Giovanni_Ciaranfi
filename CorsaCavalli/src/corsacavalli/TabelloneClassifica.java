package corsacavalli;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * la classe che gestisce il pannello della classifica finale
 * @author Giovanni Ciaranfi
 */
public class TabelloneClassifica extends JFrame{
    /**
     * il costruttore parametrizzato
     * @param lista la classifica delle corsie
     */
    public TabelloneClassifica(ArrayList<Corsia> lista){
        super("Classifica della gara");
        JPanel pan=new JPanel(new FlowLayout(FlowLayout.CENTER));
            for(Corsia c:lista){
                int ind=lista.indexOf(c);
                JLabel lab=new JLabel((ind+1)+"° - "+c.getCavallo().getNome());
                if(ind<3){
                    /*
                    anche se un po' brutto l'allineamento è volutamente sfalsato
                    per dare l'idea di un podio: il primo in alto al centro, il
                    secondo a metà a sinistra e il terzo in basso a destra.
                    */
                    lab.setHorizontalAlignment(ind*2);
                }
                else{
                    lab.setHorizontalAlignment(JLabel.LEFT);
                }
                pan.add(lab);
                lab.setPreferredSize(new Dimension(250,30));
            }
            /*
            aggiunta del bottone che permette di salvare i risultati della
            partita
            */
            JButton btnSalva=new JButton("Salva risultati");
            EventoBottoneSalvaClassifica actSalva=new EventoBottoneSalvaClassifica(lista,btnSalva);
            btnSalva.addActionListener(actSalva);
            pan.add(btnSalva);
            this.add(pan);
            this.setSize(300,(lista.size()+1)*40+40);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setVisible(true);
    }
}
