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
		   maxResults(100)
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
		
		def filter
		def con 
		def id1_inter 
		def id2_inter
		def result
		println("From filter slider parameters a")		
		HashMap jsonMap = new HashMap()
		
		println("its going for p and q filter")
		filter = params.fil;
		con = params.q.toLong();
		id1_inter = params.id1.toBigDecimal();
		id2_inter = params.id2.toBigDecimal();
		
		
		def enrichL =Enrichments.createCriteria()
		
		result= enrichL.list {
			 or {
				 eq('id1.id',con)
				 eq('id2.id',con)
			 }
			 between(filter, id1_inter, id2_inter)
			 maxResults(100)
			 order(filter)
		 }
		
		def enrichP =Enrichments.createCriteria()
		
		
	 println("list of interactions = "+result.size() +" id1 = "+ id1_inter+"  id2 = "+ id2_inter+"  id  = "+ con +"  filter  = "+ filter)
		
	 if(result.size() == 0)
	 {
		 println("inside the zero con")
		 flash.message = "${result.size()} No results found for search: ${params.id2}"
	 }
	 
		if (params.containsKey("db"))
		{
			println("its going for database filter")
			
		}
		else
		{
			println("p and q")
			
			
		}
		
		def i1= result.collect{ ids -> return ids.id1.id.toString()}
		def i2= result.collect{ ids -> return ids.id2.id.toString()}
		def all = (i1+i2)
		def ck = all.unique() as JSON
		println("Array of concepts is"+ ck.toString())
		
		def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}
		def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}
		def allid = (id1+id2)
			def allids = allid.unique()
		
			
			println(allids.sort())
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
		
		long now = System.currentTimeMillis();
		def filter
		def con 
		def id1_inter 
		def id2_inter
		def result
		println("From filter slider parameters a")		
		HashMap jsonMap = new HashMap()
		
		println("its going for p and q filter")
		filter = params.fil;
		con = params.q.toLong();
		id1_inter = params.id1.toBigDecimal();
		id2_inter = params.id2.toBigDecimal();
		
		
		def enrichL =Enrichments.createCriteria()
		
		result= enrichL.list {
			 or {
				 eq('id1.id',con)
				 eq('id2.id',con)
			 }
			 between(filter, id1_inter, id2_inter)
			 maxResults(100)
			 order(filter)
		 }
		
		
	 println("list of interactions = "+result.size() +" id1 = "+ id1_inter+"  id2 = "+ id2_inter+"  id  = "+ con +"  filter  = "+ filter)
		
	 if(result.size() == 0)
	 {
		 println("inside the zero con")
		 flash.message = "${result.size()} No results found for search: ${params.id2}"
	 }
	 
	 
		
		
		def i1= result.collect{ ids -> return ids.id1.id.toLong()}
		def i2= result.collect{ ids -> return ids.id2.id.toLong()}
		def all = (i1+i2)
		def ck = all.unique().toList()
		
		println("Array of concepts is"+ ck.toString())
		
		def enrichP =Enrichments.createCriteria()
		
		def res = enrichP.list {
		
				'in' ('id1.id',ck)
				 'in' ('id2.id',ck)
			le('pval', 0.01) 
			
		 }
		 println("size of other query" +res.size())
		
		
		def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}
		def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}
		def allid = (id1+id2)
			def allids = allid.unique()
		
			
			println(allids.sort())
		def allR =allids.collect {ids -> return [id:ids.id,label:(Concepts.get(ids.id).getName()),comNo:(Concepts.get(ids.id).getNum_compounds()),conTypes:(Concepts.get(ids.id).concept_types.getName())]}
	
		//allids.add(con)
		jsonMap.nodes = allR
		   
		def f1 = result.collect {en ->
				return [source: en.id1.id.toString(), target: en.id2.id.toString(),id: (en.pval.toString()),db_id: en.id.toString(),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id())]
			//	println(Concepts.get(en.id1.id).getOriginal_id())
				}
		
		def f2 = res.collect {en ->
			return [source: en.id1.id.toString(), target: en.id2.id.toString(),id: (en.pval.toString()),db_id: en.id.toString(),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id())]
		//	println(Concepts.get(en.id1.id).getOriginal_id())
			}
	   def ftest2 = f2-f1
	   println(ftest2)
		def f  = f1+ftest2
		
		jsonMap.edges = f
	
	   
		def check = jsonMap as JSON
		println( (System.currentTimeMillis() - now) + " ms");
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
		
		
		def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}
		def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}
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


