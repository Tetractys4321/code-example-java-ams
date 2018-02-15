package ams.view;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.border.*;
import ams.controller.MouseController;

@SuppressWarnings("serial")
public abstract class ProgramMapCell extends JPanel
{   
   private MouseController mouseController = new MouseController(this);
   
   // Constructor needs to have a boolean parameter for determining 
   // if the course is a core or elective. 
   // If core, set border to 5 else set to 2
   private final int DEFAULT_BORDER_VALUE = 1;
   private final  Color DEFAULT_BORDER_COLOR = Color.BLACK;
   private final  Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GRAY;
   
   // Constructor
   // Will Need Listeners for clickable options menu etc
   public ProgramMapCell(int borderValue, String[] courseInfo)
   {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      this.setBackground(DEFAULT_BACKGROUND_COLOR);
      this.addMouseListener(mouseController);
      if (borderValue == 0)
      {
         LineBorder border = new LineBorder(DEFAULT_BORDER_COLOR, DEFAULT_BORDER_VALUE);
         this.setBorder(border);
      }
      
      else
      {
         LineBorder border = new LineBorder(DEFAULT_BORDER_COLOR, borderValue);
         this.setBorder(border);
      } 
      ProgramMapInfoArea text = new ProgramMapInfoArea(courseInfo);
      this.add(text);
   }   
}
