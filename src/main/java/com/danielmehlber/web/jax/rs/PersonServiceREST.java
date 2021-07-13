package com.danielmehlber.web.jax.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/people")
public class PersonServiceREST {

	@GET
	@Path("/addPerson")
	// Very important: No STATIC!!! STATIC kills JAX-RS
	public String addPerson(
			final @QueryParam("name") String name,
			final @QueryParam("age") Integer age,
			final @QueryParam("phrase") String phrase)
	{
		return String.format("Adding person %s, %d: '%s'", name, age, phrase);
	}
}
