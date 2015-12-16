package com.cubeit.api.response;

public class CreateCubeResponse extends Response {

	private Integer id;
	private String name;
	private Integer user_id;

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

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer userId) {
		this.user_id = userId;
	}
}
