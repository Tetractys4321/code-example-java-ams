/**   ControllerHelper.java
 *    @author Cameron Kellett  Student ID 3468911
 * 
 * 
 *    Description:
 *    Static class of static generic methods
 *    
 *    String[] STRING_TOKENIZER(String string, String delim)
 *    String OUTPUT_STRING_ARRAY_AS_STRING(String[] stringArray)
 * 
 * 
 * */

package ams.controller;

import java.awt.Component;
import javax.swing.JOptionPane;

public class ControllerHelper
{

   // STRING_TOKENIZER(string:String, delim:String)
   //
   // Generic tokenizer for splitting strings using
   // passed delimiter.
   //
   public static String[] STRING_TOKENIZER(String string, String delim)
   {
      String[] result = string.split(delim);

      return result;
   }

   // OUTPUT_STRING_ARRAY_AS_STRIN(stringArray:String[])
   //
   // Returns the passed string array
   // as a single string using a StringBuilder.
   // If there are no strings the array will
   // return {" "}.
   //
   public static String OUTPUT_STRING_ARRAY_AS_STRING(String[] stringArray)
   {
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < stringArray.length; i++)
      {
         stringBuilder.append(stringArray[i] + "   ");
      }

      String returnString = stringBuilder.toString();
      return returnString;
   }

   // OUTPUT_STRING_ARRAY_AS_STRIN(stringArray:String[])
   //
   // Returns the passed string array
   // as a single string using a StringBuilder.
   // If there are no strings the array will
   // return {" "}.
   //
   public static String OUTPUT_STRING_ARRAY_AS_STRING(String[] stringArray,
                                                      boolean newLineRequired)
   {
      StringBuilder stringBuilder = new StringBuilder();

      if (newLineRequired)
      {
         for (int i = 0; i < stringArray.length; i++)
         {
            stringBuilder.append(stringArray[i] + "\n");
         }
      }

      else
      {
         for (int i = 0; i < stringArray.length; i++)
         {
            stringBuilder.append(stringArray[i] + " ");
         }
      }

      String returnString = stringBuilder.toString();
      return returnString;
   }

   //
   //
   // This will have multiple versions for different array types.
   // will include a generic on that outputs object[].toString
   //
   public static void PRINT_ARRAY_CONTENT(String testLocation,
                                          String[] stringArray)
   {
      System.out.println("Testing contents of " + stringArray.toString() +
                         "\n" + testLocation);
      for (int i = 0; i < stringArray.length; i++)
      {
         System.out.println(stringArray[i]);
      }
   }

   public static void PRINT_ARRAY_CONTENT(String testLocation, Object[] array)
   {

      System.out.println("Testing contents of " + array.toString() + "\n" +
                         testLocation);

      for (int i = 0; i < array.length; i++)
      {
         System.out.println(array[i].toString());
      }
   }

   // Static method specified to Component
   //
   //
   //
   public static boolean USER_INTENDED_INPUT_CHECK(Component view,
                                                   String message)
   {

      int option =
               JOptionPane.showConfirmDialog(view, message,
                                             "Input Confirmation", 2);

      if (option == JOptionPane.YES_OPTION)
         return true;

      else
         return false;
   }

}
