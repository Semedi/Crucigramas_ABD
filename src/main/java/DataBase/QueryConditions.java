package DataBase;
public class QueryConditions {
	
	private String columName;
	private QueryOperator op;
	private Object value;
	
	public QueryConditions(String columName, QueryOperator op, Object value) {
		super();
		this.columName= columName;
		this.op=op;
		this.value = value;
	}
	
	public String getClave() {
		return columName;
	}
	public void setClave(String clave) {
		this.columName = columName;
	}
	public QueryOperator getOperator() {
		return op;
	}
	public void setOperator(QueryOperator operator) {
		this.op = operator;
	}
	public Object getValue() {
		return value;
	}
	public void setVaue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return columName+op.toString()+'?';
	}
	
	
}
