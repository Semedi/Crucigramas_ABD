package DataBase;



public enum QueryOperator {
	EQ ("="),
	LE ("<="),
	GE (">="),
	LT ("<"),
	BT (">"),
	NEQ ("<>"),
	LIKE ("LIKE");
	
	String op;
	
	QueryOperator(String op){
		this.op= op;
		
	}

	public String toString(){
		return op;
		
	}

}

