package corsacavalli;

import java.io.File;

/**
 * la classe che gestisce il tipo di animale che gareggia per ogni corsia
 * @author Giovanni Ciaranfi
 */
public class Animale{
    private String animale;
    private int velocita;
    private String specie;
    /**
     * il costruttore parametrizzato
     * @param a il nome dell'animale
     */
    public Animale(String a){
        a=a.toLowerCase();
        File f=new File("src/img/icone_animali/"+a+".png");
        if(f.exists()){
            animale=a;
        }
        else{//se l'icona dell'animale scelto non dovesse esistere per oviare a problema metto l'animale cavallo
            animale="cavallo";
        }
        /*
        imposto una velocità per ogni genere di animale, il più possibile simile alla realtà
        */
        if(animale.equals("cavallo")||animale.equals("unicorno")||animale.equals("volpe")||animale.equals("cane")){
            velocita=9;
        }
        else if(animale.equals("capra")||animale.equals("pecora")||animale.equals("rinoceronte")){
            velocita=8;
        }
        else if(animale.equals("panda")||animale.equals("topo")||animale.equals("pesce")){
            velocita=7;
        }
        else{
            velocita=6;
        }
        /*
        la specie serve per disegnare la pista: per ora c'è solo il pesce di acquatico
        ma ho deciso di usare la specie invece che il nome per poter aggiungere in futuro
        altri animali acquatici (o volendo animali saerei)
        */
        if(animale.equalsIgnoreCase("pesce")){
            specie="acquatica";
        }
        else{
            specie="terrena";
        }
    }
    /**
     * 
     * @return l velocità dell'animale di default
     */
    public int getVelocita(){
        return(velocita);
    }
    /**
     * 
     * @return la specie dell'animale (o acquatica o terrena per ora)
     */
    public String getSpecie(){
        return(specie);
    }
    /**
     * 
     * @return il tipo di animale
     */
    public String getAnimale(){
        return(animale);
    }
}
