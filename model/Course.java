package ams.model;

/**
 * Course.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 *         Description: Captures common course data shared across all course
 *         types.
 * 
 */

public abstract class Course implements Courses
{
   // --- Course Field Data ---

   private String courseCode;
   private String courseTitle;
   private int creditPoints;
   private String[] preRequisiteCourses;

   // --- Course Constructors ---

   // Course()
   //
   // Default empty constructor
   //
   public Course()
   {

   }

   // Course(courseCode:String, courseTitle:String, creditPoints:int,
   // preRequisites:String[])
   //
   // Creates a Course object with courseCode, courseTitle, creditPoints and an
   // array of prerequisites based on a passed Array of Strings representing course
   // codes.
   //
   public Course(String courseCode, String courseTitle, int creditPoints,
                 String[] preRequisites)
   {
      this.courseCode = courseCode;
      this.courseTitle = courseTitle;
      this.creditPoints = creditPoints;
      this.preRequisiteCourses = preRequisites;
   }

   // --- Methods ---

   // getCourseCode()
   //
   // Returns courseCode string.
   //
   public String getCourseCode()
   {
      return courseCode;
   }

   // getTitle()
   //
   // Returns courseTitle string.
   //
   public String getCourseTitle()
   {
      return courseTitle;
   }

   // getCreditPoints()
   //
   // Returns creditValue
   //
   @Override
   public int getCreditPoints()
   {
      return creditPoints;
   }

   // getPreRequisites()
   //
   // Returns a copy of the preRequisitesArray
   //
   public String[] getPreRequisites()
   {

      if (preRequisiteCourses != null)
      {
         String[] resultArray = new String[preRequisiteCourses.length];
         for (int i = 0; i < preRequisiteCourses.length; i++)
         {
            resultArray[i] = preRequisiteCourses[i];
         }

         return resultArray;
      }

      else
      {
         String[] emptyArray = new String[0];
         return emptyArray;
      }
   }

   // outputCollectionAsString()
   //
   // Returns the preRequisiteCourses string array
   // as a single string using a StringBuilder.
   // If there are no prerequisites the array will
   // return {""}.
   //
   public String outputPreRequisitesAsString()
   {

      if (preRequisiteCourses != null)
      {
         StringBuilder stringBuilder = new StringBuilder();

         // append each entry in the preRequisiteCourses[],
         for (int i = 0; i < preRequisiteCourses.length; i++)
         {
            stringBuilder.append(preRequisiteCourses[i] + ":");
         }

         String returnString = stringBuilder.toString();
         return returnString;
      }

      else
      {
         return "";
      }
   }

   // --- Test Harness Specific Methods and General Testing Methods ---

   // getCode()
   //
   // This method is a redundant version of getCourseCode that
   // is only implemented to satisfy the testHarness
   // failure check...
   // I am including this only because I want to
   // use getCourseCode() in my own code implementations...
   //
   public String getCode()
   {
      return courseCode;
   }
}
