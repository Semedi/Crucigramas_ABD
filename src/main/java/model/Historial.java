package model;

import java.sql.Timestamp;

public class Historial {
	
	
	
	private String nick1;
	private String nick2;
	private Integer id_palabra;
	private Integer id_crucigrama;
	private String respuesta;
	private Timestamp fecha;
	
	
public Historial(String nick1, int id_palabra, int id_crucigrama, String respuesta, Timestamp fecha){
	
	this.nick1 = nick1;
	this.id_palabra=id_palabra;
	this.id_crucigrama=id_crucigrama;
	this.respuesta=respuesta;
	this.fecha=fecha;
		
	}
	
	
	
	
	
	public String getNick1() {
		return nick1;
	}
	public void setNick1(String nick1) {
		this.nick1 = nick1;
	}
	public String getNick2() {
		return nick2;
	}
	public void setNick2(String nick2) {
		this.nick2 = nick2;
	}
	public Integer getId_palabra() {
		return id_palabra;
	}
	public void setId_palabra(Integer id_palabra) {
		this.id_palabra = id_palabra;
	}
	public Integer getId_crucigrama() {
		return id_crucigrama;
	}
	public void setId_crucigrama(Integer id_crucigrama) {
		this.id_crucigrama = id_crucigrama;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

}
