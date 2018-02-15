/**
 * MenuBar.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description: 
 * 
 * Provides user access to functional requirements of the application
 * in the form of a menu navigation layout
 * 
 *  Components:
 *  
 *  File:   
 *          New Program       -  Creates a new university program
 *          Refresh           -  Refreshes the display(TBC)
 *          Exit              -  Exits the application
 *          
 *          
 *  Edit:   
 *          Add Course        -  Add a course to list of courses.
 *          Remove Course     -  Removes a course from list of courses
 *          Reset Program     -  Resets the current program.        
 *           
 *  
 * 
 * */


package ams.view;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import ams.controller.MenuBarController;

@SuppressWarnings("serial")
public class AppMenuBar extends JMenuBar 
{

   // *** MAPPED REFERENCES ***
   
   private AMSMainView amsMainView;   
   private MenuBarController controller; 
   
   // *** COMPONENTS ***   
   
   // Menu Tags (File)
   private final String FILE = "File";
   private final String ADD_PROGRAM = "Add Program";
   private final String EXIT_APPLICATION = "Exit Application";  
   
   // Menu Tags (Edit)
   private final String EDIT = "Edit";   
   private final String ADD_COURSE = "Add Course";
   private final String REMOVE_COURSE = "Remove Course";
   private final String RESET_PROGRAM = "Reset Program";
   
   // File Components 
   private JMenu file;
   private JMenuItem addProgram;
   private JMenuItem exitApplication;
   
   // Edit Components
   private JMenu edit;
   private JMenuItem addCourse;
   private JMenuItem removeCourse;
   private JMenuItem resetProgram;  
   
   //boreder
   private final Border ETCHED_Border = BorderFactory.createEtchedBorder();
   
   
   // *** CONSTRUCTORS ***
   
   // MenuBar(amsMainView:AMSMainView)
   //
   // Description Required
   //
   public AppMenuBar(AMSMainView amsMainView)
   {
      // Assign main view
      this.amsMainView = amsMainView;
      this.controller = new MenuBarController(this);
      
      // Assign LayoutManager
      this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
      this.setBorder(ETCHED_Border);
      
      // Create and add File components, register listeners
      this.file = new JMenu(FILE);      
      this.addProgram = new JMenuItem(ADD_PROGRAM);
      this.addProgram.addActionListener(controller);      
      this.exitApplication = new JMenuItem(EXIT_APPLICATION);
      this.exitApplication.addActionListener(controller);      
      this.add(file);
      this.file.add(addProgram);
      this.file.add(exitApplication);
      
      // Create and add Edit components, register listeners
      this.edit = new JMenu(EDIT);      
      this.addCourse = new JMenuItem(ADD_COURSE);
      this.addCourse.addActionListener(controller);      
      this.removeCourse = new JMenuItem(REMOVE_COURSE);
      this.removeCourse.addActionListener(controller);      
      this.resetProgram = new JMenuItem(RESET_PROGRAM);
      this.resetProgram.addActionListener(controller);      
      this.add(edit);
      this.edit.add(addCourse);
      this.edit.add(removeCourse);
      this.edit.add(resetProgram); 
      


      
   }



   public AMSMainView getAmsMainView() 
   {
      return amsMainView;
   }

   public JMenuItem getAddProgram() 
   {
      return addProgram;
   }

   public JMenuItem getExitApplication() 
   {
      return exitApplication;
   }

   public JMenuItem getAddCourse() 
   {
      return addCourse;
   }

   public JMenuItem getRemoveCourse() 
   {
      return removeCourse;
   }

   public JMenuItem getResetProgram() 
   {
      return resetProgram;
   }
   
   

   
   
   
   
   
}
