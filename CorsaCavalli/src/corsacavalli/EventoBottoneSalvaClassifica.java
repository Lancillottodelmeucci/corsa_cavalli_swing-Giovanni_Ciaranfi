package corsacavalli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 * la classe per l'evento del salvataggio su file
 * @author Giovanni Ciaranfi
 */
public class EventoBottoneSalvaClassifica implements ActionListener{
    private ArrayList<Corsia> classifica;//la classifica di arrivo
    private String dataCorsa;//la data in cui finisce la corsa e viene richiesta la classifica
    private JButton btn;//il bottone per salvare la classifica
    private ArrayList<Corsia> infortunati;
    /**
     * il costruttore parametrizzato
     * @param inGara la lista delle corsie
     * @param b il bottone da disabilitare
     */
    public EventoBottoneSalvaClassifica(ArrayList<Corsia> inGara,ArrayList<Corsia> fuoriGara,JButton b){
        classifica=inGara;
        infortunati=fuoriGara;
        GregorianCalendar cal=new GregorianCalendar();
        /*
        utilizzo l'operatore ternario per ogni argomento per ottenere sempre le
        due cifre
        */
        String y=""+cal.get(Calendar.YEAR);
        String M=cal.get(Calendar.MONTH)+1<10?"0"+(cal.get(Calendar.MONTH)+1):""+(cal.get(Calendar.MONTH)+1);
        String d=cal.get(Calendar.DAY_OF_MONTH)<10?"0"+cal.get(Calendar.DAY_OF_MONTH):""+cal.get(Calendar.DAY_OF_MONTH);
        String h=cal.get(Calendar.HOUR_OF_DAY)<10?"0"+cal.get(Calendar.HOUR_OF_DAY):""+cal.get(Calendar.HOUR_OF_DAY);
        String m=cal.get(Calendar.MINUTE)<10?"0"+cal.get(Calendar.MINUTE):""+cal.get(Calendar.MINUTE);
        String s=cal.get(Calendar.SECOND)<10?"0"+cal.get(Calendar.SECOND):""+cal.get(Calendar.SECOND);
        dataCorsa=y+M+d+"-"+h+m+s;
        btn=b;
    }
    /**
     * il metodo che crea il file per il salvataggio dei dati della corsa terminata
     * @param e l'evento di default
     */
    public void actionPerformed(ActionEvent e){
        btn.setEnabled(false);
        File f=new File("src/risultati/"+dataCorsa+".txt");
        try {
            f.createNewFile();
        }
        catch (IOException ex) {}
        FileWriter fW;
        try {
            fW = new FileWriter(f,false);
            fW.write("La classifica della gara è la seguente:\n"+stilaClassifica());
            fW.close();
        }
        catch (IOException ex) {}
        btn.setText("Risultati salvati");
    }
    /**
     * 
     * @return la classifica da inserire nel file di salvataggio
     */
    private String stilaClassifica(){
        String ret="";
        int ind=1;
        for(Corsia c:classifica){
            ret+="Posizione "+ind+": "+c.toString()+"\n";
            ind++;
        }
        
        Iterator<Corsia> i;
        for(i=infortunati.iterator();i.hasNext();){
            Corsia c=i.next();
            ret+=c.toString()+" ha subito un infortunio durante la gara";
            if(i.hasNext()){
                ret+="\n";
            }
        }
//        for(Corsia c:infortunati){
//            ret+=c.toString()+" ha subito un infortunio durante la gara";
//            if()
//        }
        return(ret);
    }
}
