package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;



public abstract class AbstractMapper<T, K> {
	
	protected DataSource ds;
	
	/*Super que pasaremos a la clase derivada
	 *  para inicializar datasource*/
	public AbstractMapper(DataSource ds){
		this.ds=ds;		
	}
	
	//Array de condiciones formado por nombre de columna operador de condicion y valor
	private QueryConditions[] getQueryConditions(T elem){
		
		String[] keyColumnNames= getKeyColumnNames();//obtenemos los nombres de las PKs
		Object[] keyValues= getKeyValues(elem);//obtenemos el valor de las keys
		QueryConditions[] conditions= new QueryConditions[keyColumnNames.length];
		for (int i = 0; i < conditions.length; i++) {
			conditions[i]= new QueryConditions(keyColumnNames[i], QueryOperator.EQ, keyValues[i]);
		}
		return conditions;
	}
	
	//String para generar las condiciones de la clausula where
	private String whereConditions(QueryConditions[] conditions) {
			
			String whereClause=" where ";
			String[] cadenaCond=new String[conditions.length];
			for (int i = 0; i < conditions.length; i++) {
				cadenaCond[i]=conditions[i].toString();
			}
			whereClause+=StringUtils.join(cadenaCond, " and ");
			return whereClause;
		}
	// Busca y devuelve un elemento dado un id
	public T findById(K id) {
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		String keyColumnName = getKeyColumnName();
		
		String sql = "SELECT " + StringUtils.join(columnNames, ", ") + " FROM "
				+ tableName + " WHERE "+ keyColumnName + " = ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setObject(1, id);
			
			try(ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return buildObject(rs);
				} else {
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void insert(T elem) {
		
		String tableName = this.getTableName();
				
		
		String sql = "INSERT INTO " +tableName + parseInsert();
		
		
		try (Connection con = ds.getConnection();
				 PreparedStatement pst = con.prepareStatement(sql)) {
			this.setValues(pst, elem, true);
			
			pst.execute();
				
		} catch ( SQLException e){
			e.printStackTrace();
			System.out.println("EXcepcion");
					
		}
		
		
	}
	public void update(T elem){
		String tableName = getTableName();
		
		String sql = "UPDATE " + tableName + " SET "+ this.parseUpdate();
		
		try (Connection con = ds.getConnection();
				 PreparedStatement pst = con.prepareStatement(sql)) {
			this.setValues(pst, elem, false);
			pst.executeUpdate();
		} catch ( SQLException e){
			e.printStackTrace();
					
		}
	}
	public void delete(T elem){
		String tableName = getTableName();
		QueryConditions[] conditions= getQueryConditions(elem);
		String sql = "DELETE FROM " + tableName + whereConditions(conditions);
		try (Connection con = ds.getConnection();
				 PreparedStatement pst = con.prepareStatement(sql)) {
			
			for (int i = 0; i < conditions.length; i++) {//leemos las condiciones para hacer el delete(las PKs)
				pst.setObject(i+1,conditions[i].getValue());
			}
			//this.setValues(pst, elem, false);
			pst.executeUpdate();
		} catch ( SQLException e){
			e.printStackTrace();
					
		}
		
	}
	
	/*Metodos publicos que implementarán las clases derivadas*/
	//public abstract void insert(T elem); //insertar <T>
	//public abstract void update(T elem); //modificar <T>
	//public abstract void delete(T elem); //borrar <T>
	
	
	/*Metodos abstractos que implementarán las clases derivadas*/
	protected abstract String getKeyColumnName();
	protected abstract String getTableName();
	protected abstract String[] getTableColumns();// devuelve los nombres de las columnas de la tabla
	protected abstract String[] getKeyColumnNames();//devuelve una array con todas las PK
	protected abstract Object[] getKeyValues(T elem);// array que nos duelve los valores de las PK
	protected abstract T buildObject(ResultSet rs) throws SQLException;
	protected abstract String parseInsert();
	protected abstract String parseUpdate();
	//protected abstract String parseDelete();
	protected abstract void setValues(PreparedStatement pst, T elem, boolean insert);
	

}
