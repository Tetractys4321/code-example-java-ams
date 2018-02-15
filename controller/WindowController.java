package ams.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import ams.view.AMSMainView;

public class WindowController implements WindowListener 
{

   AMSMainView amsMainView;
   
   public WindowController(AMSMainView amsMainView) 
   {
      this.amsMainView = amsMainView;
   }

   @Override
   public void windowActivated(WindowEvent arg0) 
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowClosed(WindowEvent arg0) 
   {

   }

   @Override
   public void windowClosing(WindowEvent arg0) {
      
      String message = "Are your sure you want to quit?";
      int option = JOptionPane.showConfirmDialog(amsMainView, message, "Closing Application", 0, 1);
      
      if (option == JOptionPane.YES_OPTION)
      {
         System.exit(0);
      }

      else
      {
         
      }

   }

   @Override
   public void windowDeactivated(WindowEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowDeiconified(WindowEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowIconified(WindowEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void windowOpened(WindowEvent arg0) {
      // TODO Auto-generated method stub

   }

}
