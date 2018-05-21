package kz.crtr.app.local;

import kz.crtr.app.domain.AppSearch;
import kz.crtr.app.domain.PageCommon;
import org.springframework.security.core.Authentication;

/**
 *
 * @author User
 */
public interface IApplication {

    PageCommon getApplicationList(Authentication authentication, AppSearch search, int start, int length);


}
