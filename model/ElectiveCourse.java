package ams.model;

/**
 * ElectiveCourse.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: Captures course data specific to elective course types.   
 * 
 */

public class ElectiveCourse extends Course 
{
   // --- ElectiveCourse Constructors ---   
   
   // ElectiveCourse()
   //
   // Default Constructor creates an empty ElectiveCourse object
   //
   public ElectiveCourse()
   {
      
   }   
   
   // ElectiveCourse(courseCode:String, courseTitle:String, preRequisites:String[])
   //
   // Creates a Course object with courseCode, courseTitle and set of 
   // prerequisites based on a passed Array of Strings representing course codes.
   //
   public ElectiveCourse(String courseCode, String courseTitle, int creditValue ,String[] preRequisites)
   {
      super(courseCode, courseTitle, creditValue, preRequisites);
   }

   // --- Courses Interface Methods --- 

   
   // toString()
   // Returns the string representation of the Course Object
   // courseCode:courseTitle:creditPoints:type

   @Override
   public String toString()
   {
      String returnString = new String(getCourseCode() + ":" + getCourseTitle() + ":" 
                                 + getCreditPoints()  + ":" + outputPreRequisitesAsString() 
                                 + "Elective");      
      return returnString;
   }

   
   
   
   
   
   
}
