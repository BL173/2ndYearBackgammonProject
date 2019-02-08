import java.beans.PropertyChangeListener;

public interface UserInputModel {
    public String getUserInput();
    public void setUserInput(String userInput);

    public void addPropertyChangeListener(PropertyChangeListener listener);
    //public void removePropertyChangeListener(PropertyChangeListener listener);
}
