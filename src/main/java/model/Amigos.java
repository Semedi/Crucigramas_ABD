package model;
public class Amigos {
	
	
	
	private String _user1;
	private String _user2;
	
	
	public Amigos(String user1, String amigo){
		this._user1 = user1;
		this._user2 = amigo;
		
	}

	public Amigos() {
		// TODO Auto-generated constructor stub
	}

	public String getNickUser1(){
		return _user1;
	}
	public void setUserNick(String nick){
		_user1= nick;
		
	}
	public String getNickAmigo(){
		return _user2;
		
	}
	public void setAmigoNick(String nickAmigo){
		_user2 = nickAmigo;
	}
}
