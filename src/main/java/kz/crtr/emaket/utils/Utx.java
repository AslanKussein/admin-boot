package kz.crtr.emaket.utils;

import lombok.extern.java.Log;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.SystemException;

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