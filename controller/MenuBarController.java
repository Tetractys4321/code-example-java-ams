/**
 * MenuBarController.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description:
 * 
 * Contains all listeners for MenuBar
 * 
 * */

package ams.controller;

import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import ams.view.AppMenuBar;
import ams.view.dialog.AddCourseDialog;
import ams.view.dialog.AddProgramDialog;
import ams.view.dialog.RemoveCourseDialog;

public class MenuBarController extends ViewController
{

   private AppMenuBar menuBar;

   public MenuBarController(AppMenuBar menuBar)
   {
      super(menuBar.getAmsMainView());
      this.menuBar = menuBar;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      // Reference ApplicationController in AMSMainView
      // REMOVE THIS as is it a SUb class of View Controller!
      ModelController modelController =
               menuBar.getAmsMainView().getModelController();

      // Retrieve source of action
      JMenuItem invoked = (JMenuItem) e.getSource();

      // Determine source of action then delegate to
      // applicationController
      if (invoked.equals(menuBar.getAddProgram()))

      {
         AddProgramDialog dialog = new AddProgramDialog(this.getAMSMainView());
      }

      if (invoked.equals(menuBar.getExitApplication()))
      {
         String message = "Are your sure you want to quit?";
         int option =
                  JOptionPane.showConfirmDialog(menuBar.getAmsMainView(),
                                                message, "Closing Application",
                                                0, 1);

         if (option == JOptionPane.YES_OPTION)
         {
            System.exit(0);
         }

         else
         {

         }
      }

      if (invoked.equals(menuBar.getAddCourse()))
      {
         AddCourseDialog dialog = new AddCourseDialog(this.getAMSMainView());
      }

      if (invoked.equals(menuBar.getRemoveCourse()))
      {
         String[] courses = modelController.getAllCourses();
         String[] simplifiedCourses = new String[courses.length];

         // Extract only code and title
         // for each string in courses array
         // tokenise then generate new string using code and title only
         // then add that string to simplifiedCourses array
         for (int i = 0; i < courses.length; i++)
         {
            // tokenize
            String[] tokens =
                     ControllerHelper.STRING_TOKENIZER(courses[i], ":");
            // new String
            String codeAndTitle = tokens[0] + " | " + tokens[1];
            // add to simplifiedCourses
            simplifiedCourses[i] = codeAndTitle;
         }

         RemoveCourseDialog dialog =
                  new RemoveCourseDialog(this.getAMSMainView(),
                                         simplifiedCourses);
      }
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      // TODO Auto-generated method stub

   }
}
