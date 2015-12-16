package com.cubeit.api.response;

public class CreateContentResponse extends Response {

	private Integer id;
	private String link;
	private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getUserId() {
		return user_id;
	}

	public void setUserId(Integer userId) {
		this.user_id = userId;
	}
}
