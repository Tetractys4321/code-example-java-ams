package ams.model;

/**
 * Student.java
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: Extends Enrollable interface and provides an interface 
 * for student objects. 
 * 
 */

public interface Student extends Enrollable 
{
   // getFullName()
   //
   // Returns full name of student.
   //
   public abstract String getFullName();
   
   
   // getStudentID()
   //
   // Returns Student Identification Number
   //
   public abstract int getStudentID();
   
   
   // calculateCurrentLoad()
   //
   // calculates and returns the current study load of the student
   // 
   public abstract int calculateCurrentLoad();
   
   
   // calculateCurrentCareerPoints()
   //
   // Calculates and returns the total number of credits earned 
   // by the student for passing courses.
   //
   public abstract int calculateCurrentCareerPoints();
   
   
   // addResult(courseCode:String, grade:String)
   //
   // Adds a record of course result to student entity.
   // Returns false if course associated with the result 
   // does not exist currently enrolled courses.
   //
   public abstract boolean addResult(Result result);
   
   
   // getResult(courseCode:String)
   //
   // Returns the grade result for the passed courseCode.
   // 
   public abstract String getResult(String courseCode);
   
   
   // getAllResults()
   //
   // Returns an array of results for the student
   // !!! NOTE: HIGHER LEVEL AMSModel INTERFACE EXPECTS Result[] !!! 
   //
   public abstract Result[] getAllResults();
   
   
   // getCurrentEnrollment()
   //
   // Returns an array of Courses the student is enrolled in.
   //
   public abstract Course[] getCurrentEnrollment();
   
   // toString()
   //
   // Returns String representation of student in the format 
   // studentID:fullName:maximumLoad
   //
   public abstract String toString();
}
