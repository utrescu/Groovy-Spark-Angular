import groovy.sql.Sql

class PersonaDAOHSQL implements PersonaDAO {

    def sql

    PersonaDAOHSQL() {
	sql = Sql.newInstance("jdbc:hsqldb:mem:testdb", "sa", "", "org.hsqldb.jdbcDriver")

	sql.execute("""
	    create table persona ( 
                id INTEGER IDENTITY PRIMARY KEY,
	        nom VARCHAR(50),
	        cognom VARCHAR(100)
	    )""")

	sql.execute "INSERT INTO persona (nom, cognom) VALUES ('Xavier', 'Sala')"

    }

    def find(int id) {
	Persona p;
	sql.rows("select * from persona where id=" + id).each { row ->
    	    p = new Persona(id:row.id, nom:row.nom, cognom:row.cognom) 
	}
	return p
    }

    def insertPersona(Persona x) {
	def key = sql.executeInsert "insert into persona(nom, cognom) values(${x.nom}, ${x.cognom})"
	x.id = key.flatten()[0]
	x
    }

    def findAll() {
	def persones = []
        sql.rows("select * from persona").each { row ->
    	    persones << new Persona(id:row.id, nom:row.nom, cognom:row.cognom)
	}
	persones
    }
}

