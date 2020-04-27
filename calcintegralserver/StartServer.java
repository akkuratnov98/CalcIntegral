

package calcintegralserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSlider;


public class StartServer extends Thread{
    Double answer = 1.0;
    List<ClientThread> clients;
    JLabel jLabelCountConnected, jLableAnswer;
    JSlider jSlider1;
    JPanelAnswer jPanelAnswer;
    JPanelLoad jPanelLoad;
            
    public StartServer (Double answer, List<ClientThread> clients, JFrameCalc jFrameCalc){
        //this.answer = answer;
        this.clients = clients;
        this.jLabelCountConnected = jFrameCalc.getJLabelCountConnected();
        this.jPanelAnswer = jFrameCalc.getJPanelAnswer();
        this.jPanelLoad = jFrameCalc.getJPanelLoad();
    }
    
    @Override
    public void run() {
        try {
            RunSerwer();
        } catch (IOException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void RunSerwer() throws IOException {        
        ServerSocket server = new ServerSocket(2000);
        System.out.println("Server Run, PORT:2000");
        while (true) { 
            Socket socket = server.accept();
            System.out.println("Client accepted");
            ClientThread clientThread = new ClientThread(socket, answer, clients,
                    jPanelAnswer, jPanelLoad);
            clientThread.start();            
            clients.add(clientThread);
            jLabelCountConnected.setText(String.valueOf(clients.size()));
        }
    }
}
