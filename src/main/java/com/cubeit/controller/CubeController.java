package com.cubeit.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.cubeit.services.ICubeService;

@Controller
public class CubeController {

    @Autowired
    ICubeService cubeService;

    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RegisterUserResponse registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return cubeService.registerUser(registerUserRequest);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateCubeResponse createCube(@RequestBody CreateCubeRequest createCubeRequest, @PathVariable(value = "userId") String userId) {
        return cubeService.createCube(createCubeRequest, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/content", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CreateContentResponse createContent(@RequestBody CreateContentRequest createContentRequest, @PathVariable(value = "userId") String userId) {
        return cubeService.createContent(createContentRequest, userId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube/{cubeId}/content", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddContentToCubeResponse addContentToCube(@RequestBody AddContentToCubeRequest addContentToCubeRequest, @PathVariable(value = "userId") String userId,
            @PathVariable(value = "cubeId") String cubeId) {
        return cubeService.addContentToCube(addContentToCubeRequest, userId, cubeId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube/{cubeId}/content/{contentId}", method = RequestMethod.DELETE)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteContentFromCube(@PathVariable(value = "userId") String userId, @PathVariable(value = "cubeId") String cubeId,
            @PathVariable(value = "contentId") String contentId) {
        return cubeService.deleteContentFromCube(userId, cubeId, contentId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube/{cubeId}", method = RequestMethod.DELETE)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCube(@PathVariable(value = "userId") String userId, @PathVariable(value = "cubeId") String cubeId) {
        return cubeService.deleteCube(userId, cubeId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube/{cubeId}/share", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SharingCubeResponse shareCubeWithUser(@RequestBody SharingRequest sharingRequest, @PathVariable(value = "userId") String userId,
            @PathVariable(value = "cubeId") String cubeId) {
        return cubeService.shareCubeWithUser(sharingRequest, userId, cubeId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/content/{contentId}/share", method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SharingContentResponse shareContentWithUser(@RequestBody SharingRequest sharingRequest, @PathVariable(value = "userId") String userId,
            @PathVariable(value = "contentId") String contentId) {
        return cubeService.shareContentWithUser(sharingRequest, userId, contentId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/cube", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public FetchCubesResponse fetchAllCubesForUser(@PathVariable(value = "userId") String userId) {
        return cubeService.fetchAllCubesForUser(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/user/{userId}/content", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_JSON)
    public FetchContentsResponse fetchAllContentsForUser(@PathVariable(value = "userId") String userId) {
        return cubeService.fetchAllContentsForUser(userId);
    }
}
