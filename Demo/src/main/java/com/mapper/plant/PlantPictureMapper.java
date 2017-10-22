package com.mapper.plant;

import java.util.List;

import com.mapper.plant.persistent.PlantPicture;

public interface PlantPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PlantPicture record);

    int insertSelective(PlantPicture record);

    List<PlantPicture> selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlantPicture record);

    int updateByPrimaryKey(PlantPicture record);
}