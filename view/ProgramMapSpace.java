package ams.view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ProgramMapSpace extends JPanel
{
   private ProgramMap programMap;
   private final JLabel NO_PROGRAM = new JLabel("NO PROGRAM PRESENT");
   private boolean programMapPresent = false;

   public ProgramMapSpace()
   {
      setLayout(new BorderLayout());

      if (programMapPresent == false)
      {
         this.add(NO_PROGRAM, BorderLayout.CENTER);
         this.NO_PROGRAM.setHorizontalAlignment(SwingConstants.CENTER);
      }
   }

   public void addProgramMap(AMSMainView amsMainView, String title)
   {
      if (programMapPresent == false)
      {
         this.remove(NO_PROGRAM);
      }

      this.programMap = new ProgramMap(amsMainView, title);
      this.add(programMap, BorderLayout.CENTER);
      this.programMapPresent = true;
      this.repaint();
   }

   public ProgramMap getProgramMap()
   {
      return this.programMap;
   }
}
