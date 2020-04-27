package calcintegralclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientReader extends Thread{
    
    Socket socket;
    InputStream in;
    OutputStream out;
    double step = 0.0001;
    
    public ClientReader(Socket socket) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        
    }
    
    @Override
    public void run(){
         try { 
            StringBuffer sb = new StringBuffer();
             while (isInterrupted() == false) {
                int k;
                sb = new StringBuffer();
                StringBuffer buf;
                
                while ((k = in.read()) != -1 && k != ':') {
                    sb.append((char) k);
                }
                int i =0;
                switch (sb.toString()) {        
                    case "!Parameters": 
                        buf = new StringBuffer();
                        while ((k = in.read()) != -1 && k != '$') 
                        {
                            buf.append((char) k);
                        }
                        String[] strings = buf.toString().split("&");
                        double answer = calculatingIntegral(Double.parseDouble(strings[0]), Double.parseDouble(strings[1])); 
                        //out.write(String.valueOf(answer).getBytes());                        
                        break;
                }
             }
         } catch(Exception e){
             
         }
    }
    
    public double calculatingIntegral(double bottomBoard, double topBoard) throws IOException{
        double n = (topBoard-bottomBoard)/step;
        double amount = 0.0;
        double h = (topBoard-bottomBoard)/10;
        for (double j = 0; j<10; j++){
            long nano_startTime = System.nanoTime();
            for (double i = bottomBoard + (j*h); i < bottomBoard+((j+1) *h); i = i + step){
                amount = amount + (1/i);
            }
            long nano_endTime = System.nanoTime();
            System.out.println("Time taken in nano seconds: "
                           + (nano_endTime - nano_startTime));
            String message = "!PercentageOfCompletion:";
            out.write("!PercentageOfCompletion:".getBytes());
        }
        double answer = ((topBoard-bottomBoard)/n) * amount;
        String s = "!Answer:" + answer + "$";
        out.write(s.getBytes());
        return answer;
    }
}
