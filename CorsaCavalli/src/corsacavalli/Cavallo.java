package corsacavalli;

/**
 * la classe del cavallo
 * @author Giovanni Ciaranfi
 */
public class Cavallo {
    private String nome;//il nome del cavallo
    private int num;//il numero di corsia del cavallo
    private int velocita;//la velocità di avanzamento del cavallo
    /**
     * il costruttore parametrizzato, che imposta anche la velocità del cavallo
     * @param n il nome del cavallo
     * @param num il numero di corsia del cavallo
     */
    public Cavallo(String n,int num){
        nome=n;
        this.num=num;
        velocita=(int)(Math.random()*10+1);//creo una velocità tra 1 e 10 per rendere più reale la corsa
    }
    /**
     * 
     * @return il valore della velocità del cavallo
     */
    public int getVelocita(){
        return(velocita);
    }
}
