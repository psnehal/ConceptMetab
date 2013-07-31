package conceptmetab

import org.compass.needle.gigaspaces.service.SearchResults;
import org.springframework.dao.DataIntegrityViolationException

class Compounds_in_conceptsController {
	
	def beforeInterceptor =
	[action:this.&auth, except:["index", "list", "show", "atom", "search","create","findComp"]]


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def findComp(){
		
	println(Compounds_in_concepts.name)
	def con = Concepts.get(params.q);
	println(con.toString())
	def serachResults = con.compounds_in_concepts.each {println it.compounds.name}
	//def searchResults = Compounds_in_concepts.executeQuery("select c.compounds_id from Compounds_in_concepts.name c  where concepts_id =1")
	
	//println(SearchResults)
		
	return [searchResults:serachResults]
	}


    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [compounds_in_conceptsInstanceList: Compounds_in_concepts.list(params), compounds_in_conceptsInstanceTotal: Compounds_in_concepts.count()]
    }

    def create() {
        [compounds_in_conceptsInstance: new Compounds_in_concepts(params)]
    }

    def save() {
        def compounds_in_conceptsInstance = new Compounds_in_concepts(params)
        if (!compounds_in_conceptsInstance.save(flush: true)) {
            render(view: "create", model: [compounds_in_conceptsInstance: compounds_in_conceptsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), compounds_in_conceptsInstance.id])
        redirect(action: "show", id: compounds_in_conceptsInstance.id)
    }

    def show(Long id) {
        def compounds_in_conceptsInstance = Compounds_in_concepts.get(id)
        if (!compounds_in_conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "list")
            return
        }

        [compounds_in_conceptsInstance: compounds_in_conceptsInstance]
    }

    def edit(Long id) {
        def compounds_in_conceptsInstance = Compounds_in_concepts.get(id)
        if (!compounds_in_conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "list")
            return
        }

        [compounds_in_conceptsInstance: compounds_in_conceptsInstance]
    }

    def update(Long id, Long version) {
        def compounds_in_conceptsInstance = Compounds_in_concepts.get(id)
        if (!compounds_in_conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (compounds_in_conceptsInstance.version > version) {
                compounds_in_conceptsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts')] as Object[],
                          "Another user has updated this Compounds_in_concepts while you were editing")
                render(view: "edit", model: [compounds_in_conceptsInstance: compounds_in_conceptsInstance])
                return
            }
        }

        compounds_in_conceptsInstance.properties = params

        if (!compounds_in_conceptsInstance.save(flush: true)) {
            render(view: "edit", model: [compounds_in_conceptsInstance: compounds_in_conceptsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), compounds_in_conceptsInstance.id])
        redirect(action: "show", id: compounds_in_conceptsInstance.id)
    }

    def delete(Long id) {
        def compounds_in_conceptsInstance = Compounds_in_concepts.get(id)
        if (!compounds_in_conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "list")
            return
        }

        try {
            compounds_in_conceptsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'compounds_in_concepts.label', default: 'Compounds_in_concepts'), id])
            redirect(action: "show", id: id)
        }
    }
}
