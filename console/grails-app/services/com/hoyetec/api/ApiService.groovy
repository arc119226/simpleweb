package com.hoyetec.api

import grails.gorm.transactions.Transactional

@Transactional
class ApiService {

	def dataSource;

    def processRequest(String apiVersion,String apiName,def _params) {
    	FastApi apiInfo=FastApi.findByApiNameAndApiVersion(apiName,apiVersion)
    	if(apiInfo){
            try{
        		def p=apiInfo.params?.split(',')
        		String q=apiInfo.conditonQuery
        		if(p != null){
        			p.each{
                        it=it.trim()
                        // println 'it = '+it
                        // println 'params = '+_params
                        // println 'params.it = '+_params."${it}"
                        // println ' q = '+q
        				q=q.replaceAll('\\$\\{'+"${it}"+'\\}',_params."${it}")
                        println 'result q = '+q
        			}
        		}
        		groovy.sql.Sql sql = new groovy.sql.Sql(dataSource)
        		def resultset = sql.rows(q)
        		def responseData = [
        			'results': resultset,
        			'msg':null,
        			'status': 'success'
    			]
    			return responseData
            }catch(Exception e){
                e.printStackTrace()
                def responseData = [
                'results': null,
                'msg':'not support this api',
                'status': 'error'
                ]
            return responseData
            }
    	}else{
    		def responseData = [
    			'results': null,
    			'msg':'not support this api',
    			'status': 'error'
			]
			return responseData
    	}
    }
}