/** 
 *  ProgramMap.java
 *  @author Cameron Kellett  Student ID 3468911
 *  
 *  Description:
 *  Grid container view of courses in a program.* 
 * 
 * */

package ams.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ams.controller.ControllerHelper;

@SuppressWarnings("serial")
public class ProgramMap extends JPanel
{

   private final int MAX_COLUMNS = 4;
   private String programTitle;
   private boolean coursePresent = false;
   private JLabel noCoursesLabel;

   public ProgramMap(AMSMainView amsMainView, String programTitle)
   {
      // this.amsMainView = amsMainView;
      this.programTitle = programTitle;
      this.setBackground(Color.WHITE);

      if (coursePresent == false)
      {
         setLayout(new BorderLayout());
         noCoursesLabel = new JLabel(this.programTitle + " HAS NO COURSES");
         this.add(noCoursesLabel);
         this.noCoursesLabel.setHorizontalAlignment(SwingConstants.CENTER);
      }

      else
      {
         setLayout(new GridLayout());
      }

   }

   public void updateProgramMap(String[] courseData)
   {
      this.removeAll();
      int courseDataLength = courseData.length;

      // update course present flag
      if (courseDataLength > 0)
      {
         this.coursePresent = true;
      }

      // Set GridLayout according to number the number
      // elements in the course array.
      // if less than 4 use (0, (numOfElements), 5, 5)
      // if 4 or more, use (0, MAX_COLS, 5, 5)
      if (courseDataLength < MAX_COLUMNS)
      {
         this.setLayout(new GridLayout(0, (courseDataLength), 5, 5));
      }

      else
      {
         this.setLayout(new GridLayout(0, MAX_COLUMNS, 5, 5));
      }

      // for each element in the array
      // create a new cell after tokenizing the entry
      // check if core or elective
      // the generate accoridingly by passing the tokenised string
      // ControllerHelper helper = new ControllerHelper();
      for (int i = 0; i < courseDataLength; i++)
      {
         // String[] courseTokens = helper.stringTokenizer(courseData[i], ":");
         String[] courseTokens =
                  ControllerHelper.STRING_TOKENIZER(courseData[i], ":");
         int courseTypeIndex = courseTokens.length - 1;
         String courseType = courseTokens[courseTypeIndex].toLowerCase();
         if (courseType.equals("core"))
         {
            this.add(new CoreCourseCell(5, courseTokens));
         }

         if (courseType.equals("elective"))
         {
            this.add(new ElectiveCourseCell(5, courseTokens));
         }
      }

      this.remove(noCoursesLabel); // check already removed
      this.revalidate();
      this.repaint();
   }
}
