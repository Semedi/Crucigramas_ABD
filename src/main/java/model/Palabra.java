package model;

public class Palabra {
	
	private Integer id;
	private String caracteres;
	private String texto;
	private byte[] imagen;
	
	
	


	public Palabra(int idTabla, String caracteres, String texto,
			byte[] fotoBytes) {
		// TODO Auto-generated constructor stub
		this.id = idTabla;
		this.caracteres=caracteres;
		this.texto=texto;
		this.imagen= fotoBytes;
		
	}



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
