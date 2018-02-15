package ams.model;

/**
 * Program.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: Captures data about a Program and its courses.  
 * 
 * 
 */

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import ams.model.Course;
import ams.model.exception.ProgramException;

public class Program
{
   // --- Program Data Fields ---

   private String programCode;
   private String programTitle;
   private int coreCourseCount;
   private int electiveCourseCount;

   // Basis for using a Set is to easily ensure no duplication.
   // Understand could have used used a Map instead to
   // make lookups slightly easier. In this case I don't
   // think makes that much of a difference.
   private Collection<Course> courseCollection = new LinkedHashSet<Course>();

   // --- Program Constructors ---

   // Program()
   //
   // Default Program constructor creates empty Program object
   //
   public Program()
   {

   }

   // Program(programCode:String, programTitle:String)
   //
   // Creates a program with passed program code and program title
   // and sets Core and Elective counters to 0.
   public Program(String programCode, String programTitle)
   {
      this.programCode = programCode;
      this.programTitle = programTitle;
      coreCourseCount = 0;
      electiveCourseCount = 0;
   }

   // --- Program Methods ---

   // addCourse(course:Course) throws ProgramException
   //
   // Adds a course to courseCollection.
   //
   // Throws a ProgramException if the passed course already exists
   // or any of its prerequisites do not exist.
   //
   // If both checks pass, course is added and method increments the
   // applicable course counter.
   //
   public void addCourse(Course course) throws ProgramException
   {
      // Check 1. (Course already exists)
      
      if (courseExists(course))
      {
         

         
         throw new ProgramException(course.toString() + " already exists.");
      }


      // Check 2. (Prerequisites.)
      if (isMissingPreRequisite(course))
      {
         throw new ProgramException(course.toString() +
                                    " has missing prerequisites.");
      }
      

      // Add course.
      // Requires "instanceof" key word. Permitted for countCoreCorses
      // and countElectiveCorses purposes.
      else
      {

         if (course instanceof ElectiveCourse)
         {

            courseCollection.add(course);
            electiveCourseCount++;
         }

         else if (course instanceof CoreCourse)
         {

            courseCollection.add(course);
            coreCourseCount++;
         }

         else
         {
            System.out.println("Error: Course Type not defined...");
         }
      }
   }

   // removeCourse(courseCode:String) throws ProgramException
   //
   // Removes the Course owning the passed courseCode from
   // the Course Collection and decrements the count
   //
   // Throws a ProgramException if the passed courseCode is
   // a prerequisite course for another Course in the courseCollection.
   //
   public void removeCourse(String courseCode) throws ProgramException
   {
      // Check if prerequisite.
      boolean courseIsPreRequisite = isCoursePreRequisite(courseCode);

      if (courseIsPreRequisite)
      {
         throw new ProgramException(
                                    "Course is a prerequisite for another course."
                                             +
                                             " Can not remove...");
      }

      // Remove course.
      // Requires "instanceof" key word. Permitted for count purposes.
      else
      {
         Course owningCourse = getCourse(courseCode);

         if (owningCourse instanceof CoreCourse)
         {
            courseCollection.remove(owningCourse);
            coreCourseCount--;
         }

         else if (owningCourse instanceof ElectiveCourse)
         {
            courseCollection.remove(owningCourse);
            electiveCourseCount--;
         }

         else
         {
            System.out.println("Error: Course type is undefined.");
         }
      }
   }

   // getCourse(courseCode:String)
   //
   // Returns the Course object owning the passed courseCode
   // from the Course Collection.
   // Returns null if no course present.
   //
   public Course getCourse(String passedCourseCode)
   {
      // Temporary Course object.
      Course returnCourse = null;

      // iterator
      Iterator<Course> iter = courseCollection.iterator();

      boolean courseFound = false;
      while (iter.hasNext() && courseFound == false)
      {
         Course currentCourse = iter.next();
         String currentCourseCode = currentCourse.getCourseCode();

         if (currentCourseCode.equals(passedCourseCode))
         {
            courseFound = true;
            returnCourse = currentCourse;
         }
      }

      return returnCourse;
   }

