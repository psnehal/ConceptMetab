package conceptmetab

class Concept_types {
	
	
	String name
	static hasMany  = [ concepts:Concepts ]
	
	 static searchable = true
	
    static constraints = {
		
		name blank:false	
		
    }
	
	String toString() {return name}
}
