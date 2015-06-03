package model;

import java.sql.Date;



public class Usuario {
	
	private String nick;
	private String password;
	private Date fechaNacimiento;
	private byte[] foto;
	
	public Usuario(String nick, String password, Date fechaNacimiento,
			byte[] foto) {
		this.nick = nick;
		this.password= password;
		this.fechaNacimiento = fechaNacimiento;
		this.foto = foto;
		
	}
	
	public String getPassword(){
		return password;
		
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public void setNick(String nick){
		this.nick=nick;
		
	}
	
	public void setPassword(String password){
		this.password=password;
		
		
	}



	@Override
	public String toString() {
		return "Contacto [Nick=" + nick + ", password=" + password +   "]";
	}

	public String getNick() {
		// TODO Auto-generated method stub
		return nick;
	}
}