package com.danielmehlber.web.jax.rs;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


@SuppressWarnings("serial")
public class PersonServiceServlet extends HttpServlet{

	private static Logger logger;

	@PersistenceContext(unitName = "person")
	private EntityManager persistenceMgr;

	@Resource
	private UserTransaction transaction;



	@Override
	public void init() throws ServletException {
		logger = Logger.getLogger(getServletName());
		logger.info(String.format("initializing servlet %s ...", getServletName()));

		super.init();
		logger.info("servlet has been sucessfully initialized");
	}

	@Override
	public void destroy() {
		logger.info(String.format("Destroying servlet %s ...", getServletName()));
		super.destroy();
		//logger.info("servlet has been destroyed");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String nameValue = req.getParameter("name");
		String ageValue = req.getParameter("age");
		String phraseValue = req.getParameter("phrase");

		if(!(nameValue == null || ageValue == null || phraseValue == null)) {
			Person person = new Person(nameValue, Integer.parseInt(ageValue), phraseValue);

			/*
			 * Try to add person to database (persist it)
			 */
			try {
				transaction.begin();
				persistenceMgr.persist(person);
				transaction.commit();
			} catch (NotSupportedException | SystemException | IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*
			 * Fetch object by name
			 */
			Person added = persistenceMgr.find(Person.class, nameValue);
			if(added != null) {
				res.getWriter().append(String.format("<h1> Added %s (%d) to database", added.getName(), added.getAge()));
			} else {
				res.getWriter().append("Failed to add new person");
			}
		}

		List<Person> people = persistenceMgr.createQuery("SELECT c FROM Person c", Person.class).getResultList();

		res.getWriter().append("<p>"+people.size()).append(" people found");

		for (Person person : people) {
			res.getWriter().append("<h1>").append(person.getName()).append("</h1>");
		}

		res.setContentType("text/html");
	}

}
