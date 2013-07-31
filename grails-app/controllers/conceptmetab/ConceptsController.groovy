package conceptmetab

import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException

class ConceptsController {
	
	
	def beforeInterceptor =
	[action:this.&auth, except:["index", "list", "show", "create","atom","getName", "search","opt","ajaxFindCity"]]

	 def search = {
		  //render Entry.search(params.q, params)
		// render Concepts.search(params.q, params)
		 println(params)
		 def searchResults = Concepts.search(params.q, params)
		 flash.message = "${searchResults.total} results found for search: ${params.q}"
		 flash.q = params.q
		 println(searchResults.total)
		 return [searchResults:searchResults.results, resultCount:searchResults.total]
	
		}

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def getName = {
		def searchResults = Concept_types.search(params.q, params)
		println(Concept_types.get(1))
		println(params)
		
		
		
		def cI = Concept_types.get(1)
		
		
		//println(Concept_types.get(params))
		//return [cName :Concept_types.get(1) ]
		render(view: "display",cName :Concept_types.get(1))
		
	}
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 30, 100)
		println(Concept_types.count())
		println(Concept_types.get(1))
		println(params.id)
		
        [conceptsInstanceList: Concepts.list(params), conceptsInstanceTotal: Concepts.count()]
    }

	def opt()
	{
		//List<Concepts> b = new ArrayList<Concepts>()
		//b = Concepts.executeQuery("SELECT c.name from conceptmetab.Concepts c").toArray()
		//[ b:b]
		
		def ajaxConFinder = {
			def conFound = Concepts.withCriteria {
				  ilike 'con', params.term + '%'
				 }
		  render (conFound as JSON)
		 }
		
	}
	
	def ajaxFindCity = {
		
					   log.debug "Find city:${params.term}"
	
					  def foundCities = Concepts.withCriteria {
									   ilike 'name', params.term + '%'
					  }
	
			 render (foundCities?.'name' as JSON)
	
			}
	
    def create() {
        [conceptsInstance: new Concepts(params)]
    }

    def save() {
        def conceptsInstance = new Concepts(params)
        if (!conceptsInstance.save(flush: true)) {
            render(view: "create", model: [conceptsInstance: conceptsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'concepts.label', default: 'Concepts'), conceptsInstance.id])
        redirect(action: "show", id: conceptsInstance.id)
    }

    def show(Long id) {
        def conceptsInstance = Concepts.get(id)
        if (!conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "list")
            return
        }

        [conceptsInstance: conceptsInstance]
    }

    def edit(Long id) {
        def conceptsInstance = Concepts.get(id)
        if (!conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "list")
            return
        }

        [conceptsInstance: conceptsInstance]
    }
	

    def update(Long id, Long version) {
        def conceptsInstance = Concepts.get(id)
        if (!conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (conceptsInstance.version > version) {
                conceptsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'concepts.label', default: 'Concepts')] as Object[],
                          "Another user has updated this Concepts while you were editing")
                render(view: "edit", model: [conceptsInstance: conceptsInstance])
                return
            }
        }

        conceptsInstance.properties = params

        if (!conceptsInstance.save(flush: true)) {
            render(view: "edit", model: [conceptsInstance: conceptsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'concepts.label', default: 'Concepts'), conceptsInstance.id])
        redirect(action: "show", id: conceptsInstance.id)
    }

    def delete(Long id) {
        def conceptsInstance = Concepts.get(id)
        if (!conceptsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "list")
            return
        }

        try {
            conceptsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'concepts.label', default: 'Concepts'), id])
            redirect(action: "show", id: id)
        }
    }
}
