package ams.controller;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.LinkedHashSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.event.ListSelectionEvent;
import ams.view.AMSMainView;
import ams.view.dialog.RemoveCourseDialog;

public class RemoveCourseDialogController extends ViewController
{
   private RemoveCourseDialog removeCourseDialog;

   // create a list object to hold course info when selected
   private Collection<String> tempStorageSpace = new LinkedHashSet<String>();

   public RemoveCourseDialogController(RemoveCourseDialog removeCourseDialog)
   {
      super(removeCourseDialog.getAmsMainView());
      this.removeCourseDialog = removeCourseDialog;
   }

   @Override
   public void valueChanged(ListSelectionEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e)
   {

      // AMSMainView amsMainView = this.getAMSMainView();

      // CHECK BOXES
      // when checkbox ticked/unticked, add text to storage
      // if already there (an error should occure as is set)
      // remove it....

      if (e.getSource() instanceof JCheckBox)
      {
         JCheckBox invoked = (JCheckBox) e.getSource();

         if (invoked.isSelected())
         {
            String tempString = invoked.getText();
            tempStorageSpace.add(tempString);

         }

         if (!invoked.isSelected())
         {
            tempStorageSpace.remove(invoked.getText()); 
         }
      } // END CHECK BOXES

      if (e.getSource() instanceof JButton)
      {
         JButton invoked = (JButton) e.getSource();

         if (invoked.equals(removeCourseDialog.getREMOVE_COURSE()))
         {

            // confirm input.
            String[] dataArray =
                     tempStorageSpace.toArray(new String[tempStorageSpace
                              .size()]);
            String confirmMessage =
                     "Do you wish to remove the following courses? \n\n";
            String dataString =
                     ControllerHelper.OUTPUT_STRING_ARRAY_AS_STRING(dataArray,
                                                                    true);
            String outputString = confirmMessage + dataString + "\n";

            if (ControllerHelper.USER_INTENDED_INPUT_CHECK(removeCourseDialog,
                                                           outputString))
            {
               // create a string array to send..
               // for each element in list, get first 8 characters as a string
               // and input into string array to send
               // using substring(0, 7)

               String[] extractedCourseCode = new String[dataArray.length];
               for (int i = 0; i < dataArray.length; i++)
               {
                  String courseCode = dataArray[i].substring(0, 8);
                  extractedCourseCode[i] = courseCode;
               }

               // send data to modelController... then dispose...
               AMSMainView amsMainView = this.getAMSMainView();
               ModelController modelController =
                        this.getAMSMainView().getModelController();

               boolean coursesRemoved =
                        modelController.removeCourses(extractedCourseCode,
                                                      amsMainView);

               if (coursesRemoved)
               {
                  // get courses and update view
                  String[] courses = modelController.getAllCourses();
                  if (courses[0].equals(""))
                  {
                     boolean removeCourseButtonEnabled =
                              amsMainView.getToolBar().getRemoveCourse()
                                       .isEnabled();

                     boolean removeCourseMenuItemEnabled =
                              amsMainView.getAppMenuBar().getRemoveCourse()
                                       .isEnabled();

                     if (removeCourseButtonEnabled)
                     {
                        amsMainView.getToolBar().getRemoveCourse()
                                 .setEnabled(false);
                     }

                     if (removeCourseMenuItemEnabled)
                     {
                        amsMainView.getAppMenuBar().getRemoveCourse()
                                 .setEnabled(false);
                     }
                  }

                  amsMainView.updateProgramMap(courses);

                  // update status
                  int retrievedCoreCount = modelController.coreCount();
                  int retrievedElectiveCount = modelController.electiveCount();
                  amsMainView.updateStatusBar(retrievedCoreCount,
                                              retrievedElectiveCount);
                  removeCourseDialog.dispose();
               }
            }
         }
         if (invoked.equals(removeCourseDialog.getCANCEL()))
         {
            removeCourseDialog.dispose();
         }

      }

   } // END actionPerformed

}
