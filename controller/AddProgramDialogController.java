/**   AddProgramDialogController.java
 *    @author Cameron Kellett  Student ID 3468911
 * 
 *    Description:
 *    Contains all listeners for addProgramDialog
 *    Communicates with ModelController.java  
 *    and AMSMainView.java in order to add 
 *    new programs to the system. 
 *
 * */

package ams.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;

import ams.view.AMSMainView;
import ams.view.dialog.AddProgramDialog;

public class AddProgramDialogController extends ViewController
{

   private AddProgramDialog addProgramDialog;

   public AddProgramDialogController(AddProgramDialog addProgramDialog)
   {
      super(addProgramDialog.getAMSMainView());
      this.addProgramDialog = addProgramDialog;
   }

   // actionPerformed(e:ActionEvent)
   //
   // If Enter is pressed a request is sent to the ModelController
   // to add a new program to the system.
   //
   // If the ModelController is successful
   // a new program is added to the the program map,
   // and status bar is updated.
   //
   // If Cancel is pressed, the dialog closes
   // and nothing occurs.
   //
   @Override
   public void actionPerformed(ActionEvent e)
   {

      JButton invoked = (JButton) e.getSource();

      if (invoked.equals(addProgramDialog.getEnter()))
      {
         // Collect all info and dispose dialogue box.s
         ModelController modelController = this.getModelController();
         AMSMainView amsMainView = this.getAMSMainView();
         String programTitle = addProgramDialog.getProgramTitle();
         String programCode = addProgramDialog.getProgramCode();
         addProgramDialog.dispose();

         // attempt to add the program to the model.
         boolean programAdded =
                  modelController.addProgram(programCode, programTitle,
                                             amsMainView);

         // if successful, update main view.
         if (programAdded)
         {
            String[] programTokens = modelController.getProgram();
            String retrievedProgramCode = programTokens[0];
            String retrievedProgramTitle = programTokens[1];
            int retrievedCoreCount = modelController.coreCount();
            int retrievedElectiveCount = modelController.electiveCount();

            // disable add program options
            boolean addProgramButtonEnabled =
                     amsMainView.getToolBar().getAddProgram().isEnabled();

            boolean addProgramMenuItemEnabled =
                     amsMainView.getAppMenuBar().getAddProgram().isEnabled();

            boolean addCourseMenuItemEnabled =
                     amsMainView.getAppMenuBar().getAddCourse().isEnabled();

            boolean addCourseButtonEnabled =
                     amsMainView.getToolBar().getAddCourse().isEnabled();

            if (addProgramButtonEnabled)
            {
               amsMainView.getToolBar().getAddProgram().setEnabled(false);
            }

            if (addProgramMenuItemEnabled)
            {
               amsMainView.getAppMenuBar().getAddProgram().setEnabled(false);
            }

            if (!addCourseMenuItemEnabled)
            {
               amsMainView.getAppMenuBar().getAddCourse().setEnabled(true);
            }

            if (!addCourseButtonEnabled)
            {
               amsMainView.getToolBar().getAddCourse().setEnabled(true);
            }

            // update status bar
            amsMainView.updateStatusBar(retrievedProgramCode,
                                        retrievedProgramTitle,
                                        retrievedCoreCount,
                                        retrievedElectiveCount);

            // add the program to the program map...
            amsMainView.addProgramToMap(retrievedProgramCode,
                                        retrievedProgramTitle);
         }
      }

      if (invoked.equals(addProgramDialog.getCancel()))
      {
         addProgramDialog.dispose();
      }
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      // TODO Auto-generated method stub
   }

}
