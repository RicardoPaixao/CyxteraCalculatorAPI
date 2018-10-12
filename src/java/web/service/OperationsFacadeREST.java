package web.service;

import java.util.logging.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import web.History;
import web.Operations;
import web.Operators;

/**
 *
 * @author Ricardo Paixão
 */
@Stateless
@Path("web.operations")
public class OperationsFacadeREST extends AbstractFacade<Operations> {

    Logger logger = Logger.getLogger(getClass().getName());

    @PersistenceContext(unitName = "CyxteraCalculatorPU")
    private EntityManager em;

    public OperationsFacadeREST() {
        super(Operations.class);
    }

    @POST
    @Path("executeOperation")

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Operators executeOperation(Operations entity) {
        logger.info("Executing a operation. Retrieving last two operators for session");

        TypedQuery<Operators> query = em.createNamedQuery("Operators.findLastTwo", Operators.class);
        query.setParameter("sessionId", entity.getSessionId());

        query.setMaxResults(2);
        List<Operators> lastOperators = query.getResultList();
        logger.info("lastOperators.size=" + lastOperators.size());

        if (lastOperators.size() < 2) {
            logger.severe("Error. there arent enough operators to execute an opetarion");
            throw new IllegalArgumentException();
        }

        float result = 0f;
        //in virtue of the ORDER BY DESC, the opetarors will be in reverse order.
        float firstValue = lastOperators.get(1).getValue();
        float secondValue = lastOperators.get(0).getValue();
        logger.info("firstValue=" + firstValue + " secondValue=" + secondValue);

        switch (entity.getOperation()) {
            case "suma":
                result = firstValue + secondValue;
                break;
            case "resta":
                result = firstValue - secondValue;
                break;
            case "multiplicación":
                result = firstValue * secondValue;
                break;
            case "división":
                if (secondValue == 0) {
                    logger.severe("Cannot divide by zero");
                    throw new IllegalArgumentException();
                }
                result = firstValue / secondValue;
                break;
            case "potenciación":
                result = (float) Math.pow(firstValue, secondValue);
                break;
            default:
                logger.severe("operation name invalid:" + entity.getOperation());
                throw new IllegalArgumentException();
        }

        logger.info("Operation successfully executed. removing old operators and inserting new in the database");
        em.remove(lastOperators.get(1));
        em.remove(lastOperators.get(0));
        Operators newOp = new Operators();
        newOp.setSessionId(entity.getSessionId());
        newOp.setValue(result);
        em.persist(newOp);

        String historyEntry="Transaction=executeOperation Value="+entity.getOperation()+" SessionID="+ entity.getSessionId();
        em.persist(new History(historyEntry, entity.getSessionId()));
        logger.info("Persistance successful, returning result");
        return newOp;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
