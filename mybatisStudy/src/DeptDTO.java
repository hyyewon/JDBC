

//dept 테이블의 하나의 행(레코드)을 저장하는 용도
public class DeptDTO {
	
	int deptno; //dept테이블의 deptno컬럼 저장
	String dname; //dept테이블의 dname컬럼 저장
	String loc; //dept테이블의 loc컬럼 저장
	
	public DeptDTO() {}
	
	public DeptDTO(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}
	
	public DeptDTO(int deptno) {
		this.deptno = deptno;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return deptno+"\t"+dname+"\t"+loc;
	}
	
	

}
