package com.hoyetec.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hoyetec.api.po.UserWallet;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: UserWalletRepository.java
*              Title:
*        Description:
*          Copyright: Hoyetec
*            Company: Hoyetec
*        Create Date: 2019年8月23日
*      Last Modifier: Arc Liu
*   Last Modify Date: 2019年8月23日
*             Author: Arc Liu  
*
*****************************************************************************
 */
@RepositoryRestResource
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> {

	@Query(nativeQuery = true, value = "select * from user_wallet w inner join currency_main c on w.currency_id=c.currency_id where w.user_uid=?1 and w.currency_id=?2 limit 1 ")
	public UserWallet findByUser(String user_uid, long currency_id);

}