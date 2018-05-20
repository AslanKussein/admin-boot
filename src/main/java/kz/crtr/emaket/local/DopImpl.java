package kz.crtr.emaket.local;

import kz.crtr.emaket.gson.GsonResult;

/**
 * @author a.kussein
 */
public interface DopImpl {
    GsonResult isTestDb();
    GsonResult setResetPass(String uName, String rn, String mail);
}
