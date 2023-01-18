package com.sbs.service;

import java.util.List;

import com.sbs.dto.SportsFieldDto;
import com.sbs.entity.SportsField;

public interface SportsFieldService {

	SportsFieldDto addFields(SportsFieldDto fieldDto);

	List<SportsField> showSportsFields();

	

}
