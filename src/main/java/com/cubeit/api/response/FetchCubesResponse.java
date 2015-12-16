package com.cubeit.api.response;

import java.util.ArrayList;
import java.util.List;

public class FetchCubesResponse extends Response {
	private List<CubeDTO> cubes = new ArrayList<CubeDTO>();

	public List<CubeDTO> getCubes() {
		return cubes;
	}

	public void setCubes(List<CubeDTO> cubes) {
		this.cubes = cubes;
	}
}
