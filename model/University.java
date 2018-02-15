package ams.model;

/**
 * University.java 
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: Captures data about student and university programs
 * !!! Currently provisions for one of each, as per ASM specifications. *
 */

import ams.model.exception.EnrollmentException;
import ams.model.exception.ProgramException;
import ams.model.facade.AMSModel;

public class University implements AMSModel
{

   // --- University Field Data ---

   private Student student;
   private Program program;

   // --- University Constructors ---

   // University()
   //
   // Default University Constructor.
   // Creates an empty University object.
   //
   public University()
   {

   }

   // --- AMSModel Interface Methods ---
   // Note: University class has a direct association with AMSFacade and
   // is required to implement all AMSFacade methods.

   // addStudent(newStudent:Student)
   //
   // Sets student to passed student.
   //
   @Override
   public void addStudent(Student newStudent)
   {
      student = newStudent;

   }

   // getStudent()
   //
   // Returns the Student
   //
   @Override
   public Student getStudent()
   {
      return student;
   }

   // addProgram(program:Program)
   //
   // Sets student to passed student.
   //
   @Override
   public void addProgram(Program program)
   {
      this.program = program;

   }

   // getProgram()
   //
   // Returns the program.
   //
   @Override
   public Program getProgram()
   {
      return program;
   }

   // addCourse(course:Course)
   //
   // Adds the passed Course to the University's
   // Program.
   //
   @Override
   public void addCourse(Course course) throws ProgramException
   {
      program.addCourse(course);

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
      program.removeCourse(courseId);
   }

   // getCourse(courseCode:String)
   //
   // Returns the Course containing the passed courseCode.
   //
   @Override
   public Course getCourse(String courseCode)
   {
      return program.getCourse(courseCode);
   }

   // getAllCourses()
   //
   // Returns an array of Course objects retrieved from the University program.
   //
   @Override
   public Course[] getAllCourses()
   {
      return program.getAllCourses();
   }

   // enrollIntoCourse(course:Course)
   //
   // Retrieves the Course owning the passed courseID
   // and attempts to enroll the student into it.
   //
   @Override
   public void enrollIntoCourse(String courseID) throws EnrollmentException
   {
      Course course = program.getCourse(courseID);
      student.enrollIntoCourse(course);
   }

   // withdrawFromCourse(courseID:String)
   //
   // Attempts to withdraw student from an enrolled course.
   //
   @Override
   public void withdrawFromCourse(String courseID) throws EnrollmentException
   {
      Course course = program.getCourse(courseID);
      student.withdrawFromCourse(course);

   }

   // addResult(result:Result)
   //
   // adds a new result. returns false if the course associated with this
   // result does not exist in the collection of all currently enrolled
   // courses.
   //
   @Override
   public boolean addResult(Result result)
   {
      return student.addResult(result);
   }

   // getResults()
   //
   // Returns an array of Results for student.
   //
   @Override
   public Result[] getResults()
   {
      return student.getAllResults();
   }

   // getCurrentEnrollment()
   //
   // Returns an array of Courses a student
   // is enrolled in.
   //
   @Override
   public Course[] getCurrentEnrollment()
   {
      return student.getCurrentEnrollment();
   }

   // calculateCurrentLoad()
   //
   // Returns the student current study load.
   //
   @Override
   public int calculateCurrentLoad()
   {
      return student.calculateCurrentLoad();
   }

   // calculateCareerPoints()
   //
   // Returns the student's career points...
   //
   @Override
   public int calculateCareerPoints()
   {
      return student.calculateCurrentCareerPoints();
   }

   // countCoreCourses()
   //
   // Returns the number of Core type courses in
   // the University's program...
   //
   @Override
   public int countCoreCourses()
   {
      return program.countCoreCorses();
   }

   // countElectiveCourses()
   //
   // Returns the number of Elective type courses in
   // the University's program...
   //
   @Override
   public int countElectiveCourses()
   {
      return program.countElectiveCourses();
   }
}
