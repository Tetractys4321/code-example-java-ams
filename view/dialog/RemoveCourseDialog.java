package ams.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ams.controller.RemoveCourseDialogController;
import ams.controller.ViewController;
import ams.view.AMSMainView;

@SuppressWarnings("serial")
public class RemoveCourseDialog extends JDialog 
{
     // connections
     private ViewController RemoveCourseDialogController;
     private AMSMainView amsMainView;
     
     // data
     private final static String TITLE = "Removes Course";     
     private JCheckBox[] checkBoxArray;  

     // buttons
     private final JButton REMOVE_COURSE = new JButton("Remove Course");
     private final JButton CANCEL = new JButton("    Cancel   ");
     
     // panels
     //private final JPanel CHECK_BOX_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
     private final JPanel CHECK_BOX_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
     private final JPanel BUTTON_PANEL = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));     
     private final JPanel COURSE_SPACE = new JPanel(new BorderLayout()); 
     private final JPanel BUTTON_SPACE = new JPanel(new BorderLayout()); 
     private final JPanel TOP_SPACE = new JPanel(new BorderLayout()); 
     private final JPanel BOTTOM_SPACE = new JPanel(new BorderLayout());
     
     //preferred Dimension settings
     private final int DEFAULT_WIDTH = 430;
     private int checkBoxPanelHeight;
     private int courseSpaceHeight = 350;
     private int buttonSpaceHeight;
     private Dimension checkBoxPanelDimension;
     private Dimension courseSpaceDimension;
     private Dimension buttonSpaceDimension;
     
     // requires a string [] of courseName + courseTitle
     public RemoveCourseDialog(AMSMainView amsMainView, String[] courses)
     {
        super(amsMainView, TITLE, true);
        this.amsMainView = amsMainView;
        RemoveCourseDialogController = new RemoveCourseDialogController(this);        
        
        this.setLayout(new BorderLayout());
        
        // generate array of check boxes
        // add listener
        // add to CHECK_BOX_PANEL
        checkBoxArray = new JCheckBox[courses.length];        
        for (int i = 0; i < courses.length;i++)
        {           
           checkBoxArray[i] = new JCheckBox(courses[i]);
           checkBoxArray[i].addActionListener(RemoveCourseDialogController);
           this.CHECK_BOX_PANEL.add(checkBoxArray[i]);           
        }
        
        // CHECK_BOX_PANEL SETTINGS
        checkBoxPanelHeight = (int)this.CHECK_BOX_PANEL.getPreferredSize().getHeight();
        checkBoxPanelDimension = new Dimension(DEFAULT_WIDTH, checkBoxPanelHeight);
        
        this.CHECK_BOX_PANEL.setMaximumSize(checkBoxPanelDimension);
        this.CHECK_BOX_PANEL.setPreferredSize(checkBoxPanelDimension);    
        
        // COURSE_SPACE SETTINGS
        this.COURSE_SPACE.add(CHECK_BOX_PANEL, BorderLayout.CENTER);
        //courseSpaceHeight = (int)this.COURSE_SPACE.getPreferredSize().getHeight();
        courseSpaceDimension = new Dimension(DEFAULT_WIDTH, courseSpaceHeight);
        this.COURSE_SPACE.setPreferredSize(courseSpaceDimension);
        this.COURSE_SPACE.setSize(this.COURSE_SPACE.getPreferredSize());
       
        
        
        // TOP_SPACE SETTINGS
        this.TOP_SPACE.add(COURSE_SPACE, BorderLayout.CENTER);
        this.TOP_SPACE.setSize(this.TOP_SPACE.getPreferredSize());        
        this.add(TOP_SPACE, BorderLayout.CENTER);
        
        // BUTTON_SPACE SETTINGS
        this.REMOVE_COURSE.addActionListener(RemoveCourseDialogController);
        this.CANCEL.addActionListener(RemoveCourseDialogController);
        this.BUTTON_PANEL.add(REMOVE_COURSE);
        this.BUTTON_PANEL.add(CANCEL);        
        this.BUTTON_SPACE.add(BUTTON_PANEL, BorderLayout.CENTER);
        buttonSpaceHeight = (int)this.BUTTON_SPACE.getPreferredSize().getHeight();
        buttonSpaceDimension = new Dimension(DEFAULT_WIDTH, buttonSpaceHeight);
        this.BUTTON_SPACE.setPreferredSize(buttonSpaceDimension);
        this.BUTTON_SPACE.setSize(this.BUTTON_SPACE.getPreferredSize());
        
        // BOTTOM_SPACE SETTINGS
        this.BOTTOM_SPACE.add(BUTTON_SPACE, BorderLayout.CENTER);        
        this.BOTTOM_SPACE.setSize(this.BOTTOM_SPACE.getPreferredSize());
        this.add(BOTTOM_SPACE, BorderLayout.SOUTH);
        
        // TOP LEVEL SETTINGS
        this.setMinimumSize(getPreferredSize());
        this.setResizable(false);
        this.pack(); 
        this.setLocationRelativeTo(amsMainView);
        this.setVisible(true);

     }

     
     public AMSMainView getAmsMainView() 
     {
        return amsMainView;
     }
     
     // need?
     public JCheckBox[] getCheckBoxArray() 
     {
        return checkBoxArray;
     }
     
     // need?
     public String getCheckedStringAtIndex(int i)
     {
        return this.checkBoxArray[i].getText();
     }


   public JButton getREMOVE_COURSE() 
   {
      return REMOVE_COURSE;
   }


   public JButton getCANCEL() 
   {
      return CANCEL;
   }
   
     

   


}

