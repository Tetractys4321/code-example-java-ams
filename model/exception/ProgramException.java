package ams.model.exception;
/**
 * ProgramException.java
 * 
 * @author Cameron Kellett Student ID 3468911
 * 
 * Description: This exception is thrown under the following conditions;
 * 
 * 1. courseCode already exists in the program
 * 
 * */
@SuppressWarnings("serial")
public class ProgramException extends AMSException 
{
   // Default Constructor
   public ProgramException() 
   {
      super("Default AMS ProgramException");
   }

   // Use this constructor when throwing ProgramException
   public ProgramException(String message) 
   {
      super(message);
      
   }
}
