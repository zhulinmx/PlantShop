package com.controller.propagate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.domain.PropagateQueryCondition;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mapper.propagate.persistent.Propagate;
import com.service.propagate.PropagateService;
import com.util.DateUtils;
import com.util.IntegerUtils;

@Controller
@RequestMapping
public class PropagateController {
	
	@Resource
	private PropagateService service;
	
	@RequestMapping("/searchPropagate.do")
    public String toPropagate(HttpServletRequest request,Model model){
		try {
			String flag = request.getParameter("flag");
			Integer page = new Integer(1);
			String title = request.getParameter("title");
			Integer status = IntegerUtils.StrToInteger(request.getParameter("status"));
			PropagateQueryCondition condition = new PropagateQueryCondition();
			if (flag != null) {
				page = Integer.parseInt(request.getParameter("page"));
				condition.setStatus(status);
				condition.setTitle(title);
			}
			Page<Propagate> propagateList = PageHelper.startPage(page,5);
			service.queryPropagate(condition);
			model.addAttribute("propagateList", propagateList);
			model.addAttribute("pages", propagateList.getPages());
			model.addAttribute("title", title);
	    	model.addAttribute("status", status);
			return "propagate/searchPropagate";
		} catch (Exception e) {
			return "propagate/searchPropagate";
		}
    }
    
    @RequestMapping("/addPropagate.do")
    public String toAddPropagate(HttpServletRequest request,Model model) throws Exception{  
    	String flag = request.getParameter("flag");
    	if (flag == null) {
    		try {
    			Propagate propagate = new Propagate();
    			propagate.setContent(request.getParameter("content"));
    			propagate.setDeadlinetime(DateUtils.stringToDate(request.getParameter("deadlinetime")));
    			propagate.setStatus(Integer.parseInt(request.getParameter("status")));
    			propagate.setTitle(request.getParameter("title"));
            	service.insertPropagate(propagate);
            	return "redirect:searchPropagate.do"; 
    		} catch (Exception e) {
    			return "error";
    		}
    	}
    	return "propagate/addPropagate";
    }
    
    @RequestMapping("/detailPropagate.do")
    public String toDetailPropagate(HttpServletRequest request,Model model){ 
		Integer propagateId = Integer.parseInt(request.getParameter("id"));
		Propagate propagate = service.queryPropagateById(propagateId);
		model.addAttribute("deadlinetime", DateUtils.unixDateToDate(propagate.getDeadlinetime()));
		model.addAttribute("ceratetime", DateUtils.unixDateToDate(propagate.getCeratetime()));
		model.addAttribute("propagate", propagate);
        return "propagate/detailPropagate"; 
    }
    
    @RequestMapping("/editPropagate.do")
    public String toEditPropagate(HttpServletRequest request,Model model){
    	try {
    		String flag = request.getParameter("flag");
    		Integer propagateId = Integer.parseInt(request.getParameter("id"));
    		Propagate propagate = service.queryPropagateById(propagateId);
    		if(propagate.equals(null)){
    			return "propagate/addPropagate";
    		}
    		if (flag != null) {
    			model.addAttribute("dateStr", DateUtils.unixDateToDate(propagate.getDeadlinetime()));
    			model.addAttribute("propagate", propagate);
    			return "propagate/editPropagate"; 
			}
    		Propagate info = new Propagate();
    		info.setContent(request.getParameter("content"));
    		info.setDeadlinetime(DateUtils.stringToDate(request.getParameter("deadlinetime")));
    		info.setStatus(Integer.parseInt(request.getParameter("status")));
    		info.setTitle(request.getParameter("title"));
    		info.setUserid(IntegerUtils.StrToInteger((request.getParameter("userid"))));
    		info.setPropagateid(propagateId);
        	service.updatePropagateInfo(info);
        	return "redirect:searchPropagate.do"; 
		} catch (Exception e) {
			return "error";
		}
       
    }
	

}
