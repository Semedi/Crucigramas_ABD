package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Etiqueta;

public class EtiquetaMapper extends AbstractMapper<Etiqueta, Integer> {

	public EtiquetaMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "id_etiqueta";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "etiqueta";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				"id_etiqueta","Tematica"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Auto-generated method stub
		return new String[] {"id_etiqueta"};
	}

	@Override
	protected Object[] getKeyValues(Etiqueta elem) {
		// TODO Auto-generated method stub
		Object[] values = {elem.getId(), elem.getTematica()};
		return values;
	}

	@Override
	protected Etiqueta buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		
		
		return new Etiqueta(rs.getInt("id_etiqueta"), rs.getString("Tematica"));
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
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ?  where "+colNam[0]+"= ?";
		
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, Etiqueta elem,
			boolean insert) {
		// TODO Auto-generated method stub
		try {
			pst.setInt(1, elem.getId());//? correspondiente al nick (PK)
			pst.setString(2,elem.getTematica());// ? corerspondiente al nick del amigo (PK)
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick y nickAmigo)
				pst.setInt(3, elem.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
	}


