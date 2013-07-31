package conceptmetab

import grails.converters.JSON

import org.compass.core.converter.mapping.osem.ClassMappingConverter.IdsAliasesObjectKey;
import org.springframework.dao.DataIntegrityViolationException

class EnrichmentsController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}
	//*****************************************************************FindbyID**************************************************************************
	def findById() {
		def con = params.q;
		println(con.class)
		println("parameter is"+con)
			println(Enrichments.get(1).id1.id)
				//println(id1.toString())
		List<Enrichments> b = new ArrayList<Enrichments>()
		b = TestEnrich.executeQuery("SELECT e.intersection from conceptmetab.Enrichments e where e.id1.id=3460 and e.id2.id=4268")
		List<Enrichments> enrichList = Enrichments.where {(id1.id == 3460) || (id2.id == 3460)    }.toList()
		def enrichL = Enrichments.createCriteria()
		def result= enrichL.list {
			or {
				("id1.id" == con.toInteger())
				("id2.id" == con.toInteger())
		   }
		   maxResults(10)
			order("intersection")
		}
	   
 //UNION select e.id1.id from conceptmetab.Enrichments  e where e.id2 =3460")
		println(b.size())
		HashMap jsonMap = new HashMap()
		jsonMap.edges = result.collect {en ->
			return [source: en.id1.id.toString(), target: en.id2.id.toString(), id: (en.intersection.toString())]
			}
		def cid = jsonMap as JSON
		 [cid:cid]
		   
	}
//*****************************************************************CheckToolTip**************************************************************************
	def checkToolTip ()
	{
		def msg = "hello"
		[msg:msg]
	}
	
//*****************************************************************filterSlider**************************************************************************
	def filterSlider(){
		
		println("From filter slider parameters a")
		def filter = params.fil;
		def con = params.q.toLong();
		def id1_inter = params.id1.toBigDecimal();
		def id2_inter = params.id2.toBigDecimal();
		HashMap jsonMap = new HashMap()
		
		def enrichL =Enrichments.createCriteria()
		
		 def result= enrichL.list {
			 or {
				 eq('id1.id',con)
				 eq('id2.id',con)
			 }
			 between(filter, id1_inter, id2_inter)
		 maxResults(100)
			  order(filter)
		 }
		 
	 println("list of interactions = "+result.size() +" id1 = "+ id1_inter+"  id2 = "+ id2_inter+"  id  = "+ con +"  filter  = "+ filter)
		
		def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}.unique()
		def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}.unique()
		def allids = (id1+id2)
		def allR =allids.collect {ids -> return [id:ids.id,label:(Concepts.get(ids.id).getName()),comNo:(Concepts.get(ids.id).getNum_compounds()),conTypes:(Concepts.get(ids.id).concept_types.getName())]}
	
		//allids.add(con)
		jsonMap.nodes = allR
		   
		jsonMap.edges = result.collect {en ->
				return [source: en.id1.id.toString(), target: en.id2.id.toString(),id: (en.pval.toString()),db_id: en.id.toString(),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id())]
			//	println(Concepts.get(en.id1.id).getOriginal_id())
				}
	   
	
	   
		def check = jsonMap as JSON
	   [check:check]
	   
	   /*def con = params.q.toLong();
		println("parameter is"+con)
		HashMap jsonMap = new HashMap()
		def enrichL =Enrichments.createCriteria()
		println("ln checkin"+con)
		def result= enrichL.list {
			or {
				eq('id1.id',con)
				eq('id2.id',con)
			}
			maxResults(100)
			order("pval")
		} */
			//def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id1.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
			//def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id2.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
			//def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}.unique()
			//def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}.unique()
			 //def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),label:ids.id1.id.toString()]}.unique()
		   // def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),label:ids.id2.id.toString()]}.unique()
		  //  def allids = (id1+id2)
	}
	///*****************************************************************CreateJson**************************************************************************
	def createJson(){
		def con = params.q.toLong();
		println("parameter is"+con)
	   
		HashMap jsonMap = new HashMap()
		def enrichL =Enrichments.createCriteria()
	   
		println("ln checkin"+con)
		def result= enrichL.list {
			or {
				eq('id1.id',con)
				eq('id2.id',con)
			   }
			maxResults(100)
			order("pval")
		}
			println("list of interactions"+result.size())
		   
			//def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id1.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
			//def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id2.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
		   
			//def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),label:ids.id1.id.toString()]}.unique()
		   // def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),label:ids.id2.id.toString()]}.unique()
			def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}.unique()
			def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}.unique()
			def allids = (id1+id2).
			def allR =allids.collect {ids -> return [id:ids.id,label:(Concepts.get(ids.id).getName()),comNo:(Concepts.get(ids.id).getNum_compounds())]}
					
			//allids.add(con)
			jsonMap.nodes = allids
		   
			jsonMap.edges = result.collect {en ->
				return [source: en.id1.id.toString(), target: en.id2.id.toString(), db_id: en.id.toString(),id: (en.pval.toString()),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id())]
				println(Concepts.get(en.id1.id).getOriginal_id())
				}
	   
			def check = jsonMap as JSON
	   
			[check:check]
			render check
	}
	
