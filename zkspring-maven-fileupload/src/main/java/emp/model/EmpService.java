package emp.model;

import java.util.List;

public interface EmpService {
	public List<EmpVO> getAll(); 
	public void insert(EmpVO empVO);
	public void delete(Integer empno);
	public EmpVO findByPrimaryKey(Integer empno);
	public void update(EmpVO empVO);
	public void testInsert(EmpVO empVO);
	public void testUpdate(EmpVO empVO);
	public void testDelete(Integer empno);
	public void comprehensivetest(EmpVO empVO, Integer empno);
	public void comprehensivetest2(EmpVO empVO, Integer empno);
	public void comprehensivetest3(EmpVO empVO, Integer empno);
	public void comprehensivetest4(EmpVO empVO, Integer empno);



}
