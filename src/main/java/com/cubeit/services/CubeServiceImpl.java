package com.cubeit.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubeit.api.request.AddContentToCubeRequest;
import com.cubeit.api.request.CreateContentRequest;
import com.cubeit.api.request.CreateCubeRequest;
import com.cubeit.api.request.RegisterUserRequest;
import com.cubeit.api.request.SharingRequest;
import com.cubeit.api.response.AddContentToCubeResponse;
import com.cubeit.api.response.ContentDTO;
import com.cubeit.api.response.CreateContentResponse;
import com.cubeit.api.response.CreateCubeResponse;
import com.cubeit.api.response.CubeDTO;
import com.cubeit.api.response.FetchContentsResponse;
import com.cubeit.api.response.FetchCubesResponse;
import com.cubeit.api.response.RegisterUserResponse;
import com.cubeit.api.response.Response;
import com.cubeit.api.response.SharingContentResponse;
import com.cubeit.api.response.SharingCubeResponse;
import com.cubeit.dao.ICubeDao;
import com.cubeit.entity.Content;
import com.cubeit.entity.Cube;
import com.cubeit.entity.CubeContentMapping;
import com.cubeit.entity.CubeitUser;
import com.cubeit.entity.UserSharedContent;
import com.cubeit.entity.UserSharedCube;

@Service("cubeService")
public class CubeServiceImpl implements ICubeService {

    @Autowired
    ICubeDao cubeDao;

