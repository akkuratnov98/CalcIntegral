package calcintegralserver;

import javax.swing.JOptionPane;

public class CheckingValues {
    String CountConnected, topBorder, bottomBorder;
    
    public CheckingValues(String CountConnected, String topBorder, String bottomBorder){
        this.CountConnected = CountConnected;
        this.bottomBorder =bottomBorder;
        this.topBorder = topBorder;
    }
    
    @SuppressWarnings("empty-statement")
    public boolean checking(){
        try{
        if(!(Integer.parseInt(CountConnected) > 0))
           throw new Exception("Число клиентов должо быть больше 0");
        double doubleBottomBorder = Double.parseDouble(bottomBorder);
        double doubleTopBorder = Double.parseDouble(topBorder);
        if((doubleBottomBorder > doubleTopBorder) || (doubleBottomBorder <= 0) ||
                (doubleTopBorder > 100000)) 
            throw new Exception("Число вышло за границы разрешенного диапазона");
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Введено не правльное число");
            return false;
        } catch(Exception e){           
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }        
        return true;
    }
}
