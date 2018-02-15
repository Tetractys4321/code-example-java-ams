/**
 * ProgramMapController.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description:
 * 
 * Contains all listeners for ProgramMap
 * 
 * */

package ams.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ams.view.ProgramMap;

public class ProgramMapController implements ActionListener 
{

   // *** MAPPED REFERENCES ***
   private ProgramMap programMap;
   
   
   // *** CONSTRUCTORS ***
   
   // ProgramMap(programMap:ProgramMap)
   //
   // Description Required
   //
   public ProgramMapController(ProgramMap programMap)
   {
      this.programMap = programMap;
   }
   
   
   @Override
   public void actionPerformed(ActionEvent e) 
   {
      // TODO Auto-generated method stub

   }

}
