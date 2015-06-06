package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.Word;



public class PalabrasMapper extends AbstractMapper<Word, Integer>{

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
	protected Object[] getKeyValues(Word elem) {
		// TODO Auto-generated method stub
		Object[] keyValues= {elem.getId()};
		return keyValues;
	}

	@Override
	protected Word buildObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
	protected void setValues(PreparedStatement pst, Word elem, boolean insert) {
		// TODO Auto-generated method stub
		
	}

}
