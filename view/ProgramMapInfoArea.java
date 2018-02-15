package ams.view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProgramMapInfoArea extends JPanel
{
   private final Color BG_COLOR = Color.LIGHT_GRAY;
   private JLabel code;
   private JLabel title;
   private JLabel creditPoints;
   private JLabel preReqs; 
   private JLabel courseType; 
   
     public ProgramMapInfoArea(String[] courseInfo)
   {
       boolean instanceHasPreReqs; 
       
       if (courseInfo.length <= 4)
       {
          instanceHasPreReqs = false;
       }
       
       if (courseInfo.length > 4);
       {
          instanceHasPreReqs = true;
       }
      
       int firstPreReqIndex = 3;
       int lastIndexed = courseInfo.length - 1;
       
       String preReqString;
       if (instanceHasPreReqs)
       {
          StringBuilder stringBuilder = new StringBuilder();
          for (int i = firstPreReqIndex; i < lastIndexed; i++)
          {
             stringBuilder.append(courseInfo[i] + "   ");
          }
          
          preReqString = stringBuilder.toString();
       }
       
       else 
       {
          preReqString = "No Prerequisites";
       }      
      
      this.setLayout(new GridLayout(5, 1));
      this.setBackground(this.BG_COLOR);      
      this.code = new JLabel("Code: " + courseInfo[0]);
      this.title = new JLabel("Title: " + courseInfo[1]);
      this.creditPoints = new JLabel("Credit Points: " + courseInfo[2]);
      this.preReqs = new JLabel("Prereqs: " + preReqString);
      this.courseType = new JLabel("Course Type: " + courseInfo[lastIndexed]);
      this.add(this.code);
      this.add(this.title);
      this.add(this.creditPoints);
      this.add(this.preReqs);
      this.add(this.courseType);
   }   
}
