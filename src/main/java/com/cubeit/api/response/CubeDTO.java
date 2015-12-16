package com.cubeit.api.response;

import com.cubeit.entity.Cube;

public class CubeDTO {

	private Integer id;
	private String name;
	private Integer user_id;

	public CubeDTO(Cube cube, Integer userId){
		this.id = cube.getId();
		this.name = cube.getName();
		this.user_id = userId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
