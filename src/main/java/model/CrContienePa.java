package model;

public class CrContienePa {

	private Integer id_crucigrama;
	private Integer id_palabra; 
	
	private String Orientacion;
	
	private Integer X;
	private Integer Y;
	
	
	private Integer Puntuacion;

	public CrContienePa(int id1, int id2, String orientacion2, int x2, int y2,
			int puntuacion2) {
		// TODO Auto-generated constructor stub
		
		this.id_crucigrama=id1;
		this.id_palabra=id2;
		this.Orientacion= orientacion2;
		this.X= x2;
		this.Y= y2;
		this.Puntuacion=puntuacion2;
		
		
	}

	public Integer getId_crucigrama() {
		return id_crucigrama;
	}

	public void setId_crucigrama(Integer id_crucigrama) {
		this.id_crucigrama = id_crucigrama;
	}

	public Integer getId_palabra() {
		return id_palabra;
	}

	public void setId_palabra(Integer id_palabra) {
		this.id_palabra = id_palabra;
	}

	public Integer getX() {
		return X;
	}

	public void setX(Integer x) {
		X = x;
	}

	public Integer getY() {
		return Y;
	}

	public void setY(Integer y) {
		Y = y;
	}

	public Integer getPuntuacion() {
		return Puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		Puntuacion = puntuacion;
	}

	public String getOrientacion() {
		return Orientacion;
	}

	public void setOrientacion(String orientacion) {
		Orientacion = orientacion;
	}
	
	
}
