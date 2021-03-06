package org.knetwork.webapp;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String display(final HttpSession session, final HttpServletRequest request, final Model model) throws MalformedURLException {
        final Token accessToken = (Token) session.getAttribute("accessToken");
        if (accessToken == null) {
        	logger.debug("Not logged in, redirecting to login page.");
            return "login";
        } else {
        	logger.debug("Already logged in.");
        	return "home";
        }
    }
    
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("accessToken");
        return "redirect:/";
    }

}
