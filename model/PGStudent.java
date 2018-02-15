package ams.model;

import ams.model.exception.EnrollmentException;

/**
 * AbstractStudent.java 
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description: Contains data for Post-Graduate Students Entity. 
 * 
 */

public class PGStudent extends AbstractStudent 
{
   // --- PostGraduate Data Fields ---
   
   // Keeping this set to private and using a getter 
   // method as no reason to make public.
   public static final int MAXIMUM_STUDY_LOAD = 48;
   
   
   // --- PostGraduate Constructors ---
   
   // PostGraduate()
   //
   // Default Constructor creates empty PostGraduate Object
   //
   public PGStudent()
   {
      
   }
   
   // PGStudent(studentID:int, studentFullName: String)
   //
   // Constructor sets student ID and full name.
   //
   public PGStudent(int studentID, String studentFullName)
   {
      super(studentID, studentFullName, MAXIMUM_STUDY_LOAD);
   }
   
   // ----- PGStudent Methods -----
   
   // enrollIntoCourse(courseCode:String)
   //
   // Enrolls Student into the unit owning the passed courseCode.
   //
   // If following conditions are not met, an EnrollmentException will be
   // thrown;
   //
   // 1. Can not be a duplicate enrollment or previously studied unit
   //    that student has passed.
   // 
   // 2. Post Graduate can be overloaded up to 6 credit points, but only if
   //    student has never failed a previously enrolled course.
   // 
   // 3. Post Graduate must have passed ANY ONE of the prerequisites.
   //
   @Override
   public void enrollIntoCourse(Course course) throws EnrollmentException
   {
      String courseCode = course.getCourseCode();
      
      // Exception checks
      
      // Check 1. (Duplicate or pass)
      if (isDuplicateEnrollment(courseCode) || hasPassedCourse(courseCode))
      {
         throw new EnrollmentException(programExceptionMessage(1));
      }
      
      //Check 2 (Overload)
      else if (enrollmentOverLoads(calculateCurrentLoad(),
                                   course.getCreditPoints()))
      {
         throw new EnrollmentException(programExceptionMessage(2));
      }
      
      //Check 3 (Prerequisites)
      else if (preRequisitesNotMet(course.getPreRequisites()))
      {
         throw new EnrollmentException(programExceptionMessage(3));
      } 
      
      else
      {
         enrolledCourses.add(course);
      }
   }
   
   
   // ----- UGStudent Helper Methods -----
   
   // enrollmentOverLoads(currentLoad:int)
   //
   // Query returns true enrolling the student
   // will breach study load conditions.
   //
   @Override 
   protected boolean enrollmentOverLoads(int currentLoad, int coursePoints)
   {
      boolean queryResult = false;
      int total = currentLoad + coursePoints;
      int maxAllowable = MAXIMUM_STUDY_LOAD + 6;

      if (total > maxAllowable || (total > MAXIMUM_STUDY_LOAD &&
                                   total <= maxAllowable && hasFailed()))
      {
         queryResult = true;
      }
      
      return queryResult;
   }
   
   // preRequisitesNotMet(preRequsites:String[])
   //
   // Query returns true if Post Graduate has 
   // passed ANY ONE of the prerequisites.
   //
   @Override
   protected boolean preRequisitesNotMet(String[] preRequisites)
   {
      // Important check.
      // Must account for a zero length array.
      if (preRequisites.length == 0)
      {
         return false;
      }
      
      boolean queryResult = false;
      
      // Flag condition for loop (pass is found)
      // If true, or end or loop reached, the loop ends
      boolean passFound = false;
      for (int i = 0; i < preRequisites.length && !passFound; i++)
      {
         boolean courseResultsHasKey =
                  courseResults.containsKey(preRequisites[i]);
         boolean courseHasPass =
                  courseResults.get(preRequisites[i]).grade().equals("pass");

         if (courseResultsHasKey && courseHasPass)
         {
            passFound = true;
         }
      }

      // If no pass found, queryResult is flagged true.
      // Potential bug if 0 length array passed into method
      // this will trigger and cause incorrect true return
      if (!passFound)
      {
         queryResult = true;
      }
      
      return queryResult;
   }
}
