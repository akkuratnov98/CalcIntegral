package calcintegralserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSlider;


public class ClientThread extends Thread{
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private Double answer;
    private JSlider jSlider1;
    private List<ClientThread> clients;
    private JLabel jLableAnswer;
    private JPanelAnswer jPanelAnswer;
    private JPanelLoad jPanelLoad;
    
    public ClientThread(Socket socket, Double answer, List<ClientThread> clients,
           JPanelAnswer jPanelAnswer, JPanelLoad jPanelLoad) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();      
        this.answer = answer;
        this.clients = clients;
        this.jPanelAnswer = jPanelAnswer;
        this.jPanelLoad = jPanelLoad;
        this.jSlider1 = jPanelLoad.getJSlider1();
        this.jLableAnswer = jPanelAnswer.getJLableAnswer();
    }
    
    public void sendMessage(double bottomBorder, double topBorder){
        String message = "!Parameters:" + String.valueOf(bottomBorder) + '&' + String.valueOf(topBorder) + '$';
        try {
            out.write(message.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
    
     @Override
    public void run() {
        try { 
            StringBuffer sb = new StringBuffer();
            //while (isInterrupted() == false && !"!Answer".equals(sb.toString())) {
            while (isInterrupted() == false) {
                int k;
                sb = new StringBuffer();
                StringBuffer buf;
                
                while ((k = in.read()) != -1 && k != ':') {
                    sb.append((char) k);
                }
                
                switch (sb.toString()) {        
                    case "!PercentageOfCompletion":   
                        synchronized (jSlider1){
                            jSlider1.setValue(10/clients.size()+ jSlider1.getValue());
                        }          
                        break;
                        
                    case "!Answer":
                        buf = new StringBuffer();
                        while ((k = in.read()) != -1 && k != '$') 
                        {
                            buf.append((char) k);
                        }
                        synchronized (jLableAnswer){
                            jPanelAnswer.setVisible(true);
                            jSlider1.setValue(100);
                            try{
                            jLableAnswer.setText(String.valueOf(Double.parseDouble(jLableAnswer.getText()) + Double.parseDouble(buf.toString())));
                            } catch (Exception e)
                            {
                                jLableAnswer.setText(String.valueOf(Double.parseDouble(buf.toString())));
                            }
                        }                                                 
                        break;
                }
             }
        } catch(Exception e){
            
        }
    }
}


