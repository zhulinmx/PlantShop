package com.mapper.propagate;

import java.util.List;

import com.domain.PropagateQueryCondition;
import com.mapper.propagate.persistent.Propagate;

public interface PropagateMapper {
    int deleteByPrimaryKey(Integer propagateid);

    int insert(Propagate record);

    int insertSelective(Propagate record);

    Propagate selectByPrimaryKey(Integer propagateid);

    int updateByPrimaryKeySelective(Propagate record);

    int updateByPrimaryKey(Propagate record);

	List<Propagate> queryUserByCondition(PropagateQueryCondition condition);
}