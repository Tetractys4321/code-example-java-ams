/**   ModelController.java * 
 *    @author Cameron Kellett  Student ID 3468911
 * 
 *    Description:
 * 
 *    1. MODEL COMMUNICATION
 *    2. USER INPUT VALIDATION
 *  
 *     
 *    - User input validation is not performed i.e.
Program:
code - must be exactly 6 alphanumeric characters
title - min length of 2 characters (no character type restrictions)
Course:
code - must be exactly 8 alphanumeric characters
title - min length of 2 characters (first character must be an upper-case letter)
 *  
 * 
 * */

package ams.controller;

import javax.swing.JOptionPane;

import ams.model.CoreCourse;
import ams.model.Course;
import ams.model.ElectiveCourse;
import ams.model.Program;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;
import ams.view.AMSMainView;

public class ModelController
{


   // *** DATA FIELDS ***
   private AMSModel amsModel;

   // input validation rules
   private final int PROGRAM_CODE_REQUIRED_LENGTH = 6;
   private final int PROGRAM_TITLE_MIN_LENGTH = 2;

   private final int COURSE_CODE_REQUIRED_LENGTH = 8;
   private final int COURSE_TITLE_MIN_LENGTH = 2;

   // Delimiter used for extracting data from
   // retrieved strings. Ideally, this would be
   // also be retrieved from facade.
   private final String DELIM = ":";

   public ModelController(AMSMainView amsMainView)
   {

      this.amsModel = amsMainView.getAMSModel();
   }

   // *** METHODS ***

   // Notes: requires main view for parent
   // replace amsMainView with ViewController so the calling
   // controller can be passed in.
   public boolean addProgram(String programCode, String programTitle,
                             AMSMainView amsMainView)
   {
      boolean result = false;

      // Confirm dialog
      String confirmInputString =
               ("Do you wish to create a program using the inputs?\n\n"
                + "Program Code: " + programCode + "\n" + "Program Title: " +
                programTitle + "\n\n");

      if (!userIntededInputCheck(amsMainView, confirmInputString))
      {
         result = false;
      }

      else
      {
         if (!newProgramInputValidationCheck(programCode, programTitle,
                                             amsMainView))
            result = false;

         else
         {
            result = true;
            amsModel.addProgram(new Program(programCode, programTitle));
         }
      }

      return result;
   }

   // getProgram()
   //
   // Returns string representation of the Program
   //
   public String[] getProgram()
   {
      String programString = amsModel.getProgram().toString();
      // String[] tokens = this.helper.stringTokenizer(programString, DELIM);
      String[] tokens = ControllerHelper.STRING_TOKENIZER(programString, DELIM);
      return tokens;
   }

   //
   //
   //
   public boolean addCourse(String courseCode, String courseTitle,
                            String[] preRequisites, int creditValue,
                            AMSMainView amsMainView)
   {
      boolean result = false;

      // INPUT CONFIRMATION
      int credits;
      if (creditValue == 6)
      {
         credits = 6;
      }

      else
      {
         credits = 12;
      }

      String preReqString =
               ControllerHelper.OUTPUT_STRING_ARRAY_AS_STRING(preRequisites);
      String confirmInputString =
               ("You have entered the following course details:\n"
                + courseCode + " | " + courseTitle + "\n"
                + "Credits: " + credits + "\n"
                + "Prerequisites:\n" + preReqString + "\n"
                + "Proceed?");

      if (!userIntededInputCheck(amsMainView, confirmInputString))
      {
         return false;
      }

      if (!newCourseInputValidationCheck(courseCode, courseTitle, amsMainView))
      {
         return false;
      }

      else
      {
         if (creditValue == 0)
         {
            try
            {
               this.amsModel.addCourse(new CoreCourse(courseCode, courseTitle,
                                                      preRequisites));
               result = true;
            }
            catch (ProgramException e)
            {
               JOptionPane.showMessageDialog(amsMainView, e.getMessage());
               return false;
            }
         }

         if (creditValue == 6)
         {
            try
            {
               this.amsModel.addCourse(new ElectiveCourse(courseCode,
                                                          courseTitle,
                                                          creditValue,
                                                          preRequisites));
               result = true;
            }
            catch (ProgramException e)
            {
               JOptionPane.showMessageDialog(amsMainView, e.getMessage());
               return false;
            }
         }

         if (creditValue == 12)
         {
            try
            {
               this.amsModel.addCourse(new ElectiveCourse(courseCode,
                                                          courseTitle,
                                                          creditValue,
                                                          preRequisites));
               result = true;
            }
            catch (ProgramException e)
            {
               JOptionPane.showMessageDialog(amsMainView, e.getMessage());
               return false;
            }
         }
      }

      return result;
   }

