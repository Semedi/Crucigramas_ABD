package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.CrContienePa;

public class CrContienePaMapper extends AbstractMapper<CrContienePa, Integer[]> {

	public CrContienePaMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "(id_crucigrama, id_palabra)";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "crucigrama_contiene_palabra";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {"id_crucigrama","id_palabra","Orientacion", "X", "Y", "Puntuacion"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Auto-generated method stub
		String [] keys = {"id_crucigrama","id_palabra"};
		return keys;
	}

	@Override
	protected Object[] getKeyValues(CrContienePa elem) {
		// TODO Auto-generated method stub
		Object[] keyValues = {elem.getId_crucigrama(), elem.getId_palabra()};
		return keyValues;
	}

	@Override
	protected CrContienePa buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int id1 = rs.getInt("id_crucigrama");
		int id2 = rs.getInt("id_palabra");
		String orientacion = rs.getString("Orientacion");
		int X = rs.getInt("X");
		int Y = rs.getInt("Y");
		int puntuacion = rs.getInt("Puntuacion");
		
		
		return new CrContienePa(id1, id2, orientacion, X, Y, puntuacion);
	}

	@Override
	protected String parseInsert() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+","+colNam[3]+","+colNam[4]+","+colNam[5]+")"+ " values " + " ( ?, ?, ?, ?, ?, ?) ";
		return str;
	}

	@Override
	protected String parseUpdate() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ? ,"+ colNam[3]+"= ? ,"+ colNam[4]+"= ? ,"+ colNam[5]+"= ? "
				+ "WHERE "+colNam[0]+"= ? AND "+colNam[1]+"= ? ";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, CrContienePa elem,
			boolean insert) {
		// TODO Auto-generated method stub
		
		
				try {
			
				
			pst.setInt(1, elem.getId_crucigrama());//
			pst.setInt(2, elem.getId_palabra());// 
			pst.setString(3, elem.getOrientacion());// 
			pst.setInt(4, elem.getX());
			pst.setInt(5, elem.getY());
			pst.setInt(6, elem.getPuntuacion());
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK () y la PK ()
				pst.setInt(7, elem.getId_crucigrama());
				pst.setInt(8, elem.getId_palabra());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	//SELECT * FROM `crucigrama_contiene_palabra` WHERE id_crucigrama =1
	public List<CrContienePa> getPalabras(Integer id) {
		// TODO Auto-generated method stub
		
		ArrayList<CrContienePa> lista = new ArrayList<CrContienePa>();
		String tableName = getTableName();
		String keyColumnName = "id_crucigrama";
		
		
		String sql = "SELECT * FROM "+ tableName + " WHERE "+ keyColumnName +  " = ?";
		
		try ( Connection con = ds.getConnection();
				
				PreparedStatement pst = con.prepareStatement(sql)){
				pst.setInt(1, id);
				
				
				try(ResultSet rs = pst.executeQuery()) {
					while (rs.next())
						lista.add(buildObject(rs));
					
				}
				
			
		} catch (SQLException e){
			e.printStackTrace();
			return null;
			
		}
		
		
		
		
		return lista;
	}

}
