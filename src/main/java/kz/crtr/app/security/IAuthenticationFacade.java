package kz.crtr.app.security;

import org.springframework.security.core.Authentication;

/**
 * @author Bekzhan
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
