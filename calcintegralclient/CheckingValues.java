package calcintegralclient;

import javax.swing.JOptionPane;

public class CheckingValues {
    String IP, Port, percentageCPU;
    
    public CheckingValues(String IP, String Port, String percentageCPU){
        this.IP = IP;
        this.Port =Port;
        this.percentageCPU = percentageCPU;
    }
    
//    @SuppressWarnings("empty-statement")
//    public boolean checking(){
//        try{
//        if(!(Integer.parseInt(CountConnected) > 0))
//           throw new Exception("Число клиентов должо быть больше 0");
//        double doubleBottomBorder = Double.parseDouble(bottomBorder);
//        double doubleTopBorder = Double.parseDouble(topBorder);
//        if((doubleBottomBorder > doubleTopBorder) || (doubleBottomBorder <= 0) ||
//                (doubleTopBorder > 100000)) 
//            throw new Exception("Число вышло за границы разрешенного диапазона");
//        } catch (NumberFormatException e){
//            JOptionPane.showMessageDialog(null, "Введено не правльное число");
//            return false;
//        } catch(Exception e){           
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            return false;
//        }        
//        return true;
//    }
}
