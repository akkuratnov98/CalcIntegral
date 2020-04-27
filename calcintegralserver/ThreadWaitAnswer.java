
package calcintegralserver;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadWaitAnswer extends Thread{
    
    List<ClientThread> clients;
    double doubleBottomBorder, doubleTopBorder;
    double range;
    JPanelAnswer jPanelAnswer1;
    
    public ThreadWaitAnswer(List<ClientThread> clients, double doubleBottomBorder,
            double doubleTopBorder, JPanelAnswer jPanelAnswer1){
        this.clients = clients;
        this.doubleBottomBorder = doubleBottomBorder;
        this.doubleTopBorder = doubleTopBorder;
        this.range = (doubleTopBorder - doubleBottomBorder) / clients.size(); 
        this.jPanelAnswer1 = jPanelAnswer1;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).sendMessage(doubleBottomBorder + (i * range),
            doubleBottomBorder + ((i + 1) * range));
                            
        }
            
    }
    
}
