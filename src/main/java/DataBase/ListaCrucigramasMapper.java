package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ListaCrucigramas;

import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;




public class ListaCrucigramasMapper extends AbstractMapper<ListaCrucigramas, String[]> {

	public ListaCrucigramasMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}	

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "lista_crucigramas";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {"Nick","id_crucigrama","Activo"};
	}

	@Override
	protected ListaCrucigramas buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "(Nick, id_crucigramas)";
	}

	
	//SELECT * FROM `lista_crucigramas` WHERE Nick="user" and Activo="1"
	public List<Object> getLista(String nick) {
		// TODO Auto-generated method stub
		
		List<Object> lista = new ArrayList<Object>();
		
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		
		String cadena = "id_crucigrama";
		
		String sql = "SELECT " + cadena + " FROM "
				+ tableName + " WHERE "+ columnNames[0] + "= ? and "+ columnNames[2]+"= ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setObject(1, nick);
			pst.setObject(2, 1);
			
		
			
			
			try(ResultSet rs = pst.executeQuery()) {
				
				
				while(rs.next()){
										
					lista.add(rs.getInt(cadena));
					
					
					
						
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return lista;	
	}

	@Override
	protected String parseInsert() {
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+")"+ " values " + " ( ?  , ? , ? ) ";
		return str;
	}


	@Override
	protected void setValues(PreparedStatement pst, ListaCrucigramas elem,
			boolean insert) {
		// TODO Apéndice de método generado automáticamente
try {
			
			pst.setString(1, elem.getNick());//? correspondiente al nick (PK)
			pst.setInt(2, elem.getId());// ? correspondiente al id_crucigrama (PK)
			pst.setInt(3,elem.getActivo());// ? correspondiente al estado (activo o no)
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick) y la PK (id_crucigrama)
				pst.setString(4, elem.getNick());
				pst.setInt(5, elem.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	protected String parseUpdate() {
		// TODO Apéndice de método generado automáticamente
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ?  WHERE "+colNam[0]+"= ? AND "+colNam[1]+"= ? ";
		
		return str;
	}


	@Override
	protected String[] getKeyColumnNames() {
		// TODO Apéndice de método generado automáticamente
		String [] keys = {"Nick","id_crucigrama"};
		return keys;
	}


	@Override
	protected Object[] getKeyValues(ListaCrucigramas elem) {
		// TODO Apéndice de método generado automáticamente
		Object[] keyValues = {elem.getNick(), elem.getId()};
		return keyValues;
	}

}