   public boolean removeCourses(String[] courseCodes, AMSMainView amsMainView)
   {
      boolean result = false;

      for (int i = 0; i < courseCodes.length; i++)
      {
         try
         {
            amsModel.removeCourse(courseCodes[i]);

         }
         catch (ProgramException e)
         {

            JOptionPane.showMessageDialog(amsMainView, e.getMessage());

            return false;

         }
      }

      result = true;

      return result;

   }

   public String[] getAllCourses()
   {

      Course[] coursesArray = this.amsModel.getAllCourses();

      if (coursesArray == null)
      {
         String[] array = { "" };
         return array;
      }

      else
      {
         String[] stringArray = new String[coursesArray.length];
         for (int i = 0; i < coursesArray.length; i++)
         {
            stringArray[i] = coursesArray[i].toString();
         }

         return stringArray;
      }
   }

   public int coreCount()
   {
      return amsModel.countCoreCourses();

   }

   public int electiveCount()
   {
      return amsModel.countElectiveCourses();
   }

   // *** HELPER METHODS ***

   // userIntededInputCheck(view:AMSMainView, message:String)
   //
   // Creates a generic confirmation dialog using AMSMainView as parent
   // and specified message. (Yes / No) options only.
   //

   private boolean userIntededInputCheck(AMSMainView view, String message)
   {

      int option =
               JOptionPane.showConfirmDialog(view, message,
                                             "Input Confirmation", 2);

      if (option == JOptionPane.YES_OPTION)
      {
         return true;
      }

      else
      {
         return false;
      }

   }

   // newProgramInputValidationCheck(programCode:String,
   // programTitle:String)
   //
   // Input Validation helper called by this.addProgram()
   //
   //
   private boolean newProgramInputValidationCheck(String programCode,
                                                  String programTitle,
                                                  AMSMainView view)
   {
      boolean codeCheck = false;
      boolean titleCheck = false;
      boolean result = false;

      if (programCode.length() == PROGRAM_CODE_REQUIRED_LENGTH)
         codeCheck = true;

      if (programTitle.length() >= PROGRAM_TITLE_MIN_LENGTH)
         titleCheck = true;

      if (!codeCheck || !titleCheck)
      {
         // User input error message
         String codeErrorMessage =
                  "Program Code is not valid. Must be 6 characters.";
         String titleErrorMessage =
                  "Program Title is not valid. Must be minimum of 2 characters.";

         if (!codeCheck && titleCheck)
            JOptionPane.showMessageDialog(view, codeErrorMessage);
         if (codeCheck && !titleCheck)
            JOptionPane.showMessageDialog(view, titleErrorMessage);
         else if (!codeCheck && !titleCheck)
         {
            JOptionPane.showMessageDialog(view, codeErrorMessage + "\n"
                                                + titleErrorMessage);
         }
      }

      else
         result = true;

      return result;
   }

   private boolean newCourseInputValidationCheck(String courseCode,
                                                 String coureTitle,
                                                 AMSMainView view)
   {
      // code - must be exactly 8 alphanumeric characters
      // title - min length of 2 characters (first character must be an
      // upper-case letter)
      if (courseCode.length() != this.COURSE_CODE_REQUIRED_LENGTH)
      {
         JOptionPane.showMessageDialog(view, "Course Code Invalid!");
         return false;
      }

      if (coureTitle.length() < this.COURSE_TITLE_MIN_LENGTH)
      {
         JOptionPane.showMessageDialog(view, "Course Title Too Short!");
         return false;
      }

      if (!Character.isUpperCase(coureTitle.charAt(0)))
      {
         JOptionPane
                  .showMessageDialog(view,
                                     "Course Title Does Not Start With Upper Case!");
         return false;
      }

      else
      {
         return true;
      }
   }
}
