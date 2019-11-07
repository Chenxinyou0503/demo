package com.xinyou.dome.dao;

import com.xinyou.dome.entity.SequenceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/5/6 16:04
 * @Description:
 */
@Mapper
public interface SequenceDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "maxValue", column = "max_value"),
            @Result(property = "step", column = "step"),
    })
    @Select("select * from sequence where id = #{id}")
    List<SequenceEntity> query(long id);

    @Update("update sequence set max_value = max_value-1 where id = #{id}")
    public void update(long id);
}
