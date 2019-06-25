import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 测试dao类
 * @author Administrator
 *1、引入springTest 模块
 *2、@ContextConfiguration 加入spring 配置文件
 *3、autowired 自动加入组件
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession session;

	@Test
	public void testCRUD(){
		/*旧模式创建springIOC容器*/
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		DepartmentMapper dept = ioc.getBean(DepartmentMapper.class);
//		System.out.println(dept);
		
		
		
		/**
		 * spring 注解方式
		 */
		//插入部门
//		departmentMapper.insertSelective(new Department(null, "开发部"));
//		departmentMapper.insertSelective(new Department(null, "研发部"));
//		System.out.println("插入部门成功!");

		//插入员工
//		int num = employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@atguigu.com", 1));
//		System.out.println("插入员工成功：" + num);
		
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 1000; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
			//mapper.insertSelective(new Employee(null, uid, "M", uid + "@atguigu.com", 1));
		}
		System.out.println("批量完成");
	}

}
