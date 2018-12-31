package emp.model;

import java.util.*;

import org.springframework.stereotype.Component;
@Component
public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(Integer empno);
          public EmpVO findByPrimaryKey(Integer empno);
          public List<EmpVO> getAll();
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
