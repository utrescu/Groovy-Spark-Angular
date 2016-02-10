import groovy.json.JsonSlurper

import static spark.Spark.*

import groovy.json.JsonBuilder

staticFileLocation("/public");

/**
 * Servirà per convertir qualsevol objecte a JSON
 * de forma automàtica
 */
Object.metaClass.asJson = {
    def builder = new JsonBuilder(delegate);
    builder.toString()
}

servei = new PersonaService(new PersonaDAOHSQL())

/**
 * Obtenir totes les persones de la llista
 *
 */
get '/persona', { req, res ->
    res.type("application/json")
    return servei.getPersones().asJson()
}

/**
 * Obtenir una persona a partir d'un determinat id
 *
 */
get '/persona/:id', { req, res ->
    res.type("application/json")
    int nid = req.params(":id").toInteger()
	Persona p = servei.getPersona(nid)
    if (p != null ) {
        p.asJson()
    } else {
        '{"error":"not found" }'
    }
}

/**
 * Afegir una nova persona a la llista amb les
 * dades en la URL
 *
 */
post '/persona/:nom/:cognom', { req, res ->
    res.type("application/json")
    servei.addPersona(req.params(":nom"), req.params(":cognom")).asJson()
}

/**
 * Afegir una nova persona a la llista
 *
 * Per provar-ho amb HTTPie:
 *    http http://localhost:4567/persona nom='Lluis' cognom='Puig' --json
 */
post '/persona', { req, res ->
    res.type("application/json")
    def slurper = new JsonSlurper()
    def result = slurper.parseText(req.body())
    servei.addPersona(result.nom, result.cognom).asJson()
}
