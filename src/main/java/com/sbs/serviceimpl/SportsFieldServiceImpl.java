package com.sbs.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dto.SportsFieldDto;
import com.sbs.entity.SportsField;
import com.sbs.repository.SportsFieldRepository;
import com.sbs.service.SportsFieldService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class SportsFieldServiceImpl implements SportsFieldService {
	@Autowired
	private SportsFieldRepository sportsRepository;

	@Override
	public SportsFieldDto addFields(SportsFieldDto fieldDto) {
		SportsField field=new SportsField();
		BeanUtils.copyProperties(fieldDto, field);
		sportsRepository.save(field);
		log.info(field.getName()+" field save successfully");
		return fieldDto;
	}

	@Override
	public List<SportsField> showSportsFields() {
		log.info("All Sports field fetch successfully");
		return sportsRepository.findAll();
	}

	

}
