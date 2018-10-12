package web.service;

import java.util.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import web.History;
import web.Operations;
import web.Operators;

/**
 *
 * @author Ricardo Paixão
 */
@Stateless
@Path("web.history")
public class HistoryFacadeREST extends AbstractFacade<Operations> {

    Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "CyxteraCalculatorPU")
    private EntityManager em;

    public HistoryFacadeREST() {
        super(Operations.class);
    }

    @GET
    @Path("{sessionId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<History> findAll(@PathParam("sessionId") Integer sessionId) {
        logger.info("Requesting history audit");
        
        TypedQuery<History> query = em.createNamedQuery("History.findAllBySessionId", History.class);
        query.setParameter("sessionId", sessionId);

        List<History> historyList = query.getResultList();
        
        logger.info("History audit with "+historyList.size()+" entries retrieved");
        return historyList;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
