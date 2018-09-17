package web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String renderErrorGetPage(HttpServletRequest httpRequest, ModelMap errorPage) {
        return renderErrorPage(httpRequest, errorPage);
    }

    @RequestMapping(value = "errors", method = RequestMethod.POST)
    public String renderErrorPostPage(HttpServletRequest httpRequest, ModelMap errorPage) {
        return renderErrorPage(httpRequest, errorPage);
    }

    private String renderErrorPage(HttpServletRequest httpRequest, ModelMap errorPage) {
        String errorMsg = getErrorMessage(httpRequest);
        errorPage.addAttribute("errorMsg", errorMsg);
        return "errorPage";
    }

    private String getErrorMessage(HttpServletRequest httpRequest) {
        Integer code = getErrorCode(httpRequest);
        return String.format("Http Error Code: %d. ", code) + HttpStatus.valueOf(code).getReasonPhrase();
    }

    private Integer getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}

