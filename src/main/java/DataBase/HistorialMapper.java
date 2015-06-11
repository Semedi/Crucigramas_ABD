package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Historial;

//PK = nick1-idP-idC-fecha
public class HistorialMapper extends AbstractMapper<Historial, Object[]> {

	public HistorialMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return ("Nick1, id_palabra, id_crucigrama, Fecha");
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "historial";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				"Nick1","Nick2","id_palabra","id_crucigrama","Respuesta", "Fecha"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Auto-generated method stub
		String[] keys = {"Nick1","id_palabra","id_crucigrama","Fecha"};
		return keys;
	}

	@Override
	protected Object[] getKeyValues(Historial elem) {
		// TODO Auto-generated method stub
		Object[] values = {elem.getNick1(), elem.getNick2(), elem.getId_palabra(), elem.getId_crucigrama(), elem.getRespuesta(), elem.getFecha()};
		return values;
	}

	@Override
	protected Historial buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Historial nuevo = new Historial(rs.getString("Nick1"), rs.getInt("id_palabra"), rs.getInt("id_crucigrama"), rs.getString("Respuesta"), rs.getTimestamp("Fecha"));
		if (rs.getString("nick2")!= null)
			nuevo.setNick2(rs.getString("nick2"));
	
		return nuevo;
	}

	@Override
	protected String parseInsert() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+","+colNam[3]+","+colNam[4]+","+colNam[5]+")"+ " values " + " ( ?  , ? , ? , ? , ?, ?) ";
		return str;

	}

	@Override
	protected String parseUpdate() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ? ,"+colNam[3]+"= ? ,"+colNam[4]+"= ? ,"+colNam[5]+"= ?  WHERE "+colNam[0]+"= ? AND "+colNam[1]+"= ? AND "+colNam[2]+"= ? AND "+colNam[3]+"= ? ";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, Historial elem,
			boolean insert) {
		// TODO Auto-generated method stub
try {
			
			pst.setString(1, elem.getNick1());//? correspondiente al nick (PK)
			pst.setString(2, elem.getNick2());// ? correspondiente al nick del ayudante (PK)
			pst.setInt(3,elem.getId_palabra());// ? correspondiente al id_crucigrama (PK)
			pst.setInt(4,elem.getId_crucigrama());
			pst.setString(5,elem.getRespuesta());
			pst.setTimestamp(6, elem.getFecha());
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick) y la PK (id_crucigrama)
				pst.setString(7, elem.getNick1());
				pst.setInt(8, elem.getId_palabra());
				pst.setInt(9, elem.getId_crucigrama());
				pst.setTimestamp(10, elem.getFecha());
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	//SELECT * FROM `historial` WHERE Nick1="sergio" and id_crucigrama = 1
	public List<String> getPalabras(String user, int idC){
		
		List<String> lista = new ArrayList<String>();

		
		
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		
		String sql = "SELECT * FROM "
				+ tableName + " WHERE "+ columnNames[0] + " = ? AND "+ columnNames[3]+" = ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setString(1, user);
			pst.setInt(2, idC);
			
			
			
			try(ResultSet rs = pst.executeQuery()) {
				while(rs.next()){
					lista.add(rs.getString("Respuesta"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return lista;		
		
	
	}
	

}
