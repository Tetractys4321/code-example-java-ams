package ams.model.facade;

import ams.model.*;
import ams.model.exception.*;

/**
 * AMSFacade.java
 * 
 * @author Mikhail Perepletchikov Implementd by Cameron Kellett
 * 
 */

public class AMSFacade implements AMSModel
{
   // --- AMSFacade Field Data ---

   private University university;

   // --- AMSFacade Constructors ---

   // AMSFacade()
   //
   // default constructor.
   //
   public AMSFacade()
   {
      university = new University();
   }

   // --- AMSFacade Methods ---

   // addStudent(newStudent:Student)
   //
   // Adds the passed Student to the University's
   // student field.
   //
   @Override
   public void addStudent(Student newStudent)
   {
      university.addStudent(newStudent);
   }

   // getStudent()
   //
   // Returns the Univeristy's stored Student object.
   //
   @Override
   public Student getStudent()
   {
      return university.getStudent();
   }

   // addProgram(program:Program)
   //
   // Sets the University's Program object
   //
   @Override
   public void addProgram(Program program)
   {
      university.addProgram(program);
   }

   // getProgram()
   //
   // Returns the University's Program object
   //
   @Override
   public Program getProgram()
   {
      return university.getProgram();
   }

   // addCourse(course:Course)
   //
   // Adds the passed Course to the University's
   // Program.
   //
   @Override
   public void addCourse(Course course) throws ProgramException
   {
      university.addCourse(course);

   }

   // removeCourse(courseId:String)
   //
   // Removes the passed course from the University's program.
   // Throws a ProgramException if the passed course is a
   // prerequisite for another course in the program.
   //
   @Override
   public void removeCourse(String courseId) throws ProgramException
   {
      university.removeCourse(courseId);
   }

   // getCourse(courseCode:String)
   //
   // Returns the Course containing the passed courseCode.
   //
   @Override
   public Course getCourse(String courseCode)
   {
      return university.getCourse(courseCode);
   }

   // getAllCourses()
   //
   // Returns an array of Course objects
   // retrieved form the University's
   // program.
   //
   @Override
   public Course[] getAllCourses()
   {
      return university.getAllCourses();
   }

   // enrollIntoCourse(courseID:String)
   //
   // Attempts to enroll student into the course owning
   // the passed courseID/courseCode
   //
   @Override
   public void enrollIntoCourse(String courseID) throws EnrollmentException
   {
      university.enrollIntoCourse(courseID);
   }

   // withdrawFromCourse(courseID:String)
   //
   // Attempts to withdraw an enrolled course.
   //
   @Override
   public void withdrawFromCourse(String courseID) throws EnrollmentException
   {
      university.withdrawFromCourse(courseID);

   }

   // addResult(result:Result)
   //
   // adds a new result. returns false if the course associated with this
   // result does not exist in the collection of all currently enrolled
   // courses.
   @Override
   public boolean addResult(Result result)
   {
      return university.addResult(result);

   }

   // getResults()
   //
   // Returns an array of Results for student.
   // Test harness expects null if empty array
   // conversion done here...
   //
   @Override
   public Result[] getResults()
   {
      Result[] returnArray = university.getResults();

      if (returnArray.length == 0)
      {
         return null;
      }

      else
      {
         return returnArray;
      }
   }

   // getCurrentEnrollment()
   //
   // Returns an array of Courses a student
   // is enrolled in.
   //
   // Test Harness Expects Null so conversion done here.
   //
   @Override
   public Course[] getCurrentEnrollment()
   {
      Course[] returnArray = university.getCurrentEnrollment();

      if (returnArray.length == 0)
      {
         return null;
      }

      else
      {
         return returnArray;
      }
   }

   // calculateCurrentLoad()
   //
   // Returns current study load for the
   // university student.
   //
   @Override
   public int calculateCurrentLoad()
   {
      return university.calculateCurrentLoad();
   }

   // calculateCareerPoints()
   //
   // Returns the student's career points...
   //
   @Override
   public int calculateCareerPoints()
   {
      return university.calculateCareerPoints();
   }

   // countCoreCourses()
   //
   // Returns the number of Core type courses in
   // the University's program...
   //
   @Override
   public int countCoreCourses()
   {
      return university.countCoreCourses();
   }

   // countElectiveCourses()
   //
   // Returns the number of Elective type courses in
   // the University's program...
   //
   @Override
   public int countElectiveCourses()
   {
      return university.countElectiveCourses();
   }
}