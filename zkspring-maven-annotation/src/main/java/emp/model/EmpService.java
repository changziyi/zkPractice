package emp.model;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.select.annotation.WireVariable;
//@Service
public class EmpService {
	//@WireVariable("EmpDAO_interface")
	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpHibernateDAO();
	}

	public EmpVO addEmp(String ename, String job, java.sql.Date hiredate,
			Double sal, Double comm, Integer deptno) {

		EmpVO empVO = new EmpVO();

		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.insert(empVO);

		return empVO;
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void addEmp(EmpVO empVO) {
		dao.insert(empVO);
	}

	public EmpVO updateEmp(Integer empno, String ename, String job,
			java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpno(empno);
		empVO.setEname(ename);
		empVO.setJob(job);
		empVO.setHiredate(hiredate);
		empVO.setSal(sal);
		empVO.setComm(comm);
		empVO.setDeptno(deptno);
		dao.update(empVO);

		return dao.findByPrimaryKey(empno);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateEmp(EmpVO empVO) {
		dao.update(empVO);
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(Integer empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
