package ams.view;

@SuppressWarnings("serial")
public class ElectiveCourseCell extends ProgramMapCell 
{

   private static int ELECTIVE_BORDER_VALUE = 0;
   
   public ElectiveCourseCell(int borderValue, String[] courseInfo) 
   
   {
      super(ELECTIVE_BORDER_VALUE, courseInfo);      
   }
}
