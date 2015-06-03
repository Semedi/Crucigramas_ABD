package model;


public class ListaAyuda {
	private String user1;
	private String user2;
	private int id_crucigrama;
	
	public ListaAyuda(String user1, String user2, int id_crucigrama){
		this.id_crucigrama = id_crucigrama;
		this.user1 =user1;
		this.user2=user2;
		
	}
	public ListaAyuda() {
		// TODO Apéndice de constructor generado automáticamente
	}
	public String getAyudante(){
		return this.user2;
	}
	public String getUsuario(){
		return this.user1;
	}
	public int getIdCrucigrama(){
		return this.id_crucigrama;
	}
	public void setUsuario(String nick){
		this.user1 = nick;
	}
	public void setAyudante(String ayudante){
		this.user2 = ayudante;
	}
	public void setCrucigrama(int id){
		this.id_crucigrama =id;
	}
}
