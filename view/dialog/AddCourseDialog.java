/**   ProgramDialog.java
 *    @author Cameron Kellett  Student ID 3468911
 *    
 *    Description:    
 *    Extended DualInputDialog used to retrieve user
 *    input when creating a new course.
 *     
 *    Mapped to AddCourseDialogController.
 *    
 *    
 * */

package ams.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ams.controller.AddCourseDialogController;
import ams.controller.ViewController;
import ams.view.AMSMainView;

@SuppressWarnings("serial")
public class AddCourseDialog extends DualInputDialog
{
   private ViewController addCourseDialogController =
            new AddCourseDialogController(this);

   private final JCheckBox CORE_COURSE_CHECK = new JCheckBox("Core Course");
   private final JCheckBox ELECTIVE_COURSE_CHECK =
            new JCheckBox("Elective Course");
   private final JButton ADD_PREREQUISITES = new JButton("Add");
   private final JButton REMOVE_PREREQUISITES = new JButton("Remove");

   private final static String CODE_CONDITION =
            "Program Code must exactly 8 alphanumeric characters.";
   private final static String TITLE_CONDITION =
            "Program Title must be at least 2 characters in length" +
                     "/nand start with an upper-case letter.";
   private static final String INFORMATION_TEXT = CODE_CONDITION + "\n" +
                                                  TITLE_CONDITION;
   private static final String ENTER_COURSE_TITLE_LABEL =
            "Enter Course Title: ";
   private static final String COURSE_TITLE_EXAMPLE = "eg. Programming 1";
   private static final String ENTER_COURSE_CODE_LABEL = "Enter Course Code: ";
   private static final String COURSE_CODE_EXAMPLE = "eg.PG000001";
   private static final String TITLE = "Create New Course";

   private final JPanel CHECK_BOX_PANEL =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

   private final JPanel PREREQUISITES_PANEL =
            new JPanel(new BorderLayout());

   private final JPanel PREREQUISITES_LIST =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

   private final JPanel PREREQUISITES_BUTTONS =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));

   private String spaceHolder = "   [ Add Prerequisite ]   ";
   private String[] preReqStrings = { spaceHolder, spaceHolder, spaceHolder };

   @SuppressWarnings({ "unchecked", "rawtypes" })
   private JList preReqList = new JList(preReqStrings);

   // private JList preReqList;
   private final Border ETCHED_Border = BorderFactory.createEtchedBorder();

   @SuppressWarnings("unused")
   private boolean hasPreReqs = false;

   public AddCourseDialog(AMSMainView amsMainView)
   {
      super(amsMainView,
            TITLE,
            INFORMATION_TEXT,
            ENTER_COURSE_TITLE_LABEL,
            ENTER_COURSE_CODE_LABEL,
            COURSE_TITLE_EXAMPLE,
            COURSE_CODE_EXAMPLE);

      // CHECK_BOX_PANEL
      this.CORE_COURSE_CHECK.setSelected(true);
      this.CORE_COURSE_CHECK.setBorder(new EmptyBorder(10, 10, 10, 10));
      this.ELECTIVE_COURSE_CHECK.setBorder(new EmptyBorder(10, 10, 10, 10));
      this.CHECK_BOX_PANEL.add(CORE_COURSE_CHECK);
      this.CHECK_BOX_PANEL.add(ELECTIVE_COURSE_CHECK);
      this.CHECK_BOX_PANEL.setBorder(new EmptyBorder(10, 20, 10, 20));
      this.CHECK_BOX_PANEL.setBorder(BorderFactory
               .createTitledBorder(ETCHED_Border,
                                   " Select Course Type [ Required ] "));
      this.getMAIN_PANEL_SPACE().add(CHECK_BOX_PANEL, BorderLayout.SOUTH);
      this.getMAIN_PANEL_SPACE().setBorder(new EmptyBorder(10, 10, 10, 10));

      // PREREQUISITES_PANEL

      this.preReqList.setBackground(this.getBackground());
      this.preReqList.setVisibleRowCount(1);
      this.preReqList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
      this.preReqList.addListSelectionListener(addCourseDialogController);

      this.PREREQUISITES_LIST.add(preReqList);
      this.PREREQUISITES_LIST.setPreferredSize(getPreferredSize());

      this.ADD_PREREQUISITES.setPreferredSize(this.REMOVE_PREREQUISITES
               .getPreferredSize());
      this.PREREQUISITES_BUTTONS.add(ADD_PREREQUISITES);
      // this.PREREQUISITES_BUTTONS.add(Box.createHorizontalStrut(0));
      this.PREREQUISITES_BUTTONS.add(REMOVE_PREREQUISITES);
      this.PREREQUISITES_BUTTONS.setBorder(new EmptyBorder(10, 10, 10, 10));

      this.PREREQUISITES_PANEL.add(PREREQUISITES_LIST, BorderLayout.CENTER);
      this.PREREQUISITES_PANEL.add(PREREQUISITES_BUTTONS, BorderLayout.SOUTH);
      this.PREREQUISITES_PANEL.setBorder(BorderFactory
               .createTitledBorder(ETCHED_Border,
                                   " Prerequisite Courses [ 3 Maximum ] "));
      this.PREREQUISITES_PANEL.setPreferredSize(this.PREREQUISITES_PANEL
               .getMinimumSize());

      this.getBOTTOM_PANEL_SPACE().add(PREREQUISITES_PANEL, BorderLayout.NORTH);
      this.getBOTTOM_PANEL_SPACE().setBorder(new EmptyBorder(10, 10, 10, 10));

      // rename buttons
      this.setEnterAndCancel("Add Course", "Cancel");

      // assign listeners
      this.CORE_COURSE_CHECK.addActionListener(addCourseDialogController);
      this.ELECTIVE_COURSE_CHECK.addActionListener(addCourseDialogController);
      this.ADD_PREREQUISITES.addActionListener(addCourseDialogController);
      this.REMOVE_PREREQUISITES.addActionListener(addCourseDialogController);
      this.getEnter().addActionListener(addCourseDialogController);
      this.getCancel().addActionListener(addCourseDialogController);

      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.setSize(this.getPreferredSize());
      this.setResizable(false);
      this.setLocationRelativeTo(amsMainView);
      this.setVisible(true);
      this.pack();

   }

   public String getCourseTitle()
   {
      return getInputField_1();
   }

   public String getCourseCode()
   {
      return getInputField_2();
   }

   public JCheckBox getCORE_COURSE_CHECK()
   {
      return CORE_COURSE_CHECK;
   }

   public JCheckBox getELECTIVE_COURSE_CHECK()
   {
      return ELECTIVE_COURSE_CHECK;
   }

   public JButton getADD_PREREQUISITES()
   {
      return ADD_PREREQUISITES;
   }

   public JButton getREMOVE_PREREQUISITES()
   {
      return REMOVE_PREREQUISITES;
   }

   public JList getPreReqList()
   {
      return this.preReqList;
   }

   public String[] getPreRequisites()
   {
      return this.preReqStrings;
   }

   public void setPreRequisites(String[] preReqs)
   {
      System.out.println(" AddCourseDialog.setPreRequisites called");
      System.out.println();

      for (int i = 0; i < 3; i++)
      {
         if (preReqs[i] != null)
         {
            this.preReqStrings[i] = preReqs[i];
         }

         else
         {
            this.preReqStrings[i] = spaceHolder;
         }
      }

      this.revalidate();
      this.repaint();
   }
}
