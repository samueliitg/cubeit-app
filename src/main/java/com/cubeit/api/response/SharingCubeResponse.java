package com.cubeit.api.response;

public class SharingCubeResponse extends Response {

	private Integer id;
	private Integer cube_id;
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCube_id() {
		return cube_id;
	}

	public void setCube_id(Integer cube_id) {
		this.cube_id = cube_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
