package Observer;

import javax.swing.ImageIcon;

public interface LoginObserver{
	
	public void onLogin(String Username, ImageIcon avatar, int edad, String fecha, String pass);
	public void onFailed();

}
