package DataBase;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Palabra;
import model.Usuario;



public class PalabrasMapper extends AbstractMapper<Palabra, Integer>{

	public PalabrasMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "id_palabra";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "palabra";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				"id_palabra","Caracteres","Texto","Imagen"};
	}

	@Override
	protected String[] getKeyColumnNames() {
		// TODO Auto-generated method stub
		String[] keys = {"id_palabra"};
		return keys;
	}

	@Override
	protected Object[] getKeyValues(Palabra elem) {
		// TODO Auto-generated method stub
		Object[] keyValues= {elem.getId()};
		return keyValues;
	}

	@Override
	protected Palabra buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int idTabla = rs.getInt(getKeyColumnName()); //identificador de la tabla usuario (NICK)
		String Caracteres = rs.getString("Caracteres");
		String Texto = rs.getString("Texto");
		Blob imagen = rs.getBlob("Imagen");
		
		byte[] fotoBytes = null;
		if (imagen != null) {
			fotoBytes = imagen.getBytes(1, (int)imagen.length());
		}
		
		return new Palabra(idTabla, Caracteres, Texto, fotoBytes);
	}

	@Override
	protected String parseInsert() {
		// TODO Auto-generated method stub
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+","+colNam[3]+")"+ " values " + " ( ? , ? , ? , ? ) ";
		return str;
	}

	@Override
	protected String parseUpdate() {
		// TODO Auto-generated method stub
		
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ? , "+colNam[3]+"  WHERE "+this.getKeyColumnName()+"= ? ";
		
		return str;
	
	}

	@Override
	protected void setValues(PreparedStatement pst, Palabra elem, boolean insert) {
		// TODO Auto-generated method stub
		try {
			pst.setInt(1, elem.getId());//? correspondiente al nick (PK)
			pst.setString(2,elem.getCaracteres());// ? corerspondiente a la contraseña 
			pst.setString(3, elem.getTexto()); // ? correspondiente a la fecha FALLA NO SE XQ?
			pst.setBytes(4, elem.getImagen());// ? correspondiente a la foto
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK 
				pst.setInt(5, elem.getId());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
