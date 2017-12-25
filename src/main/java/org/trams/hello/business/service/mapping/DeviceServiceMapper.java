/*
 * Created on 11 thg 1 2017 ( Time 10:47:42 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.Device;
import org.trams.hello.bean.jpa.DeviceEntity;
import org.trams.hello.bean.jpa.UserEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class DeviceServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public DeviceServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'DeviceEntity' to 'Device'
	 * @param deviceEntity
	 */
	public Device mapDeviceEntityToDevice(DeviceEntity deviceEntity) {
		if(deviceEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Device device = map(deviceEntity, Device.class);

		//--- Link mapping ( link to User )
		if(deviceEntity.getUser() != null) {
			device.setUserId(deviceEntity.getUser().getId());
		}
		return device;
	}
	
	/**
	 * Mapping from 'Device' to 'DeviceEntity'
	 * @param device
	 * @param deviceEntity
	 */
	public void mapDeviceToDeviceEntity(Device device, DeviceEntity deviceEntity) {
		if(device == null) {
			return;
		}

		//--- Generic mapping 
		map(device, deviceEntity);

		//--- Link mapping ( link : device )
		if( hasLinkToUser(device) ) {
			UserEntity user1 = new UserEntity();
			user1.setId( device.getUserId() );
			deviceEntity.setUser( user1 );
		} else {
			deviceEntity.setUser( null );
		}

	}
	
	/**
	 * Verify that User id is valid.
	 * @param User User
	 * @return boolean
	 */
	private boolean hasLinkToUser(Device device) {
		if(device.getUserId() != null) {
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