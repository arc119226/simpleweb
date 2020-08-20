package com.hoyetec.api

import grails.converters.JSON
import org.springframework.security.access.annotation.Secured
@Secured(['ROLE_ADMIN','ROLE_DEV','ROLE_USER'])
class ApiController {

    def index() { }

    def apiService

    def index = { 

    }

	@Secured(['permitAll'])
    def fastapi(String apiVersion,String apiName) { 
    	def responseData = apiService.processRequest(apiVersion,apiName,params)
    	render responseData as JSON
    }
}