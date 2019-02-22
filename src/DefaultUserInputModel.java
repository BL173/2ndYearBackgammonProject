/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DefaultUserInputModel implements UserInputModel{
    private PropertyChangeSupport propertyChangeSupport;
    private String userInput;
    private String infoPanelOutput;

    public DefaultUserInputModel(){
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    @Override
    public String getUserInput(){
        return userInput;
    }
    //@Override
    public void setUserInput(String newUserInput){
        String oldUserInput = userInput;
        userInput = newUserInput;
        propertyChangeSupport.firePropertyChange("userInput", oldUserInput, userInput);
        userInput = "";
        propertyChangeSupport.firePropertyChange("userInput", oldUserInput, userInput);
    }

    public String getInfoPanelOutput(){
        return infoPanelOutput;
    }
    //@Override
    public void setInfoPanelOutput(String newInfoPanelOutput){
        String oldInfoPanelOutput = infoPanelOutput;
        infoPanelOutput = newInfoPanelOutput;
        propertyChangeSupport.firePropertyChange("infoPanelOutput", oldInfoPanelOutput, infoPanelOutput);
        infoPanelOutput = "";
        propertyChangeSupport.firePropertyChange("infoPanelOutput", oldInfoPanelOutput, infoPanelOutput);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
   /* @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }*/
}
