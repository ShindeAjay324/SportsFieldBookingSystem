package com.sbs.dto.get_all_info_for_admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDtoAdmin {
	private Integer userId;
	private String name;
	private String email;
	private String password;
}
