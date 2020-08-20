<g:link class="btn btn-warning" action="index"><i class="fa fa-search"> <g:message code="default.button.list.label"/></i></g:link>
<g:link class="btn btn-success" action="create"><i class="fa fa-search"> <g:message code="default.new.label" args="[entityName]" /></i></g:link>
<g:if test="${isSave == true}">
    <button type="submit" class="btn btn-primary" name="_action_save" value="Save">
        <i class="fa fa-save">${message(code: 'default.button.save.label', default: 'Save')}</i>
    </button>
</g:if>
<g:if test="${isUpdate == true}">
    <button type="submit" class="btn btn-primary" name="_action_update" value="Update">
        <i class="fa fa-refresh">${message(code: 'default.button.update.label', default: 'Update')}</i>
    </button>
</g:if>
<g:if test="${isUpdate == true}">
    <button type="submit" class="btn btn-danger" name="_action_delete" value="Delete">
        <i class="fa fa-trash-o">${message(code: 'default.button.delete.label', default: 'Delete')}</i>
    </button>
</g:if>