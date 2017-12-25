/*
 * Created on 29 Apr 2017 ( Time 11:01:37 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.CounselorTemp;
import org.trams.hello.bean.jpa.BankEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.CounselorTempEntity;
import org.trams.hello.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CounselorTempServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CounselorTempServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CounselorTempEntity' to 'CounselorTemp'
	 * @param counselorTempEntity
	 */
	public CounselorTemp mapCounselorTempEntityToCounselorTemp(CounselorTempEntity counselorTempEntity) {
		if(counselorTempEntity == null) {
			return null;
		}

		//--- Generic mapping 
		CounselorTemp counselorTemp = map(counselorTempEntity, CounselorTemp.class);

		//--- Link mapping ( link to Bank )
		if(counselorTempEntity.getBank() != null) {
			counselorTemp.setBankId(counselorTempEntity.getBank().getId());
		}
		//--- Link mapping ( link to User )
		if(counselorTempEntity.getUser() != null) {
			counselorTemp.setAdminId(counselorTempEntity.getUser().getId());
		}
		//--- Link mapping ( link to Counselor )
		if(counselorTempEntity.getCounselor() != null) {
			counselorTemp.setCounselorId(counselorTempEntity.getCounselor().getId());
		}
		return counselorTemp;
	}
	
	/**
	 * Mapping from 'CounselorTemp' to 'CounselorTempEntity'
	 * @param counselorTemp
	 * @param counselorTempEntity
	 */
	public void mapCounselorTempToCounselorTempEntity(CounselorTemp counselorTemp, CounselorTempEntity counselorTempEntity) {
		if(counselorTemp == null) {
			return;
		}

		//--- Generic mapping 
		map(counselorTemp, counselorTempEntity);

		//--- Link mapping ( link : counselorTemp )
		if( hasLinkToBank(counselorTemp) ) {
			BankEntity bank1 = new BankEntity();
			bank1.setId( counselorTemp.getBankId() );
			counselorTempEntity.setBank( bank1 );
		} else {
			counselorTempEntity.setBank( null );
		}

		//--- Link mapping ( link : counselorTemp )
		if( hasLinkToUser(counselorTemp) ) {
			UserEntity user2 = new UserEntity();
			user2.setId( counselorTemp.getAdminId() );
			counselorTempEntity.setUser( user2 );
		} else {
			counselorTempEntity.setUser( null );
		}

		//--- Link mapping ( link : counselorTemp )
		if( hasLinkToCounselor(counselorTemp) ) {
			CounselorEntity counselor3 = new CounselorEntity();
			counselor3.setId( counselorTemp.getCounselorId() );
			counselorTempEntity.setCounselor( counselor3 );
		} else {
			counselorTempEntity.setCounselor( null );
		}

	}
	
	/**
	 * Verify that Bank id is valid.
	 * @param Bank Bank
	 * @return boolean
	 */
	private boolean hasLinkToBank(CounselorTemp counselorTemp) {
		if(counselorTemp.getBankId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(CounselorTemp counselorTemp) {
		if(counselorTemp.getAdminId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Counselor id is valid.
	 * @param Counselor Counselor
	 * @return boolean
	 */
	private boolean hasLinkToCounselor(CounselorTemp counselorTemp) {
		if(counselorTemp.getCounselorId() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}