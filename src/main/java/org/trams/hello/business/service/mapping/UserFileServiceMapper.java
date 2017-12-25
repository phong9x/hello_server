/*
 * Created on 29 Apr 2017 ( Time 10:20:35 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.trams.hello.bean.UserFile;
import org.trams.hello.bean.jpa.UserFileEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class UserFileServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public UserFileServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'UserFileEntity' to 'UserFile'
	 * @param userFileEntity
	 */
	public UserFile mapUserFileEntityToUserFile(UserFileEntity userFileEntity) {
		if(userFileEntity == null) {
			return null;
		}

		//--- Generic mapping 
		UserFile userFile = map(userFileEntity, UserFile.class);

		return userFile;
	}
	
	/**
	 * Mapping from 'UserFile' to 'UserFileEntity'
	 * @param userFile
	 * @param userFileEntity
	 */
	public void mapUserFileToUserFileEntity(UserFile userFile, UserFileEntity userFileEntity) {
		if(userFile == null) {
			return;
		}

		//--- Generic mapping 
		map(userFile, userFileEntity);

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