   // getAllCourses()
   //
   // Returns all Course objects in the program's courseCollection as
   // an array. If the courseCollection is empty, it returns null.
   //
   // I have chosen to do conversion to array here in order to
   // prevent passing the collection. (If I pass the collection
   // the collection can be modified by the calling method...)
   //
   public Course[] getAllCourses()
   {
      if (courseCollection.isEmpty())
      {
         return null;
      }

      else
      {
         Course[] returnArray = courseCollection.toArray(new Course[0]);

         return returnArray;
      }
   }

   // countElectiveCourses()
   //
   // Returns the Elective course count.
   // Note: I have delegated task of maintaining
   // count values to class level variables and delegated the
   // task of adding and removing values to
   // addCourse() and removeCourse() methods.
   //
   public int countElectiveCourses()
   {
      return electiveCourseCount;
   }

   // countCoreCourses()
   //
   // Returns the Core course count.
   // Note: I have delegated the task of maintaining
   // count values to class level variables and delegated the
   // task of adding and removing values to
   // addCourse() and removeCourse() methods.
   //
   public int countCoreCorses()
   {
      return coreCourseCount;
   }

   // toString()
   //
   // Returns a String representation of the Program in the format;
   // programCode:programTitle
   //
   public String toString()
   {
      String string = new String(programCode + ":" + programTitle);
      return string;
   }

   // --- Helper Methods ---

   // checkCourseExists(passedCourse:Course)
   //
   // Returns true if a the passed Course object already
   // exists in the Program's courseCollection...
   //
   private boolean courseExists(Course course)
   {
      boolean result;

      if (courseCollection.contains(course))
      {
         
         result = true;
      }

      else
      {
         
         result = false;         
      }

      return result;
   }

   // isMissingPreRequisite(passedCourse:Course)
   //
   // Query checks if the program's courseCollection
   // is missing any of the passedCourse's prerequisites.
   //
   private boolean isMissingPreRequisite(Course course)
   {
      // extract the String[] of courseCodes
      String[] preRequisiteArray = course.getPreRequisites();

      // pass each code into a query method that checks if it
      // exists in the program's courseCollection.
      // as soon as a course is identified as missing,
      // the result is set to true and the for loop exits.
      // Otherwise the loop continues to check until end of
      // preRequisiteArray
      boolean result = false;
      for (int i = 0; i < preRequisiteArray.length && result == false; i++)
      {
         result = isCourseCodeMissing(preRequisiteArray[i]);
      }

      return result;
   }

   // isCourseMissing(passedCourseCode:String)
   //
   // Queries if the passedCourseCode is missing from
   // the program's courseCollection. If it is missing
   // the Return value is true. Used in conjunction with
   // isMissingPreRequisite(Course passedCourse)
   //
   private boolean isCourseCodeMissing(String passedCourseCode)
   {
      // Iterator
      Iterator<Course> iter = courseCollection.iterator();

      // Loop through the courseCollection and check each
      // Course's courseCode for a match against the
      // passedCourseCode. If it does match, the codeFound flag
      // is set to true and the loop exits, otherwise it keeps
      // searching until end of courseCollection.
      boolean codeFound = false;
      while (iter.hasNext() && codeFound == false)
      {
         String currentCourseCode = iter.next().getCourseCode();

         if (currentCourseCode.equals(passedCourseCode))
         {
            codeFound = true;
         }
      }

      // Return the result of the search.
      if (codeFound)
      {
         return false;
      }

      else
      {
         return true;
      }
   }

   // isCoursePreRequisite(courseCode:string)
   //
   // Query returns true if the passed courseCode is
   // found within any of the Program's courses preRequisiteCourses
   // array.
   //
   private boolean isCoursePreRequisite(String courseCode)
   {
      Set<String> preRequisiteCourseCodes = new HashSet<String>();
      Iterator<Course> iter = courseCollection.iterator();

      // For each course in the courseCollection,
      // add the string array contents of its preRequisiteCourses array
      // to the preRequisiteCourseCodes Set.
      Course currentCourse;
      String[] currentCoursePreReqArray;
      while (iter.hasNext())
      {
         currentCourse = iter.next();
         currentCoursePreReqArray = currentCourse.getPreRequisites();

         for (int i = 0; i < currentCoursePreReqArray.length; i++)
         {
            preRequisiteCourseCodes.add(currentCoursePreReqArray[i]);
         }
      }

      // Search the set of prerequisite course codes and return result.
      boolean isPreReq = preRequisiteCourseCodes.contains(courseCode);
      return isPreReq;
   }
}
