package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Crucigrama;



public class CrucigramasMapper extends AbstractMapper<Crucigrama, Integer> {

	public CrucigramasMapper(DataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected String getKeyColumnName() {
		// TODO Auto-generated method stub
		return "id_crucigrama";
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "Crucigrama";
	}

	@Override
	protected String[] getTableColumns() {
		// TODO Auto-generated method stub
		return new String[] {
				this.getKeyColumnName(),"Titulo", "Fecha"
		};
	}

	@Override
	protected Crucigrama buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		Integer idTabla = rs.getInt(getKeyColumnName()); //identificador de la tabla usuario (NICK)
		String Titulo = rs.getString("Titulo");
		Date fecha = rs.getDate("Fecha");
			
		return new Crucigrama(idTabla, Titulo, fecha);
	}
	
	
	//select `id_crucigrama` from crucigrama where `Titulo` like '%str%'
	public List<Integer> getLista(String str){
		List<Integer> lista = new ArrayList<Integer>();

		str="%"+str+"%";
		
		String tableName = getTableName();
		String[] columnNames = getTableColumns();
		String keyColumnName = getKeyColumnName();
		
		String sql = "SELECT " + keyColumnName + " FROM "
				+ tableName + " WHERE "+ columnNames[1] + " like ?";
		try (Connection con = ds.getConnection();
			 PreparedStatement pst = con.prepareStatement(sql)) {
			
			pst.setObject(1, str);
			
			
			
			try(ResultSet rs = pst.executeQuery()) {
				while(rs.next()){
					lista.add((Integer)rs.getInt(keyColumnName));	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return lista;		
	}



	@Override
	protected String parseInsert() {
		String colNam[] = getTableColumns();//","+colNam[2]+","+colNam[3]
		String str = " ("+colNam[0]+","+colNam[1]+","+colNam[2]+")"+ " values " + " ( ? , ? , ? ) ";
		return str;
	}

	@Override
	protected void setValues(PreparedStatement pst, Crucigrama elem,
			boolean insert) {
		try {
					
					pst.setInt(1, elem.getId());//? correspondiente al id_crucigrama (PK)
					pst.setString(2, elem.getTitulo());// ? corerspondiente a la contraseña 
					pst.setDate(3, elem.getFecha()); // ? correspondiente a la fecha FALLA NO SE XQ?
					
					if(!insert){// si no es un insert (es un update) necesitamos rellenar el where con la PK (id_crucigrama)
						pst.setInt(4, elem.getId());
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
		String str = colNam[0]+"= ? ,"+colNam[1]+"= ? ,"+colNam[2]+"= ?  WHERE "+colNam[0]+"= ? ";
		
		return str;
	}


	@Override
	protected String[] getKeyColumnNames() {
		// TODO Apéndice de método generado automáticamente
		String[] keys = {"id_crucigrama"};
		return keys;
	}


	@Override
	protected Object[] getKeyValues(Crucigrama elem) {
		// TODO Apéndice de método generado automáticamente
		Object[] keyValues= {elem.getId()};
		return keyValues;
	}



}
