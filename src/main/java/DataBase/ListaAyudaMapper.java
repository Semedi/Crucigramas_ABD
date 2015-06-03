package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.ListaAyuda;


public class ListaAyudaMapper extends AbstractMapper<ListaAyuda, Object>{

	public ListaAyudaMapper(DataSource ds) {
		super(ds);
		// TODO Apéndice de constructor generado automáticamente
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	@Override
	protected String getTableName() {
		// TODO Apéndice de método generado automáticamente
		return "lista_ayuda";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Apéndice de método generado automáticamente
		return new String[] {
				"Nick1","Nick2","id_crucigrama"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Apéndice de método generado automáticamente
		String[] keys = {"Nick1","Nick2","id_crucigrama"};
		return keys;
	}

	@Override
	protected Object[] getKeyValues(ListaAyuda elem) {
		// TODO Apéndice de método generado automáticamente
		Object[] values = {elem.getUsuario(), elem.getAyudante(), elem.getIdCrucigrama()};
		return values;
	}
	// Devuelve la lista de ayuda del usuario que pasamos por parametro
	public List<Object> getListaAyuda(String nick) {
		// TODO Auto-generated method stub
		
		List<Object> lista = new ArrayList<Object>();
		
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		
		String cadena = "Nick2";
		
		String sql = "SELECT *  FROM "
				+ tableName + " WHERE "+ columnNames[0] + "= ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setObject(1, nick);
			
			System.out.println(pst);
			
			try(ResultSet rs = pst.executeQuery()) {
				
				
				while(rs.next()){
					
					lista.add(rs.getString(cadena)+" "+rs.getString("id_crucigrama"));
					
					System.out.println("la lista tiene : "+lista.size());
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return lista;	
	}

	@Override
	protected ListaAyuda buildObject(ResultSet rs) throws SQLException {
		// TODO Apéndice de método generado automáticamente
		ListaAyuda l = new ListaAyuda();
		l.setUsuario(rs.getString("Nick1"));
		l.setAyudante(rs.getString("Nick2"));
		l.setCrucigrama(rs.getInt("id_crucigrama"));
		return l;
	}

	@Override
	protected String parseInsert() {
		// TODO Apéndice de método generado automáticamente
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+")"+ " values " + " ( ?  , ? , ? ) ";
		return str;
		
	}

	@Override
	protected String parseUpdate() {
		// TODO Apéndice de método generado automáticamente
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ?  WHERE "+colNam[0]+"= ? AND "+colNam[1]+"= ? AND "+colNam[2]+"= ? ";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, ListaAyuda elem,
			boolean insert) {
		// TODO Apéndice de método generado automáticamente
	try {
			
			pst.setString(1, elem.getUsuario());//? correspondiente al nick (PK)
			pst.setString(2, elem.getAyudante());// ? correspondiente al nick del ayudante (PK)
			pst.setInt(3,elem.getIdCrucigrama());// ? correspondiente al id_crucigrama (PK)
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick) y la PK (id_crucigrama)
				pst.setString(4, elem.getUsuario());
				pst.setString(5, elem.getAyudante());
				pst.setInt(6, elem.getIdCrucigrama());
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
