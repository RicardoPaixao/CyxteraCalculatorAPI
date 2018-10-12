package web.service;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import web.History;
import web.Sessions;

/**
 *
 * @author Ricardo Paixão
 */
@Stateless
@Path("web.sessions")
public class SessionsFacadeREST extends AbstractFacade<Sessions> {
    Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "CyxteraCalculatorPU")
    private EntityManager em;

    public SessionsFacadeREST() {
        super(Sessions.class);
    }

    @GET
    @Path("createSession")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Sessions find() {
        Sessions session = new Sessions();
        session.setCreatedAt(new Date());
        super.create(session);
        em.flush();//or else id would be null until the end of the transaction
        
        logger.info("new session created. id="+session.getId());
        String historyEntry="Transaction=createSession Value="+session.getId()+" SessionID="+ session.getId();
        em.persist(new History(historyEntry, session.getId()));
        return session;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