    @Override
    @Transactional
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        RegisterUserResponse response = new RegisterUserResponse();
        if (registerUserRequest.getName() != null && registerUserRequest.getName().length() > 0 && registerUserRequest.getCity() != null
                && registerUserRequest.getCity().length() > 0) {
            CubeitUser user = new CubeitUser();
            user.setName(registerUserRequest.getName());
            user.setCity(registerUserRequest.getCity());
            cubeDao.saveUser(user);
            CubeitUser insertedUser = cubeDao.fetchUser(registerUserRequest.getName(), registerUserRequest.getCity());
            response.setId(insertedUser.getId());
            response.setName(insertedUser.getName());
            response.setCity(insertedUser.getCity());
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public CreateCubeResponse createCube(CreateCubeRequest createCubeRequest, String userId) {
        CreateCubeResponse createCubeResponse = new CreateCubeResponse();
        Integer iUserId = -1;
        try {
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iUserId > 0 && createCubeRequest.getName() != null && createCubeRequest.getName().length() > 0) {
            CubeitUser user = cubeDao.fetchUserById(iUserId);
            if (user == null) {
                createCubeResponse.setResponse("Invalid user data.");
                createCubeResponse.setResponseCode(Response.FAL_RESP_CODE);
                return createCubeResponse;
            }
            Cube cube = new Cube();
            cube.setName(createCubeRequest.getName());
            cube.setOwner(user);
            cubeDao.saveCube(cube);
            Cube insertedCube = cubeDao.fetchCube(createCubeRequest.getName());
            createCubeResponse.setId(insertedCube.getId());
            createCubeResponse.setUserId(insertedCube.getOwner().getId());
            createCubeResponse.setName(insertedCube.getName());
        } else {
            createCubeResponse.setResponse("Invalid request.");
            createCubeResponse.setResponseCode(Response.FAL_RESP_CODE);
        }
        return createCubeResponse;
    }

    @Override
    @Transactional
    public CreateContentResponse createContent(CreateContentRequest createContentRequest, String userId) {
        CreateContentResponse createContentResponse = new CreateContentResponse();
        Integer iUserId = -1;
        try {
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iUserId > 0 && createContentRequest.getLink() != null && createContentRequest.getLink().length() > 0) {
            CubeitUser user = cubeDao.fetchUserById(iUserId);
            if (user == null) {
                createContentResponse.setResponse("Invalid user data.");
                createContentResponse.setResponseCode(Response.FAL_RESP_CODE);
                return createContentResponse;
            }
            Content content = new Content();
            content.setUrl(createContentRequest.getLink());
            content.setOwner(user);
            cubeDao.saveContent(content);
            Content insertedContent = cubeDao.fetchContent(createContentRequest.getLink());
            createContentResponse.setId(insertedContent.getId());
            createContentResponse.setUserId(insertedContent.getOwner().getId());
            createContentResponse.setLink(insertedContent.getUrl());
        } else {
            createContentResponse.setResponse("Invalid request.");
            createContentResponse.setResponseCode(Response.FAL_RESP_CODE);
        }
        return createContentResponse;
    }

    @Override
    @Transactional
    public AddContentToCubeResponse addContentToCube(AddContentToCubeRequest addContentToCubeRequest, String userId, String cubeId) {
        AddContentToCubeResponse addContentToCubeResponse = new AddContentToCubeResponse();
        Integer iCubeId = -1;
        Integer iContentId = -1;
        Integer iUserId = -1;
        try {
            iCubeId = Integer.valueOf(cubeId);
            iContentId = Integer.valueOf(addContentToCubeRequest.getContent_id());
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iCubeId > 0 && iContentId > 0 && iUserId > 0) {
            Cube cube = cubeDao.fetchCubeById(iCubeId);
            Content content = cubeDao.fetchContentById(iContentId);
            CubeContentMapping mapping = cubeDao.fetchExistingCubeContentMapping(cube, content);
            if (mapping == null) {
                mapping = new CubeContentMapping();
                mapping.setContent(content);
                mapping.setCube(cube);
                cubeDao.saveCubeContentMapping(mapping);
                CubeContentMapping insertedMapping = cubeDao.fetchExistingCubeContentMapping(cube, content);
                addContentToCubeResponse.setContent_id(iContentId);
                addContentToCubeResponse.setCube_id(iCubeId);
                addContentToCubeResponse.setId(insertedMapping.getId());
            } else {
                addContentToCubeResponse.setResponse("Content already in cube.");
                addContentToCubeResponse.setContent_id(iContentId);
                addContentToCubeResponse.setCube_id(iCubeId);
                addContentToCubeResponse.setId(mapping.getId());
            }
        } else {
            addContentToCubeResponse.setResponse("Invalid request.");
            addContentToCubeResponse.setResponseCode(Response.FAL_RESP_CODE);
        }
        return addContentToCubeResponse;
    }

    @Override
    @Transactional
    public Response deleteContentFromCube(String userId, String cubeId, String contentId) {
        Response response = new Response();
        Integer iCubeId = -1;
        Integer iContentId = -1;
        Integer iUserId = -1;
        try {
            iCubeId = Integer.valueOf(cubeId);
            iContentId = Integer.valueOf(contentId);
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iCubeId > 0 && iContentId > 0 && iUserId > 0) {
            Cube cube = cubeDao.fetchCubeById(iCubeId);
            Content content = cubeDao.fetchContentById(iContentId);
            CubeContentMapping mapping = cubeDao.fetchExistingCubeContentMapping(cube, content);
            if (mapping == null) {
                response.setResponse("Content doesn't exist in the specified cube.");
                response.setResponseCode(Response.FAL_RESP_CODE);
            } else {
                Integer count = cubeDao.deleteCubeContentMapping(mapping.getId());
                if (count <= 0) {
                    response.setResponse("An error occurred.");
                    response.setResponseCode(Response.FAL_RESP_CODE);
                }
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public Response deleteCube(String userId, String cubeId) {
        Response response = new Response();
        Integer iCubeId = -1;
        Integer iUserId = -1;
        try {
            iCubeId = Integer.valueOf(cubeId);
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iCubeId > 0 && iUserId > 0) {
            Cube cube = cubeDao.fetchCubeById(iCubeId);
            if (cube == null) {
                response.setResponse("Invalid cube data.");
                response.setResponseCode(Response.FAL_RESP_CODE);
                return response;
            }
            List<UserSharedCube> userSharedCubeMappingsForCube = cubeDao.fetchAllUserSharedCubeMappingsForCube(cube);
            for (UserSharedCube userSharedCubeMapping : userSharedCubeMappingsForCube) {
                cubeDao.deleteUserSharedCubeMapping(userSharedCubeMapping.getId());
            }
            List<CubeContentMapping> mappings = cubeDao.fetchAllCubeContentMappingsForCube(cube);
            for (CubeContentMapping mapping : mappings) {
                cubeDao.deleteCubeContentMapping(mapping.getId());
            }
            Integer count = cubeDao.deleteCube(cube.getId());
            if (count <= 0) {
                response.setResponse("An error occurred.");
                response.setResponseCode(Response.FAL_RESP_CODE);
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public SharingCubeResponse shareCubeWithUser(SharingRequest sharingRequest, String userId, String cubeId) {
        SharingCubeResponse response = new SharingCubeResponse();
        Integer iCubeId = -1;
        Integer iUserId = -1;
        Integer iSharingWithUserId = -1;
        try {
            iCubeId = Integer.valueOf(cubeId);
            iSharingWithUserId = Integer.valueOf(sharingRequest.getUser_id());
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iCubeId > 0 && iSharingWithUserId > 0 && iUserId > 0) {
            CubeitUser sharingWithUser = cubeDao.fetchUserById(iSharingWithUserId);
            Cube cube = cubeDao.fetchCubeById(iCubeId);
            UserSharedCube userSharedCube = cubeDao.fetchUserSharedCube(sharingWithUser, cube);
            if (userSharedCube == null) {
                userSharedCube = new UserSharedCube();
                userSharedCube.setUser(sharingWithUser);
                userSharedCube.setCube(cube);
                cubeDao.saveUserSharedCube(userSharedCube);
                UserSharedCube insertedUserSharedCube = cubeDao.fetchUserSharedCube(sharingWithUser, cube);
                response.setCube_id(iCubeId);
                response.setUser_id(iSharingWithUserId);
                response.setId(insertedUserSharedCube.getId());
            } else {
                response.setCube_id(iCubeId);
                response.setUser_id(iSharingWithUserId);
                response.setId(userSharedCube.getId());
                response.setResponse("Cube is already shared with user.");
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public SharingContentResponse shareContentWithUser(SharingRequest sharingRequest, String userId, String contentId) {
        SharingContentResponse response = new SharingContentResponse();
        Integer iContentId = -1;
        Integer iUserId = -1;
        Integer iSharingWithUserId = -1;
        try {
            iContentId = Integer.valueOf(contentId);
            iSharingWithUserId = Integer.valueOf(sharingRequest.getUser_id());
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iContentId > 0 && iSharingWithUserId > 0 && iUserId > 0) {
            CubeitUser sharingWithUser = cubeDao.fetchUserById(iSharingWithUserId);
            Content content = cubeDao.fetchContentById(iContentId);
            UserSharedContent userSharedContent = cubeDao.fetchUserSharedContent(sharingWithUser, content);
            if (userSharedContent == null) {
                userSharedContent = new UserSharedContent();
                userSharedContent.setUser(sharingWithUser);
                userSharedContent.setContent(content);
                cubeDao.saveUserSharedContent(userSharedContent);
                UserSharedContent insertedUserSharedContent = cubeDao.fetchUserSharedContent(sharingWithUser, content);
                response.setContent_id(iContentId);
                response.setUser_id(iSharingWithUserId);
                response.setId(insertedUserSharedContent.getId());
            } else {
                response.setContent_id(iContentId);
                response.setUser_id(iSharingWithUserId);
                response.setId(userSharedContent.getId());
                response.setResponse("Content is already shared with user.");
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public FetchCubesResponse fetchAllCubesForUser(String userId) {
        FetchCubesResponse response = new FetchCubesResponse();
        Integer iUserId = -1;
        try {
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iUserId > 0) {
            CubeitUser user = cubeDao.fetchUserById(iUserId);
            if (user == null) {
                response.setResponse("Invalid user data.");
                response.setResponseCode(Response.FAL_RESP_CODE);
            } else {
                Set<Cube> ownedCubes = user.getOwnedCubes();
                Set<UserSharedCube> sharedCubes = user.getSharedCubes();
                for (UserSharedCube sharedCube : sharedCubes) {
                    ownedCubes.add(sharedCube.getCube());
                }
                for (Cube ownedCube : ownedCubes) {
                    CubeDTO dto = new CubeDTO(ownedCube, iUserId);
                    response.getCubes().add(dto);
                }
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }

    @Override
    @Transactional
    public FetchContentsResponse fetchAllContentsForUser(String userId) {
        FetchContentsResponse response = new FetchContentsResponse();
        Integer iUserId = -1;
        try {
            iUserId = Integer.valueOf(userId);
        } catch (Exception e) {

        }
        if (iUserId > 0) {
            CubeitUser user = cubeDao.fetchUserById(iUserId);
            if (user == null) {
                response.setResponse("Invalid user data.");
                response.setResponseCode(Response.FAL_RESP_CODE);
            } else {
                Set<Content> ownedContents = user.getOwnedContents();
                Set<UserSharedContent> sharedContents = user.getSharedContents();
                for (UserSharedContent userSharedContent : sharedContents) {
                    ownedContents.add(userSharedContent.getContent());
                }
                Set<Cube> ownedCubes = user.getOwnedCubes();
                Set<UserSharedCube> sharedCubes = user.getSharedCubes();
                for (UserSharedCube sharedCube : sharedCubes) {
                    ownedCubes.add(sharedCube.getCube());
                }
                for (Cube ownedCube : ownedCubes) {
                    List<CubeContentMapping> contentMappingsForCube = cubeDao.fetchAllCubeContentMappingsForCube(ownedCube);
                    for (CubeContentMapping cubeContentMapping : contentMappingsForCube) {
                        ownedContents.add(cubeContentMapping.getContent());
                    }
                }
                for (Content content : ownedContents) {
                    ContentDTO dto = new ContentDTO(content, iUserId);
                    response.getContents().add(dto);
                }
            }
        } else {
            response.setResponse("Invalid request.");
            response.setResponseCode(Response.FAL_RESP_CODE);
        }
        return response;
    }
}
