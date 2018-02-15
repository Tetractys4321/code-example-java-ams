package ams.model.exception;
/**
 * EnrollmentException.java
 * 
 * @author Cameron Kellett StudentID 3468911
 * 
 * Description:  This exception is thrown under the following conditions;
 * 
 * 1. Duplicate enrollment
 * 
 * 2. A student attempts to enroll into a previously studied unit that has been passed (PA)
 * 
 * 3. Under Graduate is overloaded.
 * 
 * 4. Post Graduate overloaded by more than 6 credit points, and if 
 *    Post Graduate student attempts to overload having failed a previously enrolled course.
 * 
 * 5. Under Graduate must have passed ALL prerequisite courses prior to enrolling.
 * 
 * 6. Post Graduate must have passed ANY ONE of the prerequisites.
 * */

@SuppressWarnings("serial")
public class EnrollmentException extends AMSException 
{
   public EnrollmentException() 
   {
      
   }

// Use this constructor when throwing EnrollmentException
   public EnrollmentException(String message) 
   {
      super(message);      
   }
}
