/**   ViewController.java
 *    @author Cameron Kellett  Student ID 3468911
 *    
 *    
 *    Description:   Abstract class that all subclasses extend
 *    
 *    1.    PREPARATION OF DATA FOR VIEW DISPLAY UPDATES
 *    2.    HANDLES INPUT STORAGE AND RETRIEVAL
 * 
 * */

package ams.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import ams.view.AMSMainView;

public abstract class ViewController implements ActionListener,
                                    ListSelectionListener
{

   private AMSMainView amsMainView;
   private ModelController modelController;

   // ViewController (amsMainView:AMSMainView)
   //
   // Receives and AMSMainView instance as a reference
   // and also references its instance of ModelController
   //
   public ViewController(AMSMainView amsMainView)
   {
      this.amsMainView = amsMainView;
      this.modelController = amsMainView.getModelController();
   }

   // *** METHODS ***

   @Override
   public abstract void actionPerformed(ActionEvent arg0);

   public ModelController getModelController()
   {
      return modelController;
   }

   public AMSMainView getAMSMainView()
   {
      return amsMainView;
   }

   public boolean userIntededInputCheck(Component view, String message)
   {

      int option =
               JOptionPane.showConfirmDialog(view, message,
                                             "Input Confirmation", 2);

      if (option == JOptionPane.YES_OPTION)
         return true;

      else
         return false;
   }

}
