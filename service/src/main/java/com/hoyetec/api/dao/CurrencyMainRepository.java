package com.hoyetec.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.hoyetec.api.po.CurrencyMain;
/**
 * 
*****************************************************************************
*
*    Page/Class Name: CurrencyMainRepository.java
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
public interface CurrencyMainRepository extends JpaRepository<CurrencyMain, Long>{
	
	    List<CurrencyMain> findAll();
	 
		@Query(nativeQuery=true,
				value="select *  from currency_main  where status=1")
		List<CurrencyMain> findCurrencyList();
	 
		@Query(nativeQuery=true,
				value="select Currency_name from currency_main  where status=1 and currency_id=?1 limit 1")		
		String findByCurrency_name(long currencyid);
		
		@Query(nativeQuery=true,
				value="select * from currency_main  where status=1 and currency_id=?1 limit 1")		
		CurrencyMain findByCurrencyName(long currencyid);
}
