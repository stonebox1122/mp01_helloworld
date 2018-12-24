package com.stone.mp.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stone.mp.beans.Emp;
import com.stone.mp.mapper.EmpMapper;

public class TestMP {

	private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
	private EmpMapper empMapper = ioc.getBean("empMapper", EmpMapper.class);

	@Test
	public void testDataSource() throws SQLException {
		DataSource ds = ioc.getBean("dataSource", DataSource.class);
		System.out.println(ds);
		Connection connection = ds.getConnection();
		System.out.println(connection);
	}

	/**
	 * 通用插入操作
	 */
	@Test
	public void testCommonInsert() {
		Emp emp = new Emp();
		emp.setLastName("bb");
		emp.setEmail("mp@stone.com");
		// emp.setGender(1);
		// emp.setAge(22);
		// 插入到数据库，使用insert()方法属性为空的字段不会出现在SQL语句中
		int result = empMapper.insert(emp);
		// 插入到数据库，使用insertAllColumn()方法属性为空的字段也会出现在SQL中
		result = empMapper.insertAllColumn(emp);

		System.out.println(result);

		// 获取当前数据在数据库中的主键值
		Integer id = emp.getId();
		System.out.println(id);
	}

	/**
	 * 通用更新操作
	 */
	@Test
	public void testCommonUpdate() {
		Emp emp = new Emp();
		emp.setId(7);
		emp.setLastName("MyBatisPlus");
		// emp.setEmail("mybatisplus@stone.com");
		// emp.setGender(0);
		// emp.setAge(33);
		// Integer result = empMapper.updateById(emp);
		Integer result = empMapper.updateAllColumnById(emp);
		System.out.println(result);
	}

	/**
	 * 通用查询操作
	 */
	@Test
	public void testSelectById() {
		// 1、通过id查询
		Emp emp = empMapper.selectById(7);
		System.out.println(emp);
	}

	@Test
	public void testSelectOne() {
		// 通过多个列进行查询id+lastName
		Emp emp = new Emp();
		emp.setId(7);
		emp.setLastName("MyBatisPlus");
		Emp result = empMapper.selectOne(emp);
		System.out.println(result);
	}

	@Test
	public void testSelectBatchIds() {
		// 通过多个id进行查询
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(4);
		idList.add(5);
		idList.add(6);
		List<Emp> emps = empMapper.selectBatchIds(idList);
		System.out.println(emps);
	}

	@Test
	public void testSelectByMap() {
		// 通过Map封装条件进行查询,key为表的字段
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("last_name", "mp");
		columnMap.put("gender", 1);
		List<Emp> emps = empMapper.selectByMap(columnMap);
		System.out.println(emps);
	}

	@Test
	public void testSelectPage() {
		// 分页查询
		List<Emp> emps = empMapper.selectPage(new Page<Emp>(4, 3), null);
		System.out.println(emps);
	}

	@Test
	public void testDeleteById() {
		// 根据id删除
		Integer result = empMapper.deleteById(7);
		System.out.println(result);
	}

	@Test
	public void testDeleteByMap() {
		// 根据条件删除
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("last_name", "mp");
		columnMap.put("email", "mp@stone.com");
		Integer result = empMapper.deleteByMap(columnMap);
		System.out.println(result);
	}

	@Test
	public void testDeleteBatchIds() {
		// 批量删除
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(10);
		idList.add(11);
		idList.add(12);
		Integer result = empMapper.deleteBatchIds(idList);
		System.out.println(result);
	}

	// 条件构造器，查询操作
	@Test
	public void testEntityWrapperSelectPage() {
		// 分页查询tbl_emp表中年龄在18-50之间，性别为男且姓名为bb的所有用户
		List<Emp> emps = empMapper.selectPage(new Page<Emp>(1, 2),
				new EntityWrapper<Emp>().between("age", 18, 50).eq("gender", 1).eq("last_name", "bb"));
		System.out.println(emps);
	}

	// 条件构造器，查询操作
	@Test
	public void testEntityWrapperSelectList1() {
		// 分页查询tbl_emp表中性别为女且名字中带有“mybatis”或者邮箱中带有“a“
		List<Emp> emps = empMapper.selectList(
				new EntityWrapper<Emp>().eq("gender", 0).like("last_name", "mybatis").or().like("email", "a"));
		System.out.println(emps);
	}

	// 条件构造器，查询操作
	@Test
	public void testEntityWrapperSelectList2() {
		// 分页查询tbl_emp表中性别为女且名字中带有“mybatis”或者邮箱中带有“a“
		List<Emp> emps = empMapper.selectList(
				new EntityWrapper<Emp>().eq("gender", 0).like("last_name", "mybatis").orNew().like("email", "a"));
		System.out.println(emps);
	}

	// 条件构造器，修改操作
	@Test
	public void testEntityWrapperUpdate() {
		// 修改tbl_emp表中姓名为bb，年龄为44的记录
		Emp emp = new Emp();
		emp.setLastName("ddd");
		emp.setEmail("ddd@stone.com");
		emp.setGender(0);
		empMapper.update(emp, new EntityWrapper<Emp>().eq("last_name", "bb").eq("age", 44));
	}

	// 条件构造器，删除操作
	@Test
	public void testEntityWrapperDelete() {
		// 删除tbl_emp表中姓名为bb，年龄为33的记录
		empMapper.delete(new EntityWrapper<Emp>().eq("last_name", "bb").eq("age", 33));
	}
	
	// 条件构造器，查询操作
	@Test
	public void testEntityWrapperSelectList3() {
		// 分页查询tbl_emp表中性别为女的记录，根据年龄进行升序排序
		List<Emp> emps = empMapper.selectList(new EntityWrapper<Emp>().eq("gender", 0).orderBy("age"));
		System.out.println(emps);
	}	

	// 条件构造器，查询操作
	@Test
	public void testEntityWrapperSelectList4() {
		// 分页查询tbl_emp表中性别为女的记录，根据年龄进行降序排序
		List<Emp> emps = empMapper.selectList(new EntityWrapper<Emp>().eq("gender", 0).orderDesc(Arrays.asList(new String[] {"age"})));
		System.out.println(emps);
	}	
	
	// 条件构造器，查询操作
	@Test
	public void testConditionSelectPage() {
		// 分页查询tbl_emp表中年龄在18-50之间，性别为男且姓名为bb的所有用户
		@SuppressWarnings("unchecked")
		List<Emp> emps = empMapper.selectPage(new Page<Emp>(1, 2),
				Condition.create().between("age", 18, 50).eq("gender", 1).eq("last_name","bb"));
		System.out.println(emps);
	}	
	
}
