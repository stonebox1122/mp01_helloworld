package com.stone.mp.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stone.mp.beans.Emp;

/**
 * 基于MyBatis：在Mapper接口中编写CRUD方法，提供Mapper接口所对应的SQL映射文件以及方法对应的SQL语句
 * 
 * 基于MP：在Mapper接口继承BaseMapper接口即可,
 * BaseMatcher<T>：泛型指定的就是当前Mapper接口所操作的实体类
 * @author lei.shi445
 *
 */
public interface EmpMapper extends BaseMapper<Emp> {
}
