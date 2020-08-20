package com.hoyetec.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hoyetec.api.po.AppUser;

/**
 * 
*****************************************************************************
*
*    Page/Class Name: AppUserRepository.java
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
public interface AppUserRepository extends JpaRepository<AppUser, String>{
	/**
	 * 對應keycloak上的USER_ID
	 * @param uuuid
	 * @return
	 */
	public AppUser findByUuid(String uuid);

	public AppUser findByUname(String uname);

	public AppUser findByUnameAndEmail(String uname, String email);

	public AppUser findByUnameAndPhone(String uname, String phone);    
    
	public AppUser findByUid(Integer uid);
}
