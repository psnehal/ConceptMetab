package conceptmetab
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.*;
import org.rosuda.Rserve.*;



class ConnectRService {
boolean transactional = false


RConnection c = new RConnection();
	def assigVar(String a)
	{	
		println(a)
		
		c.assign("A", a)
		def e = c.eval("A")
		println(e)
		
			
	}

		def doCalculations(int a, int b )
		{
			REXP x = c.eval("R.version.string");
			println(x.asString())
			println("a is"+ a + "b is "+ b)
			c.assign("a",a.abs())
			c.assign("b", b.abs())
				c.eval("A<-c(3,5,3,6)");
				c.eval("B<-c(3,6,4,5)");
				c.eval("C<-A+B");
				//get the result from R
				double[] result = (double[])c.eval("summary(A)").asDoubles();
				
				//easy print function to console
				
				for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
				}
			
			
			
		}
		def createHeatmap(String idn)
		{
		   def con = idn.toLong();	   
		   def enrichL =Enrichments.createCriteria()
		   def result= enrichL.list {
				 or {
					eq('id1.id',con)
					eq('id2.id',con)
								}
				le("qval", 0.01)
			   maxResults(100)
			   order("qval")
			}
			def id1= result.collect{ ids -> return [id:ids.id1.id]}
			def id2= result.collect{ ids -> return [id:ids.id2.id]}
			def allid = (id1+id2)
			
			def allids = allid.unique()
			println(allids.unique().sort())
			def allidcheck2 = allids.unique().sort()
			println(result)
			
			def map = [:]
			 List<Compounds_in_concepts> b = new ArrayList<Compounds_in_concepts>()
			 b = Compounds_in_concepts.executeQuery("SELECT cc from conceptmetab.Compounds_in_concepts cc where cc.concepts.id="+con)
		   println("its b"+ b.compounds.id )
		   
			   String name = con+"_qval100.csv"
			   String name2 = "/home/snehal/ConceptMetabGG/conceptmetabVersion/v4/conceptmetab/web-app/pdf/"+name
				 def out = new File(name2)
				 println("name of the new csv file"+name2)
				 
				 b.each{
					 println("id from loop" +it.compounds.id)
				 }
				allidcheck2.each {
					def cons = Concepts.get(it.id)
					def cname = cons.name.replaceAll(/,/, '')
					cname = cname.replaceAll(/,/, '')
					def comp = cons.compounds_in_concepts.compounds.id.toList().sort().toString()
					def ja = comp.substring(1, comp.length()-1)
					//println(cname + ","+ ja)
					out.append(cname + ","+ja)
					out.append '\n'
			   
				 }
				def countmap = [:]
				//For each compound
		   b.each {
			   def compid = it
			   for( i in map)
				   {
					   //println("key is"+ i.key)
					   //println( "value is"+ i.value)
					   def ts = i.value
					   //println(ts.findAll {b.equals(360)})
					   ts.collect {
						   if(it == compid )
						   {
							   println(" id from concept is"+ it + "original"+ b.id)
						   }
					   }
				   }//for map
				} //for b
		   def values = b.compounds.id ;
		   println("vomp for concepts in query are :" + values);
		   
		  calPar(values,name,con);
		  String namePdf = con+"_100.pdf"
		  return namePdf;
				
		}
		
		def calPar(ArrayList values,String name,long con)
		{
			
				RConnection connection;
		connection = new RConnection("localhost",
               6311);
		//connection = new RConnection("127.0.0.1", 6311);
		//LRPathRServer rserver = new LRPathRServer(RServerConfiguration.rserverAddress(),
           //     RServerConfiguration.rserverPort());
		//connection.voidEval(command);
		   
		   
		println("Comp id is"+ values)
		//
		int [] values2 = values;
		connection.assign("a", values2);
		
		
		String filepath = "/home/snehal/ConceptMetabGG/conceptmetabVersion/v4/conceptmetab/web-app/pdf/"+name;		
		println("filepath is"+ filepath)
		connection.assign("filepath", filepath);
		String command  = "no_col <- max(count.fields(filepath,sep = \" "+","+ "\"))";
		System.out.println(command);
		connection.voidEval(command);
		int colNo = connection.eval("no_col").asInteger();
		System.out.println(colNo);
		String c2 = "data = read.table(filepath,sep=\"" +","+"\",fill=TRUE,col.names=1:no_col)";
		connection.voidEval(c2);
		connection.voidEval("enriched.concepts <- as.list(as.data.frame(t(data)))");
		connection.voidEval("membership = t(sapply(enriched.concepts, function(enriched.concepts){ as.numeric(a %in% enriched.concepts) }))");
		connection.voidEval("colnames(membership) = a");
		connection.voidEval("rownames(membership) = data[,1]");
		connection.voidEval("m_matrix <- data.matrix(membership)");
		String pdfName = "pdf(file='/home/snehal/ConceptMetabGG/conceptmetabVersion/v4/conceptmetab/web-app/pdf/"+con+"_100.pdf')"
		println(pdfName)
		connection.voidEval(pdfName);
		connection.voidEval("nba_heatmap <- heatmap(m_matrix, Rowv=NA, Colv=NA, col = cm.colors(256), scale='column')");
		connection.voidEval("dev.off()");
		}
		
		
}