//*****************************************************************DislayMsg**************************************************************************
	
	def displayMsg()
	{
		def msg  = params.q;
		println(msg)
		ArrayList arM;
	   //[msg:msg]
		redirect(controller : "Concepts", action: "show", id: msg)
		
	}
//*****************************************************************DisplayEdge**************************************************************************
	
	def displayEdge()
	{
		def msg  = params.q;
		println(msg)
		redirect( action: "show", id: msg)
		
	}
	
//*****************************************************************DispalyJson**************************************************************************
	
def displayJson(){
	
	println(params)
	def con = params.id.toLong();
	def id1_inter = 1.45e-323.toBigDecimal()
	def id2_inter = params.id2.toBigDecimal();
	HashMap jsonMap = new HashMap()
	def enrichL =Enrichments.createCriteria()
	 def result= enrichL.list {
		  or {
			 eq('id1.id',con)
			 eq('id2.id',con)
				
		 }
		 between("pval", id1_inter, id2_inter)
		  maxResults(100)
		  order("pval")
	 }
			 
	/*def con = params.q.toLong();
	println("parameter is"+con)
	HashMap jsonMap = new HashMap()
	def enrichL =Enrichments.createCriteria()
	println("ln checkin"+con)
	def result= enrichL.list {
		or {
			eq('id1.id',con)
			eq('id2.id',con)
		}
		maxResults(100)
		order("pval")
	} */
		println("list of interactions"+result.size())
	   
		//def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id1.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
		//def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),conTypes:(Concepts.get(ids.id1.id).concept_types.getName()),label:(Concepts.get(ids.id2.id).getName()),comNo:(Concepts.get(ids.id1.id).getNum_compounds())]}.unique()
		
		//def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}.unique()
		//def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}.unique()
	   
		//def id1= result.collect{ ids -> return [id:ids.id1.id.toString(),label:ids.id1.id.toString()]}.unique()
	   // def id2= result.collect{ ids -> return [id:ids.id2.id.toString(),label:ids.id2.id.toString()]}.unique()
	  //  def allids = (id1+id2)
		
		
		def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}.unique()
		def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}.unique()
		def allids = (id1+id2)
		
		def allR =allids.collect {ids -> return [id:ids.id,label:(Concepts.get(ids.id).getName()),comNo:(Concepts.get(ids.id).getNum_compounds()),conTypes:(Concepts.get(ids.id).concept_types.getName())]}
		println(allids.sort())
		//allids.add(con)
		jsonMap.nodes = allR
		
		jsonMap.edges = result.collect {en ->
			return [source: en.id1.id.toString(), target: en.id2.id.toString(),id: (en.pval.toString()),db_id: en.id.toString(),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id())]
			println(Concepts.get(en.id1.id).getOriginal_id())
			}
		
		def check = jsonMap as JSON
	   [check:check]
	//render ("hello")
}

