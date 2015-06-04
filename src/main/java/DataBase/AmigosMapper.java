package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Amigos;



public class AmigosMapper extends AbstractMapper <Amigos, String[]>{

	public AmigosMapper(DataSource ds) {
		super(ds);
		// TODO Apéndice de constructor generado automáticamente
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Apéndice de método generado automáticamente
		return ("Nick1, Nick2");
	}

	@Override
	protected String getTableName() {
		// TODO Apéndice de método generado automáticamente
		return "Amigos";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Apéndice de método generado automáticamente
		return new String[] {
				"Nick1","Nick2"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Apéndice de método generado automáticamente
		return new String[] {"Nick1", "Nick2"};
	}

	@Override
	protected Object[] getKeyValues(Amigos elem) {
		// TODO Apéndice de método generado automáticamente
		Object[] values = {elem.getNickUser1(), elem.getNickAmigo()};
		return values;
	}

	@Override
	protected Amigos buildObject(ResultSet rs) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		Amigos amigo = new Amigos();
		amigo.setUserNick(rs.getString("Nick1"));
		amigo.setAmigoNick(rs.getString("Nick2"));
		return amigo;
	}
	
	public List<Object> getListaAmigos(String nick) {
		// TODO Auto-generated method stub
		
		List<Object> lista = new ArrayList<Object>();
		
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		
		String cadena = "Nick2";
		
		String sql = "SELECT " + cadena + " FROM "
				+ tableName + " WHERE "+ columnNames[0] + "= ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setObject(1, nick);
			
			
			
			
			try(ResultSet rs = pst.executeQuery()) {
				
				
				while(rs.next()){
					
					lista.add(rs.getString(cadena));
					
					
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return lista;	
	}
	@Override
	protected String parseInsert() {
		// TODO Apéndice de método generado automáticamente
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+")"+ " values " + " ( ? , ? ) ";
		return str;
	
	}

	@Override
	protected String parseUpdate() {
		// TODO Apéndice de método generado automáticamente
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ?  where "+colNam[0]+"= ? and "+colNam[1]+"= ?";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, Amigos elem, boolean insert) {
		// TODO Apéndice de método generado automáticamente
		try {
			pst.setString(1, elem.getNickUser1());//? correspondiente al nick (PK)
			pst.setString(2,elem.getNickAmigo());// ? corerspondiente al nick del amigo (PK)
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick y nickAmigo)
				pst.setString(3, elem.getNickUser1());
				pst.setString(4, elem.getNickAmigo());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
