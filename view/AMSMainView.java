/**
 * 
 * AMSMainView.java * 
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description:
 * Top level frame/window containing all GUI specific components.
 * 
 * */

package ams.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import ams.controller.ModelController;
import ams.controller.WindowController;
import ams.model.facade.AMSModel;


@SuppressWarnings("serial")
public class AMSMainView extends JFrame  
{  
   private AMSModel amsModel;
   private ModelController ModelController;
   private WindowController windowController = new WindowController(this);
   private static final String TITLE = "RMIT AMS 3468911";   
   
   // Components   
   private ToolBar toolBar = new ToolBar(this);
   private StatusBar statusBar = new StatusBar(this);
   private ProgramMapSpace programMapSpace = new ProgramMapSpace();
   private AppMenuBar appMenuBar = new AppMenuBar(this);
   private JPanel mainWrapper = new JPanel(new BorderLayout());   
   
   public AMSMainView(AMSModel amsModel)
   {
      super(TITLE); 
      this.amsModel = amsModel;
      this.ModelController = new ModelController(this);
      this.addWindowListener(windowController);
      this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      this.setLayout(new BorderLayout());

      //launch states
      this.toolBar.getAddCourse().setEnabled(false);
      this.toolBar.getRemoveCourse().setEnabled(false);
      this.toolBar.getResetProgram().setEnabled(false);
      this.appMenuBar.getAddCourse().setEnabled(false);
      this.appMenuBar.getRemoveCourse().setEnabled(false);
      this.appMenuBar.getResetProgram().setEnabled(false);
      
      // Panels
      this.add(mainWrapper);
      mainWrapper.add(toolBar, BorderLayout.NORTH);
      mainWrapper.add(programMapSpace, BorderLayout.CENTER);
      mainWrapper.add(statusBar, BorderLayout.SOUTH);
      mainWrapper.setPreferredSize(getMinimumSize());      
      this.setJMenuBar(appMenuBar);      
      this.add(new JScrollPane(mainWrapper, 
                               ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
                               ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
      this.setPreferredSize(getMaximumSize());
      this.setExtendedState(Frame.MAXIMIZED_BOTH);
      this.pack();    
   }


   public ToolBar getToolBar() 
   {
      return toolBar;
   }
   
   public AppMenuBar getAppMenuBar()
   {
      return appMenuBar;
   }

   public StatusBar getStatusBar()
   {
      return statusBar;
   }
  
   public void  updateStatusBar(String programCode, String programTitle, 
                                int coreCount, int electiveCount) 
   {
      // code to update status bar
      statusBar.updateStatus(programCode, programTitle, coreCount, electiveCount);
   }
   
   public void updateStatusBar(int coreCount, int electiveCount) 
   {
      statusBar.updateStatus(coreCount, electiveCount);
      
   }
   
   public ProgramMap getProgramMap() 
   {
      return this.programMapSpace.getProgramMap();
   }
   
   public AMSModel getAMSModel()
   {
      return amsModel;
   }

   public ModelController getModelController() 
   {
      return ModelController;
   }
 
   public void addProgramToMap(String programCode, String programTitle)
   {
      String title = programCode + ": " + programTitle;
      this.programMapSpace.addProgramMap(this, title);
   }
   
   public  void updateProgramMap(String[] courses)
   {
      this.programMapSpace.getProgramMap().updateProgramMap(courses);
   }    
}
