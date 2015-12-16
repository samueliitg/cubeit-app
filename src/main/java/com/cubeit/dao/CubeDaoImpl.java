package com.cubeit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cubeit.entity.Content;
import com.cubeit.entity.Cube;
import com.cubeit.entity.CubeContentMapping;
import com.cubeit.entity.CubeitUser;
import com.cubeit.entity.UserSharedContent;
import com.cubeit.entity.UserSharedCube;

@Repository("cubeDao")
public class CubeDaoImpl implements ICubeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(CubeitUser user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public CubeitUser fetchUser(String name, String city) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CubeitUser where name=:name and city=:city");
        query.setParameter("name", name);
        query.setParameter("city", city);
        return (CubeitUser) query.uniqueResult();
    }

    @Override
    public CubeitUser fetchUserById(Integer iUserId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CubeitUser where id=:id");
        query.setParameter("id", iUserId);
        return (CubeitUser) query.uniqueResult();
    }

    @Override
    public void saveCube(Cube cube) {
        sessionFactory.getCurrentSession().save(cube);
    }

    @Override
    public Cube fetchCube(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Cube where name=:name");
        query.setParameter("name", name);
        return (Cube) query.list().get(0);
    }

    @Override
    public void saveContent(Content content) {
        sessionFactory.getCurrentSession().save(content);
    }

    @Override
    public Content fetchContent(String url) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Content where url=:url");
        query.setParameter("url", url);
        return (Content) query.list().get(0);
    }

    @Override
    public Cube fetchCubeById(Integer cubeId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Cube where id=:id");
        query.setParameter("id", cubeId);
        return (Cube) query.uniqueResult();
    }

    @Override
    public Content fetchContentById(Integer contentId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Content where id=:id");
        query.setParameter("id", contentId);
        return (Content) query.uniqueResult();
    }

    @Override
    public CubeContentMapping fetchExistingCubeContentMapping(Cube cube, Content content) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CubeContentMapping where cube=:cube and content=:content");
        query.setParameter("content", content);
        query.setParameter("cube", cube);
        return (CubeContentMapping) query.uniqueResult();
    }

    @Override
    public void saveCubeContentMapping(CubeContentMapping mapping) {
        sessionFactory.getCurrentSession().save(mapping);
    }

    @Override
    public Integer deleteCubeContentMapping(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE from CubeContentMapping where id=:id");
        query.setParameter("id", id);
        return (Integer) query.executeUpdate();
    }

    @Override
    public Integer deleteCube(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE from Cube where id=:id");
        query.setParameter("id", id);
        return (Integer) query.executeUpdate();
    }

    @Override
    public List<CubeContentMapping> fetchAllCubeContentMappingsForCube(Cube cube) {
        Query query = sessionFactory.getCurrentSession().createQuery("from CubeContentMapping where cube=:cube");
        query.setParameter("cube", cube);
        return (List<CubeContentMapping>) query.list();
    }

    @Override
    public List<UserSharedCube> fetchAllUserSharedCubeMappingsForCube(Cube cube) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserSharedCube where cube=:cube");
        query.setParameter("cube", cube);
        return (List<UserSharedCube>) query.list();
    }

    @Override
    public Integer deleteUserSharedCubeMapping(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE from UserSharedCube where id=:id");
        query.setParameter("id", id);
        return (Integer) query.executeUpdate();
    }

    @Override
    public UserSharedCube fetchUserSharedCube(CubeitUser sharingWithUser, Cube cube) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserSharedCube where user=:user and cube=:cube");
        query.setParameter("user", sharingWithUser);
        query.setParameter("cube", cube);
        return (UserSharedCube) query.uniqueResult();
    }
    
    @Override
    public UserSharedContent fetchUserSharedContent(CubeitUser sharingWithUser, Content content) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserSharedContent where user=:user and content=:content");
        query.setParameter("user", sharingWithUser);
        query.setParameter("content", content);
        return (UserSharedContent) query.uniqueResult();
    }

    @Override
    public void saveUserSharedCube(UserSharedCube userSharedCube) {
        sessionFactory.getCurrentSession().save(userSharedCube);
    }
    
    @Override
    public void saveUserSharedContent(UserSharedContent userSharedContent) {
        sessionFactory.getCurrentSession().save(userSharedContent);
    }
}
