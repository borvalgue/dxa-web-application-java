package com.sdl.webapp.main.controller.core;

import com.sdl.webapp.common.api.model.Page;
import com.sdl.webapp.main.controller.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.sdl.webapp.main.WebAppConstants.PAGE_PATH_PREFIX;
import static com.sdl.webapp.main.WebAppConstants.PAGE_MODEL;

@Controller
@RequestMapping(PAGE_PATH_PREFIX)
public class IncludePageController extends ControllerBase {
    private static final Logger LOG = LoggerFactory.getLogger(IncludePageController.class);

    @RequestMapping(method = RequestMethod.GET, value = "{includePageName}")
    public String handleIncludePage(HttpServletRequest request, @PathVariable String includePageName) {
        LOG.debug("handleIncludePage: includePageName={}", includePageName);

        final Page page = getPageFromRequest(request);
        final Page includePage = page.getIncludes().get(includePageName);
        if (includePage == null) {
            LOG.error("Include page not found: {}", includePageName);
            throw new NotFoundException("Include page not found: " + includePageName);
        }

        request.setAttribute(PAGE_MODEL, includePage);

        final String viewName = includePage.getViewName();
        LOG.debug("viewName: {}", viewName);
        return viewName;
    }
}