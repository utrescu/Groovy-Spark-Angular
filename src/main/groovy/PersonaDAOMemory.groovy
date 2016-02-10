class PersonaDAOMemory implements PersonaDAO {
    static def persones = [ new Persona(id:1, nom:'Xavier', cognom:'Sala')]

    def find(int id) {
        def x = persones.find { it.id == id }

        println "id:" + id + ":" + x
        return x
    }

    def insertPersona(Persona x) {
        x.id = persones.max{ it.id }.id + 1
        persones << x
        x
    }

    def findAll() {
        return persones
    }
}

