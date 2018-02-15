/**
 * ToolBarController.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description:
 * Contains all listeners for ToolBar class components...
 * 
 * 
 * */

package ams.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;

import ams.view.AMSMainView;
import ams.view.ToolBar;
import ams.view.dialog.AddCourseDialog;
import ams.view.dialog.AddProgramDialog;
import ams.view.dialog.RemoveCourseDialog;

public class ToolBarController extends ViewController
{

   private ToolBar toolBar;

   public ToolBarController(ToolBar toolBar)
   {
      super(toolBar.getAMSMainView());
      this.toolBar = toolBar;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      // Reference ApplicationController in AMSMainView.
      // ModelController modelController = this.getModelController();

      AMSMainView amsMainView = this.getAMSMainView();

      // Retrieve source of action.
      JButton invoked = (JButton) e.getSource();

      // Determine source of action then delegate to
      // applicationController
      if (invoked.equals(toolBar.getAddProgram()))
      {
         // ALL THIS DOES IS CALL THE DIALOG BOX
         AddProgramDialog dialog = new AddProgramDialog(amsMainView);
      }

      if (invoked.equals(toolBar.getAddCourse()))
      {
         AddCourseDialog dialog = new AddCourseDialog(amsMainView);
      }

      if (invoked.equals(toolBar.getRemoveCourse()))
      {
         ModelController modelController =
                  this.toolBar.getAMSMainView().getModelController();
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
                  new RemoveCourseDialog(amsMainView, simplifiedCourses);
      }

      if (invoked.equals(toolBar.getResetProgram()))
      {
         // if confirmed

         // get array containing all courses
         // sort so can delete without exceptions (pre reqs)

      }
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      // TODO Auto-generated method stub

   }
}
