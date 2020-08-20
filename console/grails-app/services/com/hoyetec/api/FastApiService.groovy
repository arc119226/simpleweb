package com.hoyetec.api

import grails.gorm.services.Service

@Service(FastApi)
interface FastApiService {
    FastApi get(Serializable id)
    List<FastApi> list(Map args)
    Long count()
    void delete(Serializable id)
    FastApi save(FastApi fastApi)
}