package com.cubeit.api.response;

public class Response {

	public static final String SUC_RESP_CODE = "100";
	public static final String FAL_RESP_CODE = "101";

	private String response = "Success.";
	private String responseCode = SUC_RESP_CODE;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
}
