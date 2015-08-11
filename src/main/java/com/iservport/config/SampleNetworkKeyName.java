package com.iservport.config;

import java.io.Serializable;

import org.helianto.core.internal.KeyNameAdapter;

/**
 * Sample enum to demonstrate how standard values may be
 * changed when passed as constructor arguments to the
 * RootQueryService bean.
 * 
 * @author mauriciofernandesdecastro
 */
public enum SampleNetworkKeyName implements KeyNameAdapter {
	
	FIRST('1'),
	SECOND('2');
	
	private SampleNetworkKeyName(char value) {
		this.value = value;
	}
	
	private char value;

	@Override
	public String getCode() {
		return String.valueOf(value);
	}

	@Override
	public Serializable getKey() {
		return value;
	}

	@Override
	public String getName() {
		return name();
	}

}
