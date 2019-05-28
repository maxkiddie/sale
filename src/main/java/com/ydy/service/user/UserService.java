/**
 * 
 */
package com.ydy.service.user;

import com.ydy.model.User;
import com.ydy.vo.other.PageVo;
import com.ydy.vo.token.UserTokenVo;

/**
 * @author xuzhaojie
 *
 *         2019年5月27日 下午7:11:11
 */
public interface UserService {

	PageVo<User> selectData(User user, Integer page, Integer size);

	User register(User user);

	UserTokenVo checkUser(User user);
}
