package com.service.plant.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;

import com.domain.PlantInfo;
import com.domain.PlantInfoConditon;
import com.domain.PlantQueryCondition;
import com.mapper.plant.PlantCategoryMapper;
import com.mapper.plant.PlantMapper;
import com.mapper.plant.PlantPictureMapper;
import com.mapper.plant.persistent.Plant;
import com.mapper.plant.persistent.PlantCategory;
import com.mapper.plant.persistent.PlantPicture;
import com.service.plant.PlantService;
import com.util.CSVUtils;
import com.util.DateUtils;

@Repository
public class PlantServiceImpl implements PlantService {
	
	@Resource
	private PlantMapper plantMapper;
	
	@Resource
	private PlantCategoryMapper plantCategoryMapper;
	
	@Resource
	private PlantPictureMapper plantPictureMapper; 

	@Override
	public List<PlantCategory> queryPlantCategory() {
		List<PlantCategory> plantCategorieList = this.plantCategoryMapper.selectByPrimaryKey(null);
		return plantCategorieList;
	}

	@Override
	public void insertPlant(Plant plant) {
		this.plantMapper.insert(plant);
	}

	@Override
	public List<Plant> queryPlantByCondition(PlantQueryCondition condition) {
		List<Plant> plantList = this.plantMapper.selectByCondition(condition);
		return plantList;
	}

	@Override
	public void updatePlant(Plant plant) {
		this.plantMapper.updateByPrimaryKeySelective(plant);
	}
	
	@Override
	public List<PlantPicture> queryPictureById(String plantId) {
		return this.plantPictureMapper.selectByPrimaryKey(Integer.parseInt(plantId));
	}

	@Override
	public Plant queryPlantById(int plantId) {
		return this.plantMapper.selectByPrimaryKey(plantId);
	}

	@Override
	public void exportUserInfo(HttpServletRequest request, HttpServletResponse response, List<Plant> list) throws Exception {
		//表头
		String sTitle = "植物Id,植物名,描述,状态,创建时间";
		//保存csv文件名字
		String fName = "Plant";
		//每列对应属性
		String mapKey = "plantid,plantname,descript,status,ceratetime";
		List<HashMap<String, Object>> userList = new ArrayList<HashMap<String, Object>>();
		try {
			for (Plant plant : list) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("plantid", String.valueOf(plant.getPlantid()));
				map.put("plantname", plant.getPlantname());
				map.put("descript", plant.getDescript());
				map.put("status", "0".equals(plant.getStatus())?"无效":"有效");
				map.put("ceratetime", DateUtils.dateToString(plant.getCeratetime()));
				userList.add(map);
			}
			OutputStream os = response.getOutputStream();
			CSVUtils.responseSetProperties(fName, response);
			CSVUtils.doExport(userList, sTitle, mapKey, os);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public void insertPicture(PlantPicture picture) {
		this.plantPictureMapper.insert(picture);
	}

	@Override
	public void updatePicture(PlantPicture picture) {
		this.plantPictureMapper.updateByPrimaryKeySelective(picture);
	}

	@Override
	public List<PlantInfo> queryPlanInfoByCondition(PlantInfoConditon plantCondition) {
		return this.plantMapper.queryPlanInfoByCondition(plantCondition);
	}

}
