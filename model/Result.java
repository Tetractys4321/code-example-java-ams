package ams.model;

/**
 * Result.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 *         Description: Contains data for student results.
 * 
 */
public class Result
{
   // --- Result Data Fields ---

   private String grade;
   private Course course;

   // --- Result Constructors ---

   // Result()
   //
   // Default result constructor creates an empty Result object
   //
   public Result()
   {

   }

   // Result(course:Course, grade:boolean)
   //
   // Constructs Result object using a passed Course object
   // and grade value.
   //
   public Result(Course course, boolean passed)
   {
      this.course = course;

      if (passed)
      {
         grade = "pass";
      }

      else
      {
         grade = "fail";
      }
   }

   // --- Result Methods ---

   // getCourseCode()
   //
   // Returns couresCode string
   //
   public String getCourseCode()
   {
      return course.getCourseCode();
   }

   // getGrade()
   //
   // Returns grade string
   //
   public String grade()
   {
      return grade;
   }

   // setGrade(courseCode:String)
   //
   // set the grade to passed string value
   //
   public void setGrade(String grade)
   {
      this.grade = grade;
   }

   // toString()
   //
   // Creates a string representation of the result.
   // courseCode:grade
   //
   public String toString()
   {
      String returnString = new String(course.getCourseCode() + ":" + grade);
      return returnString;
   }

   // --- Test Harness Specific Methods and General Testing Methods ---

   // getCourse()
   //
   // This method is a redundant and is only implemented to
   // satisfy the testHarness.
   // I am including this only because I want to
   // use getCourseCode() in my own code implementations...
   //
   public Course getCourse()
   {
      return course;
   }

   // getCode()
   //
   // This method is a redundant version of getCourseCode.
   // I am including this only because I want to
   // use getCourseCode() in my own code implementations...
   //
   public String getCode()
   {
      return course.getCourseCode();
   }

}
