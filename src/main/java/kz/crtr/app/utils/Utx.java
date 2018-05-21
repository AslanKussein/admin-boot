package kz.crtr.app.utils;

import lombok.extern.java.Log;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Log
public class Utx {

    public static void rollBackOnly() throws Exception {
        try {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.info("rollback successful");
        } catch (IllegalStateException e) {
            throw new Exception(e.getMessage());
        }
    }
}