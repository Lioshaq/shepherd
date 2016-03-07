package md.mi.transaction.handler.impl;

import md.mi.domain.entity.Account;
import md.mi.model.json.request.TransactionRequest;
import md.mi.model.security.AuthUser;
import md.mi.transaction.handler.TransactionHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implements the email transactionHandler
 * Case when making a transaction using email_to
 * Created by ael on 3/7/16.
 */
public class EmailTransactionHandler extends TransactionHandler {
    @Override
    protected boolean handlerRequestImpl(TransactionRequest transactionRequest) {
        if(transactionRequest.getType().equalsIgnoreCase("email")){
            //TODO handle email transaction
            AuthUser user = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(transactionRequest.getFrom() != user.getUsername()){
                //TODO throw some serious error
            }
            Account fromAccount = dbService.findAccountByUserid(user.getId());
            //TODO find user by email
            AuthUser toUserDetails = (AuthUser)dbService.loadUserByUsername(transactionRequest.getTo());
            Account toAccount = dbService.findAccountByUserid(toUserDetails.getId());
            fromAccount.addToBalance(transactionRequest.getAmount() * -1);
            toAccount.addToBalance(transactionRequest.getAmount());

            dbService.makeTransfer(fromAccount, toAccount);
            return true;
        }
        else {
            return false;
        }
    }
}
