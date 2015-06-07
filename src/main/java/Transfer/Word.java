package Transfer;

import es.ucm.abd.crossword.WordModel;


public class Word implements WordModel {
	
	private int x;
	private int y;
	private String word;
	private boolean horizontal;
	private String texto;
	private byte[] foto;
	
	private int idP;
	private int idC;
	
	private int _indice;

	public Word(int x, int y, String word, boolean horizontal, String texto, byte[] foto, int ind, int idP, int idC) {
		this.x = x;
		this.y = y;
		this.word = word;
		this.horizontal = horizontal;
		this.texto=texto;
		this.foto = foto;
		_indice = ind;
		this.idP=idP;
		this.idC=idC;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int geti(){
		
		return _indice;
	}

	public boolean isHorizontal() {
		return this.horizontal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (horizontal ? 1231 : 1237);
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (horizontal != other.horizontal)
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return this.word;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	
	

}