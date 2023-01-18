package com.sbs.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dto.SportsFieldDto;
import com.sbs.entity.SportsField;
import com.sbs.repository.SportsFieldRepository;
import com.sbs.service.SportsFieldService;
@Service
public class SportsFieldServiceImpl implements SportsFieldService {
	@Autowired
	private SportsFieldRepository SportsRepository;

	@Override
	public SportsFieldDto addFields(SportsFieldDto fieldDto) {
		System.out.println(fieldDto);
		SportsField field=new SportsField();
		BeanUtils.copyProperties(fieldDto, field);
		SportsRepository.save(field);
		return fieldDto;
	}

	@Override
	public List<SportsField> showSportsFields() {
		List<SportsField> fields=SportsRepository.findAll();
		return fields;
	}

	

}
