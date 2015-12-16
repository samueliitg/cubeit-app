package com.cubeit.dao;

import java.util.List;

import com.cubeit.entity.Content;
import com.cubeit.entity.Cube;
import com.cubeit.entity.CubeContentMapping;
import com.cubeit.entity.CubeitUser;
import com.cubeit.entity.UserSharedContent;
import com.cubeit.entity.UserSharedCube;

public interface ICubeDao {

    void saveUser(CubeitUser user);

    CubeitUser fetchUser(String name, String city);

    CubeitUser fetchUserById(Integer iUserId);

    void saveCube(Cube cube);

    Cube fetchCube(String name);

    void saveContent(Content content);

    Content fetchContent(String url);

    Cube fetchCubeById(Integer cubeId);

    Content fetchContentById(Integer contentId);

    CubeContentMapping fetchExistingCubeContentMapping(Cube cube, Content content);

    void saveCubeContentMapping(CubeContentMapping mapping);

    Integer deleteCubeContentMapping(Integer id);

    Integer deleteCube(Integer id);

    List<CubeContentMapping> fetchAllCubeContentMappingsForCube(Cube cube);

    List<UserSharedCube> fetchAllUserSharedCubeMappingsForCube(Cube cube);

    Integer deleteUserSharedCubeMapping(Integer id);

    UserSharedCube fetchUserSharedCube(CubeitUser sharingWithUser, Cube cube);

    UserSharedContent fetchUserSharedContent(CubeitUser sharingWithUser, Content content);

    void saveUserSharedCube(UserSharedCube userSharedCube);

    void saveUserSharedContent(UserSharedContent userSharedContent);

}
