package com.lawencon.booting.service;

import com.lawencon.booting.model.BaseModel;

public class BaseService {

	<T extends BaseModel> T check(T data, T data2) {
		if(data.getVersion().equals(data2.getVersion())) {
			data2.setVersion(data.getVersion() + 1L);
			return data2;
		} else {
			return null;
		}
	}
}
