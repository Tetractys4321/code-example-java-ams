package ams.model;

import ams.model.exception.EnrollmentException;

/**
 * AbstractStudent.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 *         Description: Contains data for Under-Graduate Students Entity.
 * 
 */

public class UGStudent extends AbstractStudent
{
   // --- UnderGraduate Data Fields ---

   public static final int MAXIMUM_STUDY_LOAD = 60;

   // --- UnderGraduate Constructors ---

   // UnderGraduate()
   //
   // Default constructor creates empty UnderGraduate object
   //
   public UGStudent()
   {

   }

   // UGStudent(studentID:int, studentFullName: String)
   //
   // Constructor sets student ID and full name.
   //
   public UGStudent(int studentID, String studentFullName)
   {
      super(studentID, studentFullName, MAXIMUM_STUDY_LOAD);
   }

   // enrollIntoCourse(courseCode:String)
   //
   // Enrolls student into the unit owning the passed courseCode.
   //
   // If following conditions are not met, an EnrollmentException will be
   // thrown;
   //
   // 1. Can not be a duplicate enrollment or previously studied unit
   // that student has passed.
   //
   // 2. Under Graduate can not be overloaded.
   //
   // 3. Under Graduate must have passed ALL prerequisite courses prior to
   // enrolling.
   //
   @Override
   public void enrollIntoCourse(Course course) throws EnrollmentException
   {
      // Exception checks

      String courseCode = course.getCourseCode();

      // Check 1. (Duplicate or pass)
      if (isDuplicateEnrollment(courseCode) || hasPassedCourse(courseCode))
      {
         throw new EnrollmentException(programExceptionMessage(1));
      }

      // Check 2 (Overload)
      else if (enrollmentOverLoads(calculateCurrentLoad(),
                                   course.getCreditPoints()))
      {
         throw new EnrollmentException(programExceptionMessage(2));
      }

      // Check 3 (Prerequisites)
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
      if ((currentLoad + coursePoints) > MAXIMUM_STUDY_LOAD)
      {
         queryResult = true;
      }

      return queryResult;
   }

   // preRequisitesNotMet(preRequsites:String[])
   //
   // Query returns true if Under Graduate has passed
   // ALL prerequisites in the passed preRequisites array.
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
      for (int i = 0; i < preRequisites.length && !queryResult; i++)
      {
         // Condition 1.
         // Is key present?
         String currentKey = preRequisites[i];
         boolean courseResultsHasKey = courseResults.containsKey(currentKey);

         // Condition 2.
         // If key is present, has the student passed?
         Result result = courseResults.get(currentKey);
         boolean hasPassed = false;
         if (courseResultsHasKey && result.grade().equals("pass"))

         {
            hasPassed = true;
         }

         // If either condition is not met, the queryResult is flagged true.
         if (!courseResultsHasKey || !hasPassed)
         {
            queryResult = true;
         }
      }

      return queryResult;
   }
}
