package conceptmetab

class Compounds {
	String display_id 
	String name 
	
	static hasMany = [compounds_in_concepts:Compounds_in_concepts]
	static belongsTo = [compSrc : Compound_sources]
	static searchable = true
    static constraints = {
		display_id nullable : false
		name nullable:false
		
    }
	String toString(){
		" (${name}) ${display_id}"
	  }
	
}
