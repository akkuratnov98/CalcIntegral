package calcintegralclient;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;


public class CalcIntegralClient {
    
    String IP;
    int port;
    int percentageCPU;
    
    public CalcIntegralClient(String IP, String port, String percentageCPU ){
        this.IP = IP;
        this.port = Integer.parseInt(port);
        this.percentageCPU = Integer.parseInt(percentageCPU);
    }
    
    public void ConnectToServer(){
        Socket socket;
        try {
            socket = new Socket(IP, port);
            System.out.println("Connect");    
            ClientReader clientReader = new ClientReader(socket);
            clientReader.start();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ошибка подключения к серверу");
        } 
    }
}
