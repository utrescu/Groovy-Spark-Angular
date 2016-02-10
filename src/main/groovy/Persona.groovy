/**
 * Created by xavier on 05/02/16.
 */
class Persona {
    int id
    String nom
    String cognom = ""
}


class PersonaService {
    PersonaDAO dao

    def PersonaService(PersonaDAO dao) {
        this.dao = dao
    }


    def getPersona(id) {

        return dao.find(id)
    }

    def getPersones() {
        return dao.findAll()
    }

    def addPersona(nom, cognom) {
        dao.insertPersona(new Persona(nom:nom, cognom:cognom))
    }
}



