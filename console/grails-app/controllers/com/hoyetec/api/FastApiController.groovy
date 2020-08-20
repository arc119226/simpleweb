package com.hoyetec.api

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.security.access.annotation.Secured
@Secured(['ROLE_ADMIN','ROLE_DEV'])
@Transactional(readOnly = false)
class FastApiController {

    def index(Integer max) {
        respond FastApi.list(params), model:[fastApiCount: FastApi.count()]
    }

    def show(FastApi fastApi) {
        this.flash.message=flash.message
        this.flash.error=flash.error
        respond fastApi
    }

    def create() {
        respond new FastApi(params)
    }

    @Transactional
    def save(FastApi fastApi) {
        if (fastApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fastApi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fastApi.errors, view:'create'
            return
        }

        fastApi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fastApi.label', default: 'FastApi'), fastApi.id])
                redirect fastApi
            }
            '*' { respond fastApi, [status: CREATED] }
        }
    }

    def edit(FastApi fastApi) {
        respond fastApi
    }

    @Transactional
    def update(FastApi fastApi) {
        if (fastApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (fastApi.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond fastApi.errors, view:'edit'
            return
        }

        fastApi.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'fastApi.label', default: 'FastApi'), fastApi.id])
                redirect fastApi
            }
            '*'{ respond fastApi, [status: OK] }
        }
    }

    @Transactional
    def delete(FastApi fastApi) {

        if (fastApi == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        try{
            fastApi.delete flush:true
        }catch (allError){
            flash.error =  "Impossível excluir. Verifique os relacionamentos."
            respond fastApi
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'fastApi.label', default: 'FastApi'), fastApi.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fastApi.label', default: 'FastApi'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}