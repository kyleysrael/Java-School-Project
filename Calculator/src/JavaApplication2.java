import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JavaApplication2
{
   public static void main(String[] args)
   {  
      CalculatorFrame frame = new CalculatorFrame();
      //Fix the Size of the JFrame 
      frame.setSize(420, 550);  
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}




//   A frame with a calculator panel.


class CalculatorFrame extends JFrame
{
   public CalculatorFrame()
   {
      setTitle("Calculator");
      CalculatorPanel panel = new CalculatorPanel();
      add(panel);
      pack();
   }
}


//   A panel with calculator buttons and a result display.


class CalculatorPanel extends JPanel
{  
   public CalculatorPanel()
   {  
      setLayout(new BorderLayout());


      result = 0;
      lastCommand = "=";
      start = true;
      
      // add the display


      display = new JButton("0");
      display.setEnabled(false);
      add(display, BorderLayout.NORTH);
      
      ActionListener insert = new InsertAction();
      ActionListener command = new CommandAction();


      // add the buttons in a 4 x 4 grid
      panel = new JPanel();
      panel.setLayout(new GridLayout(5, 4));
      panel.setBackground(Color.GRAY);

      
      addButton("7", insert);
      addButton("8", insert);
      addButton("9", insert);
      addButton("/", command);
      
      addButton("4", insert);
      addButton("5", insert);
      addButton("6", insert);
      addButton("*", command);
      
      addButton("1", insert);
      addButton("2", insert);
      addButton("3", insert);
      addButton("-", command);
      
      addButton("0", insert);
      addButton(".", insert);
      addButton("=", command);
      addButton("+", command);
      
      addButton("√", command);
      addButton("^2", command);
      addButton("C", command);


      add(panel, BorderLayout.CENTER);
   }


   /*
      Adds a button to the center panel.
      @param label the button label
      @param listener the button listener
   */


   private void addButton(String label, ActionListener listener)
   {  
      JButton button = new JButton(label);
      button.addActionListener(listener);
      panel.add(button);
   }



   /*
      This action inserts the button action string to the
      end of the display text.
   */
   private class InsertAction implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         String input = event.getActionCommand();
         if (start) 
         {
            display.setText("");
            start = false;
         }
         display.setText(display.getText() + input);
      }
   }


   /*
      This action executes the command that the button
      action string denotes.
   */
   private class CommandAction implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {  
         String command = event.getActionCommand();


         if (start)
         {  
            if (command.equals("-")) 
            { 
               display.setText(command); 
               start = false; 
            }
            else 
               lastCommand = command;
         }
         else
         {  
            calculate(Double.parseDouble(display.getText()));
            lastCommand = command;
            start = true;
         }
         //clr btn
         if (lastCommand.equals("C")){
              result = 0;
           display.setText("" + result);  
            
         }
      }
   }
   
    

   
   /*
      Carries out the pending calculation. 
      @param x the value to be accumulated with the prior result.
   */
   public void calculate(double x)
   {
       switch (lastCommand) {
           case "+":
               result += x;
               break;
           case "-":
               result -= x;
               break;
           case "*":
               result *= x;
               break;
           case "/":
               result /= x;
               break;
           case "=":
               result = x;
               break;
           case "√":
               result = Math.sqrt(x);
               break;
           case "^2":
               result = x * x;
               break;
           default:
               break;
       }
      display.setText("" + result);
   }
   
   
   private JButton display;
   private JPanel panel;
   private double result;
   private String lastCommand;
   private boolean start;
}
 

