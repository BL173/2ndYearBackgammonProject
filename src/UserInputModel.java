/*
Team: Jives
Written by: Brian Leahy 17372896,
            Oscar Byrne Carty 17430786,
            Gearoid Lynch 17459176
 */

import java.beans.PropertyChangeListener;

public interface UserInputModel {
    public String getUserInput();
    public void setUserInput(String userInput);


    public void addPropertyChangeListener(PropertyChangeListener listener);
    //public void removePropertyChangeListener(PropertyChangeListener listener);
}
