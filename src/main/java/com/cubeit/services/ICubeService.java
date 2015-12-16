package com.cubeit.services;

import com.cubeit.api.request.AddContentToCubeRequest;
import com.cubeit.api.request.CreateContentRequest;
import com.cubeit.api.request.CreateCubeRequest;
import com.cubeit.api.request.RegisterUserRequest;
import com.cubeit.api.request.SharingRequest;
import com.cubeit.api.response.AddContentToCubeResponse;
import com.cubeit.api.response.CreateContentResponse;
import com.cubeit.api.response.CreateCubeResponse;
import com.cubeit.api.response.FetchContentsResponse;
import com.cubeit.api.response.FetchCubesResponse;
import com.cubeit.api.response.RegisterUserResponse;
import com.cubeit.api.response.Response;
import com.cubeit.api.response.SharingContentResponse;
import com.cubeit.api.response.SharingCubeResponse;

public interface ICubeService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);

    CreateCubeResponse createCube(CreateCubeRequest createCubeRequest, String userId);

    CreateContentResponse createContent(CreateContentRequest createContentRequest, String userId);

    AddContentToCubeResponse addContentToCube(AddContentToCubeRequest addContentToCubeRequest, String userId, String cubeId);

    Response deleteContentFromCube(String userId, String cubeId, String contentId);

    Response deleteCube(String userId, String cubeId);

    SharingCubeResponse shareCubeWithUser(SharingRequest sharingRequest, String userId, String cubeId);

    SharingContentResponse shareContentWithUser(SharingRequest sharingRequest, String userId, String contentId);

    FetchCubesResponse fetchAllCubesForUser(String userId);

    FetchContentsResponse fetchAllContentsForUser(String userId);
}
