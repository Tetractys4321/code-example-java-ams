/**   DualInputDialog.java
 *    @author Cameron Kellett  Student ID 3468911
 * 
 *    Description:
 *        
 *    Generic abstract dialog box consisting of the three extendible panels,
 *    and generates two input fields, an information
 *    area and an Enter and Cancel button. 
 *       
 *    Note: A controller must be declared and instantiated in subclasses. 
 *    This ONLY specifies layout and components.
 *    
 *    Development Note: This would be improved by extracting
 *    the following JPanels and giving them there own abstract class
 *    allowing for better generics.
 *    
 *    TOP_PANEL_SPACE
 *    MAIN_PANEL_SPACE
 *    BOTTOM_PANEL_SPACE
 * 
 */

package ams.view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ams.view.AMSMainView;

@SuppressWarnings("serial")
public abstract class DualInputDialog extends JDialog
{
   // parent view
   private AMSMainView amsMainView;

   // text area
   private JTextArea informationArea;

   // labels
   private JLabel inputFieldLabel_1;
   private JLabel inputFieldLabel_2;

   // text fields
   private JTextField inputField_1;
   private JTextField inputField_2;

   // buttons
   private String enterLabel;
   private String cancelLabel;
   private JButton enter;
   private JButton cancel;

   // panels
   private final JPanel TOP_PANEL_SPACE = new JPanel(new BorderLayout());
   private final JPanel MAIN_PANEL_SPACE = new JPanel(new BorderLayout());
   private final JPanel BOTTOM_PANEL_SPACE = new JPanel(new BorderLayout());
   private final JPanel INFORMATION_PANEL = new JPanel(new BorderLayout());
   private final JPanel INPUT_PANEL = new JPanel(new GridLayout(2, 2, 5, 5));
   private final JPanel BUTTON_PANEL =
            new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));

   // defaults
   protected final int DEFAULT_WIDTH = 430;

   // DualInputDialog(amsMainView:AMSMainView,
   // inputMessage_1:String, inputMessage_2:String)
   //
   // Creates a DualInputDialog with a specified AMDSMainView/Owner, title,
   // information text, input labels and example messages.
   //
   public DualInputDialog(AMSMainView amsMainView,
                          String title,
                          String informationMessage,
                          String inputMessage_1,
                          String inputMessage_2,
                          String exampleMessage_1,
                          String exampleMessage_2)
   {
      super(amsMainView, title, true);
      this.amsMainView = amsMainView;

      this.setLayout(new BorderLayout());

      this.inputFieldLabel_1 = new JLabel(inputMessage_1);
      this.inputFieldLabel_2 = new JLabel(inputMessage_2);

      this.inputField_1 = new JTextField(exampleMessage_1, 50);
      this.inputField_2 = new JTextField(exampleMessage_2, 50);

      this.informationArea = new JTextArea(informationMessage);
      this.informationArea.setEditable(false);
      this.informationArea.setBackground(this.getBackground());

      // INFORMATION_PANEL
      this.INFORMATION_PANEL.add(informationArea);
      this.INFORMATION_PANEL.setBorder(new EmptyBorder(10, 10, 10, 10));
      int infoHeight =
               (int) this.INFORMATION_PANEL.getPreferredSize().getHeight();
      Dimension infoPanelDimension = new Dimension(DEFAULT_WIDTH, infoHeight);
      this.INFORMATION_PANEL.setPreferredSize(infoPanelDimension);

      // INPUT_PANEL

      this.INPUT_PANEL.add(inputFieldLabel_1);
      this.INPUT_PANEL.add(inputField_1);
      this.INPUT_PANEL.add(inputFieldLabel_2);
      this.INPUT_PANEL.add(inputField_2);
      this.INPUT_PANEL.setBorder(new EmptyBorder(10, 10, 10, 10));
      int inputHeight = (int) this.INPUT_PANEL.getPreferredSize().getHeight();
      Dimension inputPanelDimension = new Dimension(DEFAULT_WIDTH, inputHeight);
      this.INPUT_PANEL.setPreferredSize(inputPanelDimension);

      // BUTTON_PANEL
      this.enterLabel = "  Enter  ";
      this.cancelLabel = "  Cancel  ";
      this.enter = new JButton(enterLabel);
      this.cancel = new JButton(cancelLabel);
      this.BUTTON_PANEL.add(this.enter);
      this.BUTTON_PANEL.add(Box.createHorizontalStrut(5));
      this.BUTTON_PANEL.add(this.cancel);
      this.BUTTON_PANEL.setBorder(new EmptyBorder(10, 10, 10, 10));

      this.add(TOP_PANEL_SPACE, BorderLayout.NORTH);
      this.add(MAIN_PANEL_SPACE, BorderLayout.CENTER);
      this.add(BOTTOM_PANEL_SPACE, BorderLayout.SOUTH);

      this.TOP_PANEL_SPACE.add(INFORMATION_PANEL, BorderLayout.CENTER);
      this.MAIN_PANEL_SPACE.add(INPUT_PANEL, BorderLayout.CENTER);
      this.BOTTOM_PANEL_SPACE.add(BUTTON_PANEL, BorderLayout.CENTER);
      this.pack();

   }

   public AMSMainView getAMSMainView()
   {
      return amsMainView;
   }

   public String getInputField_1()
   {
      return inputField_1.getText();
   }

   public String getInputField_2()
   {
      return inputField_2.getText();
   }

   public JButton getEnter()
   {
      return enter;
   }

   public void setEnterAndCancel(String newEnter, String newCancel)
   {
      this.BUTTON_PANEL.remove(this.enter);
      this.BUTTON_PANEL.remove(this.cancel);
      enterLabel = newEnter;
      cancelLabel = newCancel;
      this.enter = new JButton(enterLabel);
      this.cancel = new JButton(cancelLabel);
      this.cancel.setPreferredSize(this.enter.getPreferredSize());
      this.BUTTON_PANEL.add(this.enter);
      this.BUTTON_PANEL.add(Box.createHorizontalStrut(1));
      this.BUTTON_PANEL.add(this.cancel);
      this.enter.validate();
      this.enter.repaint();
   }

   public JButton getCancel()
   {
      return cancel;
   }

   public void setCancel(String newLabel)
   {
      cancelLabel = newLabel;
   }

   public JPanel getMainPanel()
   {
      return MAIN_PANEL_SPACE;
   }

   public JPanel getInputPanel()
   {
      return INPUT_PANEL;
   }

   public JPanel getInformationPanel()
   {
      return INFORMATION_PANEL;
   }

   public JPanel getTOP_PANEL_SPACE()
   {
      return TOP_PANEL_SPACE;
   }

   public JPanel getMAIN_PANEL_SPACE()
   {
      return MAIN_PANEL_SPACE;
   }

   public JPanel getBOTTOM_PANEL_SPACE()
   {
      return BOTTOM_PANEL_SPACE;
   }

}
