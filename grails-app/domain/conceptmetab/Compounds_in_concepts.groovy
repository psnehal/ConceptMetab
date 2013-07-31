package conceptmetab

class Compounds_in_concepts {
	
	
	
	
	
	
	static belongsTo = [concepts:Concepts, compounds:Compounds]
    static constraints = {
    }
	static mapping = {
		
		table 'compounds_in_concepts'
		
		
	}
}
