package ams.model;

/**
 * AbstractStudent.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description: Contains data and Accessors/Mutators for Student Entity 
 * 
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import ams.model.exception.EnrollmentException;

public abstract class AbstractStudent implements Student
{
   // --- AbstractStudent Data Fields ---

   private int studentID;
   private String studentFullName;
   private int maximumStudyLoad;
   private int currentCareerPoints; // modified at addResult
   protected Set<Course> enrolledCourses = new HashSet<Course>();
   protected Map<String, Result> courseResults = new HashMap<String, Result>();

   // --- AbstractStudent Constructors ---

   // AbstractStudent()
   //
   // Default Empty Constructor
   //
   public AbstractStudent()
   {

   }

   // AbstractStudent(studentID:int, studentFullName: String)
   //
   // Constructor sets student ID and full name.
   //
   public AbstractStudent(int studentID, String studentFullName,
                          int maximumStudyLoad)
   {
      this.studentID = studentID;
      this.studentFullName = studentFullName;
      this.maximumStudyLoad = maximumStudyLoad;
      currentCareerPoints = 0;
   }

   // --- AbstractStudent Methods ---

   // getFullName()
   //
   // Returns full name of student.
   //
   @Override
   public String getFullName()
   {
      return studentFullName;
   }

   // getStudentID()
   //
   // Returns Student Identification Number
   //
   @Override
   public int getStudentID()
   {
      return studentID;
   }

   // enrollIntoCourse(courseCode:String)
   //
   // Enrolls entity into the unit owning the passed courseCode.
   // If following conditions are not met, an EnrollmentException will be
   // thrown;
   //
   // 1. Can not be a duplicate enrollment or previously studied unit
   // that has been passed (PA)
   // 2. Under Graduate can not be overloaded.
   // 3. Post Graduate can be overloaded up to 6 credit points, but only if
   // student has never failed a previously enrolled course.
   // 4. Under Graduate must have passed ALL prerequisite courses prior to
   // enrolling.
   // 5. Post Graduate must have passed ANY ONE of the prerequisites.
   //
   @Override
   public abstract void enrollIntoCourse(Course course)
            throws EnrollmentException;

   // withdrawFromCourse(courseCode:String)
   //
   // Removes the Course owning the passed courseCode from
   // the entities enrolled courses.
   //
   // EnrollmentException thrown if the courseCode does not
   // exist in enrolled courses.
   //
   @Override
   public void withdrawFromCourse(Course course) throws EnrollmentException
   {
      // Exception Check
      if (!enrolledCourses.contains(course))
      {
         throw new EnrollmentException("Student is not enrolled in "
                                       + course.getCourseCode());
      }

      else
      {
         enrolledCourses.remove(course);
      }
   }

   // calculateCurrentLoad()
   //
   // Adds the total amount of creditPoints
   // owned by Course objects contained within
   // the enrolledCourses collection.
   //
   @Override
   public int calculateCurrentLoad()
   {
      // iterate through enrolled Courses and add
      // the credit value...

      Iterator<Course> iter = enrolledCourses.iterator();

      int load = 0;
      while (iter.hasNext())
      {
         Course currentCourse = iter.next();
         load += currentCourse.getCreditPoints();
      }

      return load;
   }

   // calculateCareerPoints()
   //
   // Returns the student's career points.
   // I have delegated task of maintaining
   // the total value to class level variable
   // currentCareerPoints which is incremented
   // whenever a passed result is added.
   //
   @Override
   public int calculateCurrentCareerPoints()
   {
      return currentCareerPoints;
   }

   // addResult(courseCode:String, grade:String)
   //
   // Adds a Course Result to courseResults and adds
   // creditPoints to currentCareerPoints if a pass is achieved.
   //
   // Returns false if course associated with the result
   // does not exist in currently enrolled courses.
   //
   // Also removes the course from currently enrolled courses
   //
   @Override
   public boolean addResult(Result result)
   {
      // compare course codes to ensure present...
      // Tried to use containsKey(result.getCourse())
      // for determining if the object was present, but kept
      // getting false return. I could not determine why.
      // String should be same value.

      /*
       * // Testing containsKey... System.out.println("");
       * System.out.println("*** System Test ***");
       * System.out.println("Assigning boolean value to containsKey method return"
       * ); boolean containsResult =
       * courseResults.containsKey(result.getCourseCode());
       * System.out.println("containsResult = " + containsResult);
       * System.out.println("*** End System Test ***"); System.out.println("");
       */

      boolean coursePresent = false;
      Course[] enrolledArray = getCurrentEnrollment();
      for (int i = 0; i < enrolledArray.length && !coursePresent; i++)
      {
         if (result.getCourseCode().equals(enrolledArray[i].getCourseCode()))
         {
            coursePresent = true;

            // Testing
            // for use as comparable when investigating containsKey method
            // System.out.println("coursePresent is " + coursePresent);
         }
      }

      if (!coursePresent)
      {
         return false;
      }

      else
      {
         // add result and IF PASSED add the credits to the
         // students currentCareerPoints.
         courseResults.put(result.getCourseCode(), result);

         if (result.grade().equals("pass"))
         {
            currentCareerPoints += result.getCourse().getCreditPoints();
         }

         // remove course from enrolledCourse collection
         enrolledCourses.remove(result.getCourse());

         return true;
      }
   }

   // getResult(courseCode:String)
   //
   // Returns the grade value for passed courseCode.
   // (Not used. Will consider deleting...)
   //
   @Override
   public String getResult(String courseCode)
   {
      Result result = courseResults.get(courseCode);
      String returnString = result.grade();
      return returnString;
   }

   // getResults()
   //
   // Returns the courseResults collection as an array.
   //
   @Override
   public Result[] getAllResults()
   {
      Result[] returnArray = courseResults.values().toArray(new Result[0]);
      return returnArray;
   }

   // getCurrentEnrollment()
   //
   // Returns an array of Courses the student
   // is enrolled in.
   //
   @Override
   public Course[] getCurrentEnrollment()
   {
      Course[] returnArray = enrolledCourses.toArray(new Course[0]);
      return returnArray;
   }

   // toString()
   //
   // Returns String representation of student in the format
   // studentID:fullName:maximumLoad
   //
   @Override
   public String toString()
   {
      String string = new String(getStudentID() + ":" + getFullName() + ":"
                                 + maximumStudyLoad);
      return string;
   }

   // --- AbstractStudent Helper Methods ---

   // isDuplicateEnrollment(courseCode:String)
   //
   // Query Returns true if the passed courseCode
   // is already in the enrolledCourses Collection.
   //
   protected boolean isDuplicateEnrollment(String courseCode)
   {
      Iterator<Course> iter = enrolledCourses.iterator();

      boolean queryResult = false;
      while (iter.hasNext() && queryResult == false)
      {
         String currentCourseCode = iter.next().getCourseCode();
         if (currentCourseCode.equals(courseCode))
         {
            queryResult = true;
         }
      }

      return queryResult;
   }

   // hasPassedCourse(courseCode:String))
   //
   // Query returns true if the courseCode
   // has already been passed by the student.
   //
   protected boolean hasPassedCourse(String courseCode)
   {
      boolean queryResult = false;

      if (courseResults.containsKey(courseCode)
          && courseResults.get(courseCode).equals("pass"))
      {
         queryResult = true;
      }

      return queryResult;
   }

   // enrollmentOverLoads(currentLoad:int)
   //
   // Query returns true if enrolling the student
   // will breach study load conditions.
   //
   protected abstract boolean enrollmentOverLoads(int currentLoad,
                                                  int coursePoints);

   // hasFailed()
   //
   // Returns true if the student has previously failed a course.
   //
   protected boolean hasFailed()
   {
      // Create a key set and use as iterator for search...
      Set<String> keySet = courseResults.keySet();
      Iterator<String> iter = keySet.iterator();

      // Loops through the the courseResults Map
      // and tests result elements for a "fail" grade.
      //
      // If true, the queryResult flag is set to
      // true and the loop exits.
      //
      // Otherwise continues checking until end of map.
      boolean queryResult = false;
      while (iter.hasNext() && queryResult == false)
      {
         String currentKey = iter.next();
         Result currentResult = courseResults.get(currentKey);

         if (currentResult.grade().equals("fail"))
         {
            queryResult = true;
         }
      }

      return queryResult;
   }

   // preRequisitesNotMet(preRequsites:String[])
   //
   // Query checks if student has not met any of the passed
   // preRequisites.
   //
   protected abstract boolean preRequisitesNotMet(String[] preRequisites);

   // programExceptionMessage(condition:int)
   //
   // Returns a String representing a message that
   // can be used when throwing a programException
   // to represent the condition breach
   //
   protected String programExceptionMessage(int condition)
   {
      String returnString = "defaultProgramExceptionMessage";

      if (condition == 1)
      {
         returnString = "The student is either already enrolled " +
                        "or has passed the course.";
      }

      if (condition == 2)
      {
         returnString = "Enrolling in this course will cause the " +
                        "student to exceed maximum study load criteria.";
      }

      if (condition == 3)
      {
         returnString = "Student has not met prerequisites.";
      }

      return returnString;
   }
}
