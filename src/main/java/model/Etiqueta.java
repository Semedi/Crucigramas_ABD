package model;

public class Etiqueta {
	
	private Integer id;
	private String tematica;
	
	
	public Etiqueta(int id, String tematica) {
		// TODO Auto-generated constructor stub
		
		this.id=id;
		this.tematica=tematica;
		
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}

}
