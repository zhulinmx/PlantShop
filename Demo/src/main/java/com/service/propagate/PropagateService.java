package com.service.propagate;

import java.util.List;

import com.domain.PropagateQueryCondition;
import com.mapper.propagate.persistent.Propagate;

public interface PropagateService {

	List<Propagate> queryPropagate(PropagateQueryCondition condition);

	void insertPropagate(Propagate propagate);

	void updatePropagateInfo(Propagate info);

	Propagate queryPropagateById(Integer propagateId);

}
