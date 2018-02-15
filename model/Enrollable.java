package ams.model;

/**
 * Enrollable.java 
 * @author Cameron Kellett Student ID 3468911
 *
 * Description: Interface for entities that can enroll into Courses. 
 *
 */

import ams.model.exception.EnrollmentException;

public interface Enrollable
{
   // enrollIntoCourse(courseCode:String)
   //
   // Enrolls entity into the unit owning the passed coursCode.
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
   public abstract void enrollIntoCourse(Course course)
            throws EnrollmentException;

   // withdrawFromCourse(course: Course)
   //
   // Removes the Course owning the passed courseCode from the entities enrolled
   // courses.
   // EnrollmentException thrown if the courseCode does not exist in enrolled
   // courses.
   //
   public abstract void withdrawFromCourse(Course course)
            throws EnrollmentException;
}
