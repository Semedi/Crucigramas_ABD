package model;

import es.ucm.abd.crossword.WordModel;


public class Palabra implements WordModel {
	
	private int x;
	private int y;
	private String word;
	private boolean horizontal;
	
	

	public Palabra(int x, int y, String word, boolean horizontal) {
		this.x = x;
		this.y = y;
		this.word = word;
		this.horizontal = horizontal;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
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
		Palabra other = (Palabra) obj;
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

	
	

}