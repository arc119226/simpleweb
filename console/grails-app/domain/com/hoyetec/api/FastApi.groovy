package com.hoyetec.api

class FastApi {

	String apiName
	String apiVersion
	String params
	String conditonQuery
	String description

    static constraints = {
    	apiName nullable : false, maxSize:128
    	apiVersion nullable : false
    	conditonQuery nullable : true
    	params nullable : true
    	description nullable : true
    }
}
