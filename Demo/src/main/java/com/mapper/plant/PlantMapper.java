package com.mapper.plant;

import java.util.List;

import com.domain.PlantInfo;
import com.domain.PlantInfoConditon;
import com.domain.PlantQueryCondition;
import com.mapper.plant.persistent.Plant;

public interface PlantMapper {
    int deleteByPrimaryKey(Integer plantid);

    int insert(Plant record);

    int insertSelective(Plant record);

    Plant selectByPrimaryKey(Integer plantid);

    int updateByPrimaryKeySelective(Plant record);

    int updateByPrimaryKey(Plant record);

	List<Plant> selectByCondition(PlantQueryCondition condition);
	
	List<PlantInfo> queryPlanInfoByCondition(PlantInfoConditon condition);
}