package kz.crtr.app.local;

import kz.crtr.app.gson.GsonResult;

/**
 * @author a.kussein
 */
public interface DopImpl {
    GsonResult isTestDb();
    GsonResult setResetPass(String uName, String rn, String mail);
}
