package com.mapper.plant;

import java.util.List;

import com.mapper.plant.persistent.PlantCategory;

public interface PlantCategoryMapper {
    int deleteByPrimaryKey(Integer categoryid);

    int insert(PlantCategory record);

    int insertSelective(PlantCategory record);

    List<PlantCategory> selectByPrimaryKey(Integer categoryid);

    int updateByPrimaryKeySelective(PlantCategory record);

    int updateByPrimaryKey(PlantCategory record);
}