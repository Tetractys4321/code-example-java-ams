package ams.view;

@SuppressWarnings("serial")
public class CoreCourseCell extends ProgramMapCell
{

   private final static int CORE_BORDER_VALUE = 5;

   public CoreCourseCell(int borderValue, String[] courseInfo)
   {
      super(CORE_BORDER_VALUE, courseInfo);
   }

}
