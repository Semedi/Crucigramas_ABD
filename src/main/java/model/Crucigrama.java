package model;

import java.sql.Date;




public class Crucigrama {
	
	private Integer id;
	private String titulo;
	private Date fecha;
	
	public Crucigrama(Integer id, String titulo, Date fecha){
		this.id=id;
		this.titulo=titulo;
		this.fecha= fecha;
		
		
	}
	
	
	public void setNombre(String nombre){
		
		
	}
	
	public void setFecha(Date fecha){
		
		
	}
	
	public String getTitulo(){
		
		return this.titulo;
	}
	
	public Date getFecha(){
		
		return this.fecha;
	}
	
	public Integer getId(){
		return this.id;
	}

}
