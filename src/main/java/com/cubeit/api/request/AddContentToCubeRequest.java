package com.cubeit.api.request;

public class AddContentToCubeRequest extends BaseRequest {

	private static final long serialVersionUID = 1L;

	private String content_id;

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
}
