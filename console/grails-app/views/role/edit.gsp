<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="adminlte2" />
    <g:set var="entityName" value="${message(code: 'role.label', default: 'Role')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<g:form resource="${this.role}">
    <div class="row">
        <section class="content-header">
            <div class="pull-right">
                <ul>
                    <g:render template="/layouts/actionButtons" model="[isUpdate : isSave?false:true]"/>
                </ul>
            </div>
        </section>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">${message(code: 'role.label', default: 'Role')}</h3>
                </div>
                <div class="box-body">
                    <g:if test="${flash.message}">
                        <div class="alert alert-success" role="status">${flash.message}</div>
                    </g:if>
                    <g:if test="${flash.error}">
                        <div class="alert alert-danger" role="status">${flash.error}</div>
                    </g:if>
                    <g:hasErrors bean="${this.role}">
                        <ul class="errors alert alert-danger" role="alert">
                            <g:eachError bean="${this.role}" var="error">
                                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                            </g:eachError>
                        </ul>
                    </g:hasErrors>
                    <div class="form-group">
                        <f:all bean="role"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:form>
</body>
</html>