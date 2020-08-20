package com.hoyetec.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hoyetec.api.po.AppUserLog;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: AppUserLogRepository.java
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
public interface AppUserLogRepository extends JpaRepository<AppUserLog, Integer> {
	public List<AppUserLog> findByUuid(String uuid);

	public AppUserLog findByUid(Integer uid);
	
	public AppUserLog findLastByUname(String uname);

	public AppUserLog findByUnameAndEmail(String uname, String email);

	public AppUserLog findByUnameAndPhone(String uname, String phone);  
}
