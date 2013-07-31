package conceptmetab

import org.springframework.dao.DataIntegrityViolationException

class Compound_sourcesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [compound_sourcesInstanceList: Compound_sources.list(params), compound_sourcesInstanceTotal: Compound_sources.count()]
    }

    def create() {
        [compound_sourcesInstance: new Compound_sources(params)]
    }

    def save() {
        def compound_sourcesInstance = new Compound_sources(params)
        if (!compound_sourcesInstance.save(flush: true)) {
            render(view: "create", model: [compound_sourcesInstance: compound_sourcesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), compound_sourcesInstance.id])
        redirect(action: "show", id: compound_sourcesInstance.id)
    }

    def show(Long id) {
        def compound_sourcesInstance = Compound_sources.get(id)
        if (!compound_sourcesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "list")
            return
        }

        [compound_sourcesInstance: compound_sourcesInstance]
    }

    def edit(Long id) {
        def compound_sourcesInstance = Compound_sources.get(id)
        if (!compound_sourcesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "list")
            return
        }

        [compound_sourcesInstance: compound_sourcesInstance]
    }

    def update(Long id, Long version) {
        def compound_sourcesInstance = Compound_sources.get(id)
        if (!compound_sourcesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (compound_sourcesInstance.version > version) {
                compound_sourcesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'compound_sources.label', default: 'Compound_sources')] as Object[],
                          "Another user has updated this Compound_sources while you were editing")
                render(view: "edit", model: [compound_sourcesInstance: compound_sourcesInstance])
                return
            }
        }

        compound_sourcesInstance.properties = params

        if (!compound_sourcesInstance.save(flush: true)) {
            render(view: "edit", model: [compound_sourcesInstance: compound_sourcesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), compound_sourcesInstance.id])
        redirect(action: "show", id: compound_sourcesInstance.id)
    }

    def delete(Long id) {
        def compound_sourcesInstance = Compound_sources.get(id)
        if (!compound_sourcesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "list")
            return
        }

        try {
            compound_sourcesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compound_sources.label', default: 'Compound_sources'), id])
            redirect(action: "show", id: id)
        }
    }
}
