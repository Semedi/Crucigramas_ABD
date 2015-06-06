package model;

public class Word {
	
	private Integer id;
	private String caracteres;
	private String texto;
	private byte[] imagen;
	
	
	


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id=id;
	}





	public String getCaracteres() {
		return caracteres;
	}




	public void setCaracteres(String caracteres) {
		this.caracteres = caracteres;
	}



	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public byte[] getImagen() {
		return imagen;
	}



	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