def createDb(){
	
		println(params)
		def con = params.id.toLong();
		def database = params.db.toString()
		//def id1_inter = 1.45e-323.toBigDecimal()
		//def id2_inter = params.id2.toBigDecimal();
		HashMap jsonMap = new HashMap()
		def enrichL =Enrichments.createCriteria()
		 def result= enrichL.list {
			  or {
				 eq('id1.id',con)
				 eq('id2.id',con)
					
			 }
			// between("pval", id1_inter, id2_inter)
			// maxResults(10)
			  order("pval")
		 }
				 
		 
		
		
			println("list of interactions"+result.size())
			def id1= result.collect{ ids -> return [id:ids.id1.id.toString()]}
			def id2= result.collect{ ids -> return [id:ids.id2.id.toString()]}
			def allid = (id1+id2)
			def allids = allid.unique()
			println(allids.sort())
			//def allR =allids.collect {ids -> return [id:ids.id,label:(Concepts.get(ids.id).getName()),comNo:(Concepts.get(ids.id).getNum_compounds()),conTypes:(Concepts.get(ids.id).concept_types.getName())]}
			//println(allids.sort())
			//def testc = allR.findAll {id -> id.conTypes.equals(database)}
			
			
			
			
			 def enrichT= result.collect {en ->
				return [source: en.id1.id.toString(), target: en.id2.id.toString(),id: (en.pval.toString()),db_id: en.id.toString(),thick: (en.intersection),label: (Concepts.get(en.id1.id).getOriginal_id()),dbid: (Concepts.get(en.id1.id).concept_types.getName()),dbid2: (Concepts.get(en.id2.id).concept_types.getName())]
				//println(Concepts.get(en.id1.id).getOriginal_id())
				}
			 
			def r1 = enrichT.findAll {it.dbid.equals(database)|| it.dbid2.equals(database)}
			def i1 = r1.collect {it.source}.unique()
			def i2 = r1.collect {it.target}.unique()
			def ifin = (i1+i2)
			
			def allR =ifin.collect {ids -> return [id:ids,label:(Concepts.get(ids).getName()),comNo:(Concepts.get(ids).getNum_compounds()),conTypes:(Concepts.get(ids).concept_types.getName())]}
			
			jsonMap.nodes = allR
			
			 jsonMap.edges = r1
			 println(jsonMap)
			
			def check = jsonMap as JSON
			[check:check]
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
	  def id2= result.collect{ ids -> return(Concepts.get(ids.id2.id).concept_types.getName())}
	  def allid = id1+id2
	
	  def map = [:]  //1
	  allid.each {  //2
		  if(map.containsKey(it)) map[it] = map[it] + 1  //3
		  else map[it] = 1;
	  }
		 
		
		
		 
		 def emptyList = []
		 map.each{ 
			//println("this is start of loop"+it.getKey())
			 if(it.getKey().equals("GOBP"))
			 {
				
				 def index =map.findIndexOf {it.key =='GOBP'}
				
				 emptyList.addAll(index, "red")
			 }
			 if(it.getKey().equals("GOCC"))
			 {
			
				 def index =map.findIndexOf {it.key=='GOCC'}
				
				 emptyList.addAll(index, "yellow")
			 }
			 if(it.getKey().equals("GOMF"))
			 {
				 
				 def index =map.findIndexOf {it.key=='GOMF'}
				
				 emptyList.addAll(index, "green")
			 }
			 if(it.getKey().equals("Enzyme"))
			 {
				
				 def index =map.findIndexOf {it.key=='Enzyme'}
				 
				 emptyList.addAll(index, "blue")
			 }
			 if(it.getKey().equals("KEGG"))
			 {
				 
				 def index =map.findIndexOf {it.key=='KEGG'}
				
				 emptyList.addAll(index, "purple")
			 }
			 if(it.getKey().equals("MeSH"))
			 {
				
				 def index =map.findIndexOf {it.key=='MeSH'}
			
				 emptyList.addAll(index, "#CCCCFF")
			 }
			 if(it.getKey().equals("Cluster"))
			 {
				 
				 def index =map.findIndexOf {it.key=='Cluster'}
			
				 emptyList.addAll(index, "black")
			 }
			 println("this is end of loop"+it.getKey())
		 }
		 println('map at first  \n' + map)
		
		 
		 
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