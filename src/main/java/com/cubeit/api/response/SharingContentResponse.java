package com.cubeit.api.response;

public class SharingContentResponse extends Response {

	private Integer id;
	private Integer content_id;
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContent_id() {
		return content_id;
	}

	public void setContent_id(Integer content_id) {
		this.content_id = content_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
