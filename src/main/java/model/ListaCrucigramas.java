package model;


public class ListaCrucigramas {
	
	private String Nick;
	private Integer id_crucigrama;
	private boolean activo;
	
	public ListaCrucigramas(String Nick, Integer id_crucigrama, boolean activo){
		this.Nick= Nick;
		this.id_crucigrama=id_crucigrama;
		this.activo=activo;	
	}
	
	
	public Integer getId(){
		return this.id_crucigrama;
	}
	
	public String getNick(){
		return this.Nick;
		
	}
	
	public int getActivo(){
		if (activo) return 1;
		else return 0;
		
	}
	
	public String[] values(){
		String id =""+id_crucigrama;
		String activo=""+this.activo;
		
		
		return new String[] {
				this.Nick,id, activo
		};	}



}