//***********************************************************LIST,CREATE,SAVE,SHOW,EDIT,UPDATE,DELETE**************************************************************************
 def createChart() {
	 
	 println("From create Chart:***********************************************************")
	 long con = params.q.toLong();
	
	 HashMap jsonMap = new HashMap()
	 def enrichL =Enrichments.createCriteria()
	  def result= enrichL.list {
		   or {
			  eq('id1.id',con)
			  eq('id2.id',con)
				 
		  }
		  
	  }
	  
	  println("list of interactions == "+result.size()+ "for id is = " + con)
		 
		 def id1= result.collect{ ids -> return(Concepts.get(ids.id1.id).concept_types.getName())}
		
		 def map = [:]  //1
		 id1.each {  //2
			 if(map.containsKey(it)) map[it] = map[it] + 1  //3
			 else map[it] = 1;
		 }
		 def emptyList = []
		 map.each{ 
			println("this is start of loop"+it.getKey())
			 if(it.getKey().equals("GOBP"))
			 {
				 println("i found GOPBP")
				 def index =map.findIndexOf {it.key =='GOBP'}
				 println(index)
				 emptyList.addAll(index, "red")
			 }
			 if(it.getKey().equals("GOCC"))
			 {
				 println("i found GOCC")
				 def index =map.findIndexOf {it.key=='GOCC'}
				 println(index)
				 emptyList.addAll(index, "yellow")
			 }
			 if(it.getKey().equals("GOMF"))
			 {
				 println("i found GOMF")
				 def index =map.findIndexOf {it.key=='GOMF'}
				 println(index)
				 emptyList.addAll(index, "green")
			 }
			 if(it.getKey().equals("Enzyme"))
			 {
				 println("i found Enzyme")
				 def index =map.findIndexOf {it.key=='Enzyme'}
				 println(index)
				 emptyList.addAll(index, "blue")
			 }
			 if(it.getKey().equals("KEGG"))
			 {
				 println("i found KEGG")
				 def index =map.findIndexOf {it.key=='KEGG'}
				 println(index)
				 emptyList.addAll(index, "purple")
			 }
			 if(it.getKey().equals("Mesh"))
			 {
				 println("i found Mesh")
				 def index =map.findIndexOf {it.key=='Mesh'}
				 println(index)
				 emptyList.addAll(index, "#CCCCFF")
			 }
			 if(it.getKey().equals("Cluster"))
			 {
				 println("i found Mesh")
				 def index =map.findIndexOf {it.key=='Cluster'}
				 println(index)
				 emptyList.addAll(index, "black")
			 }
			 println("this is end of loop"+it.getKey())
		 }
		 println('map at first  \n' + map)
		 println('arralist is+'+ emptyList)
		 
		 
		 def columns = []
		 columns << ["id": 'Col1',"label": 'database', "type": 'string']
		 columns << ["id": 'Col2',"label": 'no', "type": 'number']
		// println(columns)
		
		 def rows = []
		 def cells
		 map.each {
		 cells = []
		 cells << [v: it.getKey()] << [v: it.getValue()]
		 rows << ['c': cells]
		 }
		
		 def table = [cols: columns, rows: rows]
		 jsonMap.table = table
		// println map
		 
		 println("jsonMap is+" +  table)
		
		 //def allR =map.collect {ids -> return [ids.getKey().toString(),ids.getValue()]}
		 
		
		
		//render map
		def dbc = table as JSON
		 //render allR
	def clst = emptyList as grails.converters.JSON
		[dbc:dbc,clst:clst]
		// render "google.visualization.Query.setResponse(" + (table as JSON) + ")"
		 //render allR
	 
 }

//***********************************************************LIST,CREATE,SAVE,SHOW,EDIT,UPDATE,DELETE**************************************************************************
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[enrichmentsInstanceList: Enrichments.list(params), enrichmentsInstanceTotal: Enrichments.count()]
	}

	def create() {
		[enrichmentsInstance: new Enrichments(params)]
	}

	def save() {
		def enrichmentsInstance = new Enrichments(params)
		if (!enrichmentsInstance.save(flush: true)) {
			render(view: "create", model: [enrichmentsInstance: enrichmentsInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), enrichmentsInstance.id])
		redirect(action: "show", id: enrichmentsInstance.id)
	}

	def show(Long id) {
		def enrichmentsInstance = Enrichments.get(id)
		if (!enrichmentsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "list")
			return
		}

		[enrichmentsInstance: enrichmentsInstance]
	}

	def edit(Long id) {
		def enrichmentsInstance = Enrichments.get(id)
		if (!enrichmentsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "list")
			return
		}

		[enrichmentsInstance: enrichmentsInstance]
	}

	def update(Long id, Long version) {
		def enrichmentsInstance = Enrichments.get(id)
		if (!enrichmentsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (enrichmentsInstance.version > version) {
				enrichmentsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'enrichments.label', default: 'Enrichments')] as Object[],
						  "Another user has updated this Enrichments while you were editing")
				render(view: "edit", model: [enrichmentsInstance: enrichmentsInstance])
				return
			}
		}

		enrichmentsInstance.properties = params

		if (!enrichmentsInstance.save(flush: true)) {
			render(view: "edit", model: [enrichmentsInstance: enrichmentsInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), enrichmentsInstance.id])
		redirect(action: "show", id: enrichmentsInstance.id)
	}

	def delete(Long id) {
		def enrichmentsInstance = Enrichments.get(id)
		if (!enrichmentsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "list")
			return
		}

		try {
			enrichmentsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'enrichments.label', default: 'Enrichments'), id])
			redirect(action: "show", id: id)
		}
	}
}