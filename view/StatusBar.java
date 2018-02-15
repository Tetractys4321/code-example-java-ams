/**
 *  StatusBar.java
 *  @author Cameron Kellett  Student ID 3468911
 *  
 *  Description:
 *  Displays status info of AMS.
 * 
 * */

package ams.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class StatusBar extends JPanel
{
   private final String DEFAULT_CORE_COUNT = "";
   private final String DEFAULT_ELECTIVE_COUNT = "";
   private final String NO_PROGRAM = "No Program Added";
   
   private JLabel programCodeAndName;
   private JLabel coreCount;
   private JLabel electiveCount;
   private final Border ETCHED_Border = BorderFactory.createEtchedBorder();
   
   public StatusBar(AMSMainView amsMainView)
   {
      this.setLayout(new GridLayout(0, 3));
      this.coreCount = new JLabel(DEFAULT_CORE_COUNT);
      this.electiveCount = new JLabel(DEFAULT_ELECTIVE_COUNT);
      this.programCodeAndName = new JLabel(NO_PROGRAM);
      this.add(programCodeAndName);
      this.add(coreCount);
      this.add(electiveCount);
      
      this.setBorder(ETCHED_Border);
      this.setPreferredSize(getPreferredSize());

   }
 
  
   // updateStatus(programName:String, coreCount:int, electiveCount:int)
   //
   // Updates all components of the status bar
   //
   public void updateStatus(String programCode, String programName, int coreCount, int electiveCount)
   {
      this.programCodeAndName.setText(programCode + " " + programName);
      this.coreCount.setText("Core Courses: " + countToString(coreCount));
      this.electiveCount.setText("Elective Courses " + countToString(electiveCount));
   }  
   
   // updateStatus(coreCount:int, electiveCount:int)
   //
   // Updates only the course counts.
   //
   public void updateStatus(int coreCount, int electiveCount)
   {
      this.coreCount.setText("Core Courses: " + countToString(coreCount));
      this.electiveCount.setText("Elective Courses: " + countToString(electiveCount));
   }
   
   // countToString(count:int)
   //
   // Updates all components of the status bar
   //
   private String countToString(int count)
   {      
      // convert count to string
      String result = String.valueOf(count);      
      return result;
   }   
   
}
