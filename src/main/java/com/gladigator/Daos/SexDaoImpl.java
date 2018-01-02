package com.gladigator.Daos;

import org.springframework.stereotype.Repository;

import com.gladigator.Entities.Sex;

@Repository
public class SexDaoImpl extends GenericDaoImpl<Sex> implements SexDao{

	public SexDaoImpl() {
		super(Sex.class);
	}

}
