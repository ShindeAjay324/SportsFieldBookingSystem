package com.sbs.dto.get_all_info_for_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SportsFieldDtoAdmin {
	private Integer sportsFieldId;
	private String name;
	private String location;
	private Integer capacity;
}
