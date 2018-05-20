package kz.crtr.emaket.security;

import org.springframework.security.core.Authentication;

/**
 * @author Bekzhan
 */
public interface IAuthenticationFacade {
    Authentication getAuthentication();
}
