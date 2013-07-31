package conceptmetab

class Compound_sources {

	String name
    static constraints = {
		name nullable : false
    }
	static hasMany = [ compounds:Compounds]
	static searchable = true
	
	String toString() {return name}
}
