package com.cubeit.api.response;

public class AddContentToCubeResponse extends Response {

	private Integer id;
	private Integer cube_id;
	private Integer content_id;

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

	public Integer getContent_id() {
		return content_id;
	}

	public void setContent_id(Integer content_id) {
		this.content_id = content_id;
	}
}
