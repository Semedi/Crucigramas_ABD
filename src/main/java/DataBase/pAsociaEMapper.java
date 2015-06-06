package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.pAsociaE;

public class pAsociaEMapper extends AbstractMapper<pAsociaE, Integer[]> {

	public pAsociaEMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return ("id_palabra, id_etiqueta");
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "palabra_asocia_etiqueta";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				"id_palabra","id_etiqueta"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Auto-generated method stub
		return new String[] {"id_palabra", "id_etiqueta"};
	}

	@Override
	protected Object[] getKeyValues(pAsociaE elem) {
		// TODO Auto-generated method stub
		Object[] values = {elem.getId_palabra(), elem.getId_etiqueta()};
		return values;
	}

	@Override
	protected pAsociaE buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int id1 = rs.getInt("id_palabra");
		int id2 = rs.getInt("id_etiqueta");
		
		return new pAsociaE(id1, id2);
	}

	@Override
	protected String parseInsert() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+")"+ " values " + " ( ? , ? ) ";
		return str;
	}

	@Override
	protected String parseUpdate() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ?  where "+colNam[0]+"= ? and "+colNam[1]+"= ?";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, pAsociaE elem,
			boolean insert) {
		// TODO Auto-generated method stub
		
		try {
			pst.setInt(1, elem.getId_palabra());//? correspondiente al nick (PK)
			pst.setInt(2,elem.getId_etiqueta());// ? corerspondiente al nick del amigo (PK)
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick y nickAmigo)
				pst.setInt(3, elem.getId_palabra());
				pst.setInt(4, elem.getId_etiqueta());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
