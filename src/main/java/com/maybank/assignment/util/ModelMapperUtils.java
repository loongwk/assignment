package com.maybank.assignment.util;

import org.modelmapper.ModelMapper;

public class ModelMapperUtils {
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	static {
        modelMapper = new ModelMapper();
        
	}
	
	public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

}
