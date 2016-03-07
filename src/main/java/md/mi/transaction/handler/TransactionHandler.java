package md.mi.transaction.handler;

import md.mi.model.json.request.TransactionRequest;
import md.mi.service.impl.DbService;
import md.mi.transaction.exceptions.TransactionTypeException;

/**
 * Created by ael on 3/7/16.
 *  TransactionHandler is an abstract class it implements Chain of Responsability Pattern
 *  It handles all types of TransactionRequest.
 *  User to User or admin to user
 *  @author ael
 */
public abstract class TransactionHandler {

    private TransactionHandler _succesor;

    //@Autowired
    protected DbService dbService;

    public void setSuccesor(TransactionHandler succesor) {
        this._succesor = succesor;
    }

    /**
     *  Implements transaction type logic
     *
     *  @return If this node handles implementation
     * */
    protected abstract boolean handlerRequestImpl(TransactionRequest transactionRequest);

    /**
     * Checks if current node handles the request
     * */
    public final void handleRequest(TransactionRequest transactionRequest) throws Exception {
        boolean handledByThisNode = this.handlerRequestImpl(transactionRequest);
        if(_succesor != null && !handledByThisNode) {
            _succesor.handleRequest(transactionRequest);
        }

        if(_succesor == null) {
            throw new TransactionTypeException("No such type of transaction handler " + transactionRequest.getType());
        }
    }
}
