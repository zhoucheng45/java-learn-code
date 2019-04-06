package cn.com.benny.learn.dao;

import cn.com.benny.learn.models.TestOrder;
import cn.com.benny.learn.models.TestOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TestOrderMapper {
    long countByExample(TestOrderExample example);

    int deleteByExample(TestOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestOrder record);

    int insertSelective(TestOrder record);

    List<TestOrder> selectByExample(TestOrderExample example);

    TestOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestOrder record, @Param("example") TestOrderExample example);

    int updateByExample(@Param("record") TestOrder record, @Param("example") TestOrderExample example);

    int updateByPrimaryKeySelective(TestOrder record);

    int updateByPrimaryKey(TestOrder record);
}