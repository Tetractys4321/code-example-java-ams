/**
 * ToolBar.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description: 
 * 
 * Provides user access to functional requirements of the application
 * in the form of a tool bar navigation layout
 * 
 *  Components:
 *  
 *          New Program       -  Creates a new university program
 *          Reset Program     -  Resets the current program.             
 *          Add Course        -  Add a course to list of courses.
 *          Remove Course     -  Removes a course from list of courses          
 *
 * 
 **/

package ams.view;
import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import ams.view.AMSMainView;
import ams.controller.ToolBarController;
import ams.controller.ViewController;

@SuppressWarnings("serial")
public class ToolBar extends JPanel
{
   private AMSMainView amsMainView;
   
   private ViewController controller; 
   
   public final String ADD_PROGRAM = "Add Program";
   public final String ADD_COURSE = "Add Course";
   public final String REMOVE_COURSE = "Remove Course";
   public final String RESET_PROGRAM = "Reset Program";
   
   private JButton addProgram;
   private JButton addCourse;
   private JButton removeCourse;
   private JButton resetProgram;
   
   public ToolBar(AMSMainView amsMainView)   
   {

      this.amsMainView = amsMainView;
      this.controller = new ToolBarController(this);
      setLayout(new BorderLayout());

      JToolBar bar = new JToolBar();      
      
      // Create and add button components
      // and register listeners
      this.addProgram = new JButton(ADD_PROGRAM);
      this.addCourse = new JButton(ADD_COURSE);
      this.removeCourse = new JButton(REMOVE_COURSE);
      this.resetProgram = new JButton(RESET_PROGRAM);
      this.addProgram.addActionListener(controller);
      this.addCourse.addActionListener(controller);
      this.removeCourse.addActionListener(controller);
      this.resetProgram.addActionListener(controller);
      bar.add(addProgram);
      bar.add(addCourse);
      bar.add(removeCourse);
      bar.add(resetProgram);
      this.add(bar, BorderLayout.CENTER);
      this.setPreferredSize(this.getPreferredSize());
      
      
   }
   
   public AMSMainView getAMSMainView()
   {
      return amsMainView;
   }
   
   public JButton getAddProgram()
   {
      return addProgram;
   }

   public JButton getAddCourse() {
      return addCourse;
   }

   public JButton getRemoveCourse() 
   {
      return removeCourse;
   }

   public JButton getResetProgram() {
      return resetProgram;
   }
   
   
   

   
   
   
}
