package ams.model;

/**
 * CoreCourse.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 *         Description: Captures course data specific to core course types.
 * 
 */
public class CoreCourse extends Course
{
   // --- CoreCourse Field Data ---

   private static final int CREDIT_VALUE = 12;

   // --- CoreCourse Constructors ---

   // CoreCourse()
   //
   // Default constructor creates empty CoreCourse object
   //
   public CoreCourse()
   {

   }

   // CoreCourse(courseCode:String, courseTitle:String, preRequisites:String[])
   //
   // Creates a Course object with courseCode, courseTitle and set of
   // prerequisites based on a passed Array of Strings representing course
   // codes.
   //
   public CoreCourse(String courseCode, String courseTitle,
                     String[] preRequisites)
   {
      super(courseCode, courseTitle, CREDIT_VALUE, preRequisites);
   }

   // --- Methods ---

   // toString()
   // Returns the string representation of the Course Object
   // courseCode:courseTitle:creditValue:prereqsCourseCodes:type
   // "COSC1073:Programming 1:12:CORE"
   @Override
   public String toString()
   {
      // Calls Course.java helper method outputPreRequisitesAsString()
      String returnString =
               new String(getCourseCode() + ":" + getCourseTitle() + ":"
                          + CREDIT_VALUE + ":" + outputPreRequisitesAsString()
                          + "Core");
      return returnString;
   }
}
