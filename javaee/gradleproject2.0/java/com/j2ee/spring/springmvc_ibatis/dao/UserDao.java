package com.j2ee.spring.springmvc_ibatis.dao;

import com.j2ee.spring.springmvc_ibatis.model.User;
import com.j2ee.spring.springmvc_ibatis.util.Pages;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDao
 *
 * @author archie2010
 *
 *         since 上午02:31:52
 */
@Component
public class UserDao extends BaseDao {

    /**
     * 添加
     *
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        User u = (User) getSqlMapClientTemplate().insert("insertUser", user);
        return u != null ? true : false;
    }

    /**
     * 删除
     *
     * @param uid
     * @return
     */
    public boolean deleteUser(int uid) {
        int res = getSqlMapClientTemplate().delete("deleteUserByID", uid);
        return res > 0 ? true : false;
    }

    /**
     * 查询
     *
     * @param uid
     * @return
     */
    public User getUserByUid(int uid) {
        return (User) getSqlMapClientTemplate().queryForObject("findUserByID",
                uid);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return getSqlMapClientTemplate().queryForList("findAllUser");
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        int res = getSqlMapClientTemplate().update("updateUserByUser", user);
        return res > 0 ? true : false;
    }

    /**
     * 查询(根据用户名密码)
     *
     * @param user
     * @return
     */
    public User getUserByUnameUpwd(User user) {
        return (User) getSqlMapClientTemplate().queryForObject(
                "selectByUnameAndUpwd", user);

    }

    /**
     * 记录总数
     *
     * @return
     */
    public int getAllUserCount() {
        return (Integer) getSqlMapClientTemplate().queryForObject(
                "findAllUserCount");
    }

    /**
     * 分页
     *
     * @param pageNo
     * @return
     */
    @SuppressWarnings("unchecked")
    public Pages<User> getUserByPageNo(int pageNo) {
        Pages<User> pages = new Pages<User>(getAllUserCount());
        pages.setPageSize(5);
        List<User> userList = new ArrayList<User>();
        userList = getSqlMapClientTemplate().queryForList("findPageUser",
                pages.getStart(pageNo));
        pages.setPageList(userList);
        return pages;
    }
}
