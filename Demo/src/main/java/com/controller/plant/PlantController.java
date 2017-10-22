package com.controller.plant;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.domain.PlantQueryCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.plant.persistent.Plant;
import com.mapper.plant.persistent.PlantCategory;
import com.mapper.plant.persistent.PlantPicture;
import com.mapper.user.persistent.User;
import com.service.plant.PlantService;
import com.util.IntegerUtils;

@Controller
@RequestMapping
public class PlantController {
	
	@Resource
	private PlantService plantService;
	 
	@RequestMapping("/toAddPlant.do")
	public String toAddPlant(HttpServletRequest request,Model model){
		List<PlantCategory> plantCategoryList = plantService.queryPlantCategory();
		model.addAttribute("categorylist", plantCategoryList);
		return "plant/addPlant";
	}
	 
	@RequestMapping("/addPlant.do")
	public String addPlant(HttpServletRequest request,Model model){
		try {
			Plant plant = new Plant();//应该抽一个工具类的
			plant.setDescript(request.getParameter("descript"));
			plant.setPlantname(request.getParameter("plantname"));
			plant.setCategoryid(IntegerUtils.StrToInteger((request.getParameter("categoryid").substring(0, 1))));
			plant.setCategoryid(new Integer(1));
			plant.setStatus(IntegerUtils.StrToInteger(request.getParameter("status")));
			plantService.insertPlant(plant);
		} catch (Exception e) {
			return "error";
		}
		return "redirect:toSearchPlant.do";
	}
	 
	@RequestMapping("/toSearchPlant.do")
	public String toSearchPlant(HttpServletRequest request,Model model){
		Integer page = new Integer(1);
		Page<User> plantList = PageHelper.startPage(page,5);
		plantService.queryPlantByCondition(null);
		model.addAttribute("plantList", plantList);
		model.addAttribute("pages", plantList.getPages());
		return "plant/searchPlant";
	}
	
	@RequestMapping("/searchPlant.do")
	public String searchPlant(HttpServletRequest request,Model model) throws Exception{
		Integer page = Integer.parseInt(request.getParameter("page"));
		PlantQueryCondition condition = new PlantQueryCondition();
		String plantName = request.getParameter("plantname");
		Integer status = IntegerUtils.StrToInteger(request.getParameter("status"));
		condition.setPlantName(plantName);
		condition.setStatus(status);
		Page<User> plantList = PageHelper.startPage(page,5);
		plantService.queryPlantByCondition(condition);
		model.addAttribute("plantList", plantList);
		model.addAttribute("pages", plantList.getPages());
		model.addAttribute("plantName", plantName);
    	model.addAttribute("status", status);
		return "plant/searchPlant";
	}
	 
	@RequestMapping("/detailPlant.do")
	public String todetailPlant(HttpServletRequest request,Model model){
		String plantId = request.getParameter("plantid");
		if (plantId==null) {
			return "redirect:toSearchPlant.do";
		}
		Plant plant = plantService.queryPlantById(Integer.parseInt(plantId));
		List<PlantPicture> pictureList = plantService.queryPictureById(plantId);
		PlantPicture picture = new PlantPicture();
		if (pictureList.size()>0) {
			picture = pictureList.get(0);
		}
		List<PlantCategory> plantCategoryList = plantService.queryPlantCategory();
		model.addAttribute("categorylist", plantCategoryList);
		model.addAttribute("fileUrl", picture.getPicture());
		model.addAttribute("plant", plant);
		model.addAttribute("picture", picture);
		return "plant/detailPlant";
	}
	 
	@RequestMapping("/toEditPlant.do")
	public String toEditPlant(HttpServletRequest request,Model model) throws Exception{
		String plantId = request.getParameter("plantid");
		if (plantId==null) {
			return "plant/addPlant";
		}
		Plant plant = plantService.queryPlantById(IntegerUtils.StrToInteger(plantId));
		List<PlantPicture> pictureList = plantService.queryPictureById(plantId);
		PlantPicture picture = new PlantPicture();
		if (pictureList.size()>0) {
			picture = pictureList.get(0);
		}
		List<PlantCategory> plantCategoryList = plantService.queryPlantCategory();
		List<PlantCategory> list = new ArrayList<PlantCategory>();
		for (PlantCategory plantCategory : plantCategoryList) {
			plantCategory.setCategoryname(plantCategory.getCategoryname());
			list.add(plantCategory);
		}
		model.addAttribute("list", list);
		model.addAttribute("plant", plant);
		model.addAttribute("fileUrl", picture.getPicture());
		return "plant/editPlant";
	}
	 
	@RequestMapping("/editPlant.do")
	public String editPlant(HttpServletRequest request,Model model){
		try {
			Plant plant = new Plant();
			plant.setPlantid(IntegerUtils.StrToInteger(request.getParameter("id")));
			plant.setCategoryid(IntegerUtils.StrToInteger(request.getParameter("categoryid").substring(0, 1)));
			plant.setDescript(request.getParameter("descript"));
			plant.setPlantname(request.getParameter("plantname"));
			plant.setStatus(IntegerUtils.StrToInteger(request.getParameter("status")));
			plantService.updatePlant(plant);
			return "redirect:toSearchPlant.do";
		} catch (Exception e) {
			return "error";
		}
	 }
	@RequestMapping("/toAddPlantPicture.do")
	public String toAddPlantPicture(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,Model model){
		String plantId = request.getParameter("plantid");
		System.out.println(plantId + "|开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
//        String fileName = file.getOriginalFilename();
        String fileName = new Date().getTime()+".jpg";
        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()) {
            targetFile.mkdirs(); 
        }
        //保存  
        try {  
            file.transferTo(targetFile);
            PlantPicture picture = new PlantPicture();
            picture.setPicture(request.getContextPath()+"/upload/"+fileName);
            picture.setPlantid(IntegerUtils.StrToInteger(plantId));
            List<PlantPicture> pictureList = plantService.queryPictureById(plantId);
    		if (pictureList.size() >0) {
    			picture.setId(pictureList.get(0).getId());
    			plantService.updatePicture(picture);
    		} else {
    			plantService.insertPicture(picture);
    		}
    		String s = "ok!";
    		JSONObject ok = JSONObject.parseObject(s);
    		return ok.toJSONString();
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return "redirect:toSearchPlant.do";
	}
	
	@RequestMapping("/exportPlantInfo.do")
    public void exportPlantInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	try {
    		PlantQueryCondition condition = new PlantQueryCondition();
    		condition.setPlantName(request.getParameter("plantName"));
    		String status = request.getParameter("status");
    		if (status!=null && status != "") {
    			condition.setStatus(Integer.parseInt(status));
    		}
        	List<Plant> plantList = plantService.queryPlantByCondition(condition);
        	plantService.exportUserInfo(request, response, plantList);
		} catch (Exception e) {
		}
    }
	
}
