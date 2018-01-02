package com.gladigator.Daos;

import org.springframework.stereotype.Repository;

import com.gladigator.Entities.BodyType;

@Repository
public class BodyTypeDaoImpl extends GenericDaoImpl<BodyType> implements BodyTypeDao{

	public BodyTypeDaoImpl() {
		super(BodyType.class);
	}

}
