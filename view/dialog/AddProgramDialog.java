/**   ProgramDialog.java
 *    @author Cameron Kellett  Student ID 3468911
 *    
 *    Description:    
 *    Extended DualInputDialog used to retrieve user
 *    input when creating a new program.
 *     
 *    Mapped to AddProgramDialogController.
 *    
 *    
 * */

package ams.view.dialog;

import javax.swing.JDialog;
import ams.controller.AddProgramDialogController;
import ams.controller.ViewController;
import ams.view.AMSMainView;

@SuppressWarnings("serial")
public class AddProgramDialog extends DualInputDialog
{
   private ViewController addProgramDialogController =
            new AddProgramDialogController(this);

   // Using static final to ensure each instance conforms to these settings.
   private static final String CODE_CONDITION =
            "Program Code must exactly 6 alphanumeric characters.";
   private static final String TITLE_CONDITION =
            "Program Title must be at least 2 characters in length.";
   private static final String INFORMATION_TEXT = CODE_CONDITION + "\n" +
                                                  TITLE_CONDITION;
   private static final String ENTER_PROGRAM_TITLE_LABEL =
            "Enter Program Title: ";
   private static final String PROGRAM_TITLE_EXAMPLE =
            "eg. Bachelor of Computer Science";
   private static final String ENTER_PROGRAM_CODE_LABEL =
            "Enter Program Code: ";
   private static final String PROGRAM_CODE_EXAMPLE = "eg. BP0001";
   private static final String TITLE = "Create New Program";

   // AddProgramDialog(amsMainView:AMSMainView)
   //
   // Creates DualInputDialog with Specified
   // title, input labels and examples, related
   // to adding a new program.
   //
   // It also adds an non editable
   // text area for detailing add program
   // input conditions.
   //
   public AddProgramDialog(AMSMainView amsMainView)
   {
      super(amsMainView,
            TITLE,
            INFORMATION_TEXT,
            ENTER_PROGRAM_TITLE_LABEL,
            ENTER_PROGRAM_CODE_LABEL,
            PROGRAM_TITLE_EXAMPLE,
            PROGRAM_CODE_EXAMPLE);

      this.getEnter().addActionListener(addProgramDialogController);
      this.getCancel().addActionListener(addProgramDialogController);
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.setLocationRelativeTo(amsMainView);
      this.setResizable(false);
      this.setVisible(true);
   }

   public String getProgramTitle()
   {
      return getInputField_1();
   }

   public String getProgramCode()
   {
      return getInputField_2();
   }
}
