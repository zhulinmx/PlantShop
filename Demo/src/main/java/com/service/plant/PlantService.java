package com.service.plant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.PlantInfo;
import com.domain.PlantInfoConditon;
import com.domain.PlantQueryCondition;
import com.mapper.plant.persistent.Plant;
import com.mapper.plant.persistent.PlantCategory;
import com.mapper.plant.persistent.PlantPicture;

public interface PlantService {

	List<PlantCategory> queryPlantCategory();

	void insertPlant(Plant plant);

	List<Plant> queryPlantByCondition(PlantQueryCondition condition);

	void updatePlant(Plant plant);

	List<PlantPicture> queryPictureById(String plantId);

	Plant queryPlantById(int parseInt);

	void exportUserInfo(HttpServletRequest request, HttpServletResponse response, List<Plant> plantList) throws Exception;

	void insertPicture(PlantPicture picture);

	void updatePicture(PlantPicture picture);

	List<PlantInfo> queryPlanInfoByCondition(PlantInfoConditon plantCondition);

}
