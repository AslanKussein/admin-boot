package kz.crtr.app.security;

import kz.crtr.app.entity.gson.GsonUsers;
import kz.crtr.app.entity.tbl.Groupmembers;
import kz.crtr.app.entity.tbl.Users;
import kz.crtr.app.gson.GsonResult;
import kz.crtr.app.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;

import static kz.crtr.app.utils.Constant.*;
import static kz.crtr.app.wrapper.UserWrapper.wrapToGsonUsers;

/**
 * @author a.kussein
 */
@Component
@Log
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    private EntityManager em;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {

        GsonResult checkUser = null;

        String j_username = authentication.getName();

        Users users = userRepository.findUsersByUName(j_username);

        List<Groupmembers> groupmembers = getGroupmembers(j_username);

        GsonUsers user = null;
        try {
            user = wrapToGsonUsers(users, groupmembers);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (user != null) {

            user.setIpAddress(getIPAddress(request));
            user.setHost(getHostName());
            user.setComputerName(getHostName());

            HttpSession session = request.getSession(Boolean.TRUE);
            session.setAttribute(USER, user);

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        }
    }

    private List<Groupmembers> getGroupmembers(String uName) {
        try {
            return em.createNamedQuery("Groupmembers.findByGMember").setParameter("gMember", uName).getResultList();
        } catch (Exception e) {
            log.log(Level.SEVERE, DEF_ERROR_PAR + "(uName: " + uName + ")", e);
        }
        return null;
    }

    private String getIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.equals(MAIN_SERVER_IP)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    private String getHostName() {
        String hostname = "Unknown";
        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        } catch (UnknownHostException ex) {
            log.log(Level.SEVERE, "Hostname can not be resolved");
        }
        return hostname;
    }
}
