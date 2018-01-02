package com.gladigator.Daos;

import org.springframework.stereotype.Repository;

import com.gladigator.Entities.FrequencyOfActivity;

@Repository
public class FrequencyOfActivityDaoImpl extends GenericDaoImpl<FrequencyOfActivity> implements FrequencyOfActivityDao{

	public FrequencyOfActivityDaoImpl(){
		super(FrequencyOfActivity.class);
	}

}
