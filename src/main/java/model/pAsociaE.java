package model;

public class pAsociaE {
	
	private Integer id_palabra;
	private Integer id_etiqueta;
	
	public pAsociaE(int id1, int id2) {
		// TODO Auto-generated constructor stub
		this.id_palabra= id1;
		this.id_etiqueta = id2;
		
	}
	public Integer getId_palabra() {
		return id_palabra;
	}
	public void setId_palabra(Integer id_palabra) {
		this.id_palabra = id_palabra;
	}
	
	public Integer getId_etiqueta() {
		return id_etiqueta;
	}
	public void setId_etiqueta(Integer id_etiqueta) {
		this.id_etiqueta = id_etiqueta;
	}

}
