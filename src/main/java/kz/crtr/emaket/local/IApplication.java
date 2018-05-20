package kz.crtr.emaket.local;

import kz.crtr.emaket.domain.AppSearch;
import kz.crtr.emaket.domain.PageCommon;
import org.springframework.security.core.Authentication;

/**
 *
 * @author User
 */
public interface IApplication {

    PageCommon getApplicationList(Authentication authentication, AppSearch search, int start, int length);


}
