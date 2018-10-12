package web.service;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import web.History;
import web.Operators;

/**
 *
 * @author Ricardo Paixão
 */
@Stateless
@Path("web.operators")
public class OperatorsFacadeREST extends AbstractFacade<Operators> {

    Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "CyxteraCalculatorPU")
    private EntityManager em;

    public OperatorsFacadeREST() {
        super(Operators.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Operators entity) {
        super.create(entity);
        logger.info("new operator creaded on session=" + entity.getSessionId() + " value=" + entity.getValue());
        String historyEntry="Transaction=createOperator Value="+entity.getValue()+" SessionID="+ entity.getSessionId();
        em.persist(new History(historyEntry, entity.getSessionId()));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
