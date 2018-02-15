/**   AddProgramDialogController.java
 *    @author Cameron Kellett  Student ID 3468911
 * 
 *    Description:
 *    Contains all listeners for addCourseDialog.
 *    
 *    Communicates with ModelController.java  
 *    and AMSMainView.java in order to add 
 *    new Courses to the system. 
 *
 * */

package ams.controller;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ams.model.exception.ProgramException;
import ams.view.AMSMainView;
import ams.view.dialog.AddCourseDialog;

public class AddCourseDialogController extends ViewController implements
                                                             ListSelectionListener
{
   private AddCourseDialog addCourseDialog;

   private String courseCode;
   private String courseTitle;
   private int coreCourse = 1;
   private int electiveCourse = 2;
   private int courseType = 0;
   private String selected = null;
   private String[] preRequisiteCourses = new String[3];
   private int preReqCount = 0;
   private String spaceHolder = "   [ Add Prerequisite ]   ";

   public AddCourseDialogController(AddCourseDialog addCourseDialog)
   {
      super(addCourseDialog.getAMSMainView());
      this.addCourseDialog = addCourseDialog;
   }

   // Development note: Given time, make this more atomic by using helpers
   @Override
   public void actionPerformed(ActionEvent e)
   {
      // *** JCheckBox CONTROL ***
      if (e.getSource() instanceof JCheckBox)
      {
         JCheckBox check = (JCheckBox) e.getSource();

         // CORE_COURSE_CHECK
         if (check.equals(addCourseDialog.getCORE_COURSE_CHECK()))
         {
            if (addCourseDialog.getELECTIVE_COURSE_CHECK().isSelected())
            {
               addCourseDialog.getELECTIVE_COURSE_CHECK().setSelected(false);
            }

            courseType = coreCourse;
         }

         // ELECTIVE_COURSE_CHECK
         if (check.equals(addCourseDialog.getELECTIVE_COURSE_CHECK()))
         {

            if (addCourseDialog.getCORE_COURSE_CHECK().isSelected())
            {
               addCourseDialog.getCORE_COURSE_CHECK().setSelected(false);
            }

            courseType = electiveCourse;
         }
      } // *** END JCheckBox CONTROL

      // *** JButton CONTROL ***
      if (e.getSource() instanceof JButton)
      {
         JButton invoked = (JButton) e.getSource();

         // ADD_PREREQUISITES
         if (invoked.equals(addCourseDialog.getADD_PREREQUISITES()))
         {
            // check if array full. If so send error message...
            if (this.preReqCount >= 3)
            {
               String errorMessage =
                        "You have already reached the maximum allowed prerequisites.";
               JOptionPane.showMessageDialog(addCourseDialog, errorMessage);
            }

            else
            {
               // get input
               String message =
                        "IMPORTANT!\nCourse Code must be 8 alphanumeric characters\n ";
               String title = "Enter Prerequisite Course Code";
               int messageType = 2;
               boolean flag = false;
               
               
               try
               {
                  do
                  {
                     String input =
                              JOptionPane.showInputDialog(addCourseDialog,
                                                          message, title,
                                                          messageType);             
           
   
                     if (input.length() != 8)
                     {
                        String errorMessage =
                                 "Course Code Must be 8 Alphanumeric Characters!";
                        JOptionPane.showMessageDialog(addCourseDialog,
                                                      errorMessage);
                     }

                     else
                     {
                        // Confirm input
                        String confirmMessage = "Add " + input;
                        if (userIntededInputCheck(addCourseDialog, confirmMessage))
                        {

                           // preRequisiteCourses[preReqCount] = " " + input + " ";
                           preRequisiteCourses[preReqCount] = input;
                           preReqCount++;
                           addCourseDialog.setPreRequisites(preRequisiteCourses);
                           flag = true;
                        }
                     }
                  } while (!flag);
               }
               catch (java.lang.NullPointerException e1)
               {
                 // ignore
               }
               
              
            }
         } // END ADD_PREREQUISITES

         // REMOVE_PREREQUISITES
         if (invoked.equals(addCourseDialog.getREMOVE_PREREQUISITES()))
         {
            for (int i = 0; i < preRequisiteCourses.length; i++)
            {
               if (selected.equals(preRequisiteCourses[i]))
               {
                  preRequisiteCourses[i] = spaceHolder;
               }
            }
            preReqCount--;
            addCourseDialog.setPreRequisites(preRequisiteCourses);
         }

         // IF ADD
         if (invoked.equals(addCourseDialog.getEnter()))
         {

            // Prep data to send to model controller
            // validate input
            ModelController modelController = this.getModelController();
            AMSMainView amsMainView = this.getAMSMainView();

            this.courseCode = addCourseDialog.getCourseCode();
            this.courseTitle = addCourseDialog.getCourseTitle();

            String[] nonNullArray = new String[this.preReqCount];
            for (int i = 0; i < this.preReqCount; i++)
            {
               nonNullArray[i] = this.preRequisiteCourses[i];
            }

            int selectedCredits = 0;

            if (this.courseType == 2)
            {
               // get credit value
               // array of radio buttons
               String message =
                        "Choose Credit Points Value for Elective Course";
               String title = "Select Credit Points Value";
               String[] options =
                        new String[] { "6 Points", "12 Points", "Cancel" };

               int selected =
                        JOptionPane
                                 .showOptionDialog(addCourseDialog,
                                                   message,
                                                   title,
                                                   JOptionPane.DEFAULT_OPTION,
                                                   JOptionPane.QUESTION_MESSAGE,
                                                   null, options, null);

               if (selected == 0)
               {
                  selectedCredits = 6;
               }

               if (selected == 1)
               {
                  selectedCredits = 12;
               }

               if (selected == 2)
               {
                  selectedCredits = 0;
                  return;
               }

            }

            // check system update ok
            boolean courseAdded =
                     modelController.addCourse(courseCode, courseTitle,
                                               nonNullArray, selectedCredits,
                                               amsMainView);

            if (courseAdded)
            {
               // enable remove course
               boolean removeCourseButtonEnabled =
                        amsMainView.getToolBar().getRemoveCourse().isEnabled();

               boolean removeCourseMenuItemEnabled =
                        amsMainView.getAppMenuBar().getRemoveCourse()
                                 .isEnabled();

               if (!removeCourseButtonEnabled)
               {
                  amsMainView.getToolBar().getRemoveCourse().setEnabled(true);
               }

               if (!removeCourseMenuItemEnabled)
               {
                  amsMainView.getAppMenuBar().getRemoveCourse()
                           .setEnabled(true);
               }

               // get courses
               String[] courses = modelController.getAllCourses();
               amsMainView.updateProgramMap(courses);

               // update status
               int retrievedCoreCount = modelController.coreCount();
               int retrievedElectiveCount = modelController.electiveCount();
               amsMainView.updateStatusBar(retrievedCoreCount,
                                           retrievedElectiveCount);
               addCourseDialog.dispose();
            }
         }
         // IF CANCEL
         if (invoked.equals(addCourseDialog.getCancel()))
         {
            addCourseDialog.dispose();
         }
      }

   }

   @Override
   public void valueChanged(ListSelectionEvent e)
   {
      if (addCourseDialog.getPreReqList().getSelectedValue().toString() != null)
      {
         selected =
                  addCourseDialog.getPreReqList().getSelectedValue().toString();
      }
   }

}
