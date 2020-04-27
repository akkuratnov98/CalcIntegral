package calcintegralserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalcIntegralServer {       
        
    public static void main(String[] args) throws IOException {
        Double ansewr = 0.0;
        List<ClientThread> clients =new ArrayList<ClientThread>();       
        
        JFrameCalc frameCalc = new JFrameCalc(ansewr, clients);
        frameCalc.setVisible(true);
        
        StartServer startServer = new StartServer(ansewr, clients, frameCalc);
        startServer.start();

    }
    
}
