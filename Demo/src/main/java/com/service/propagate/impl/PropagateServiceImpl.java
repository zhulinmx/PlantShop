package com.service.propagate.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.domain.PropagateQueryCondition;
import com.mapper.propagate.PropagateMapper;
import com.mapper.propagate.persistent.Propagate;
import com.service.propagate.PropagateService;

@Repository
public class PropagateServiceImpl implements PropagateService {

	@Resource 
	private PropagateMapper propagateMapper;
	
	@Override
	public List<Propagate> queryPropagate(PropagateQueryCondition condition) {
		List<Propagate> propagateList = this.propagateMapper.queryUserByCondition(condition);
		return propagateList;
	}

	@Override
	public void insertPropagate(Propagate propagate) {
		this.propagateMapper.insert(propagate);
	}

	@Override
	public void updatePropagateInfo(Propagate propagate) {
		this.propagateMapper.updateByPrimaryKeySelective(propagate);
	}

	@Override
	public Propagate queryPropagateById(Integer propagateId) {
		return this.propagateMapper.selectByPrimaryKey(propagateId);
	}

}
