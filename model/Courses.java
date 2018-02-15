package ams.model;

/**
 * Courses.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: Interface for entities capturing course data.  
 * 
 */

public interface Courses 
{
   // getCourseCode()
   //
   // return courseCode string.
   //
   public abstract String getCourseCode();   
   
   // getTitle()
   //
   // Return courseTitle string.
   //
   public abstract String getCourseTitle();   
   
   // getCreditPoints()
   //
   // Returns value of credit points for the course
   //
   public abstract int getCreditPoints();   
   
   // getPreRequisites()
   // 
   // Returns a String array of courseID data
   //
   public abstract String[] getPreRequisites();   
   
   // toString()
   //
   // Returns a string representation of the Course
   // in the format; 
   // courseCode:courseTitle:creditValue:preRequisiteCourseCodes:CourseType
   //
   public abstract String toString();
   
   // outputCollectionAsString()
   //
   // Returns the preRequisiteCourses collection of strings
   // as a single string.
   //
   public String outputPreRequisitesAsString();   
}
