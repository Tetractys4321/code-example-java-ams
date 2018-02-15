/**
 * AMSDriver.java
 * @author Cameron Kellett  Student ID 3468911
 * 
 * Description:
 * 
 * Main Driver for AMS system. 
 * GUI launches from here.
 * 
 * 
 */

package ams.view.main;
import ams.model.facade.AMSFacade;
import ams.model.facade.AMSModel;
import ams.view.*;

public class AMSDriver {

   public static void main(String[] args) 
   {
      AMSModel amsModel = new AMSFacade();
      AMSMainView mainView = new AMSMainView(amsModel);      
      mainView.setVisible(true);
   }
}
