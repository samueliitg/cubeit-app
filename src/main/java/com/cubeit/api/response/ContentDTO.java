package com.cubeit.api.response;

import com.cubeit.entity.Content;

public class ContentDTO {

	private Integer id;
	private String link;
	private Integer user_id;

	public ContentDTO(Content content, Integer userId) {
		this.id = content.getId();
		this.link = content.getUrl();
		this.user_id = userId;
	}

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

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
}
