/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corsacavalli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;

/**
 *
 * @author giova
 */
public class EventoBottoneRiavvio implements ActionListener{
    public EventoBottoneRiavvio(Ippodromo i,JFrame f){
        Vector v=i.getIppodromo();
//        i=new Ippodromo((String)v.get(0),(int)v.get(1),(JFrame)v.get(2),(int)v.get(3),(ArrayList)v.get(4),(ArrayList)v.get(5));
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        
    }
}
