package com.cubeit.api.response;

import java.util.ArrayList;
import java.util.List;

public class FetchContentsResponse extends Response {
	private List<ContentDTO> contents = new ArrayList<ContentDTO>();

	public List<ContentDTO> getContents() {
		return contents;
	}

	public void setContents(List<ContentDTO> contents) {
		this.contents = contents;
	}
}
