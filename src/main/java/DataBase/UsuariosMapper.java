package DataBase;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;









import javax.sql.DataSource;

import model.Usuario;


public class UsuariosMapper extends AbstractMapper<Usuario, String> {
	
	
	
	public UsuariosMapper(DataSource ds){
		super(ds);			
	}


	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				this.getKeyColumnName(),"Password", "Nacimiento", "Avatar"
		};
	}


	@Override
	protected Usuario buildObject(ResultSet rs) throws SQLException {
		String idTabla = rs.getString(getKeyColumnName()); //identificador de la tabla usuario (NICK)
		String password = rs.getString("Password");
		Date fecha = rs.getDate("Nacimiento");
		Blob avatar = rs.getBlob("Avatar");
		
		byte[] fotoBytes = null;
		if (avatar != null) {
			fotoBytes = avatar.getBytes(1, (int)avatar.length());
		}
		
		return new Usuario(idTabla, password, fecha, fotoBytes);
	}

	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "Nick";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "Usuario";
	}


	@Override
	protected String parseInsert() {
		// TODO Apéndice de método generado automáticamente
		
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+","+colNam[3]+")"+ " values " + " ( ? , ? , ? , ? ) ";
		return str;
	}


	@Override
	protected void setValues(PreparedStatement pst, Usuario elem, boolean insert) {
		// TODO Apéndice de método generado automáticamente
		try {
			pst.setString(1, elem.getNick());//? correspondiente al nick (PK)
			pst.setString(2,elem.getPassword());// ? corerspondiente a la contraseña 
			pst.setDate(3, elem.getFechaNacimiento()); // ? correspondiente a la fecha FALLA NO SE XQ?
			pst.setBytes(4, elem.getFoto());// ? correspondiente a la foto
			
			if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (nick)
				pst.setString(5, elem.getNick());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	protected String parseUpdate() {
		String colNam[] = getTableColumns();
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ? ,"+colNam[3]+"= ?  where "+colNam[0]+"= ? ";
		
		return str;
	}


	@Override
	protected String[] getKeyColumnNames() {
		// TODO Apéndice de método generado automáticamente
		String[] keys = {"Nick"};
		return keys;
	}





	@Override
	protected Object[] getKeyValues(Usuario elem) {
		// TODO Apéndice de método generado automáticamente
		Object[] keyValues = {elem.getNick()};
		return keyValues;
	}

	

}
