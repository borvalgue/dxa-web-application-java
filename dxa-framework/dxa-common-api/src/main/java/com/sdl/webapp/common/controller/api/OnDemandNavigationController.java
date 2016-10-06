package com.sdl.webapp.common.controller.api;

import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.model.entity.SitemapItem;
import com.sdl.webapp.common.api.navigation.NavigationFilter;
import com.sdl.webapp.common.api.navigation.OnDemandNavigationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.sdl.webapp.common.api.serialization.json.filter.IgnoreByNameInRequestFilter.ignoreByName;

/**
 * Handles requests to on-demand navigation API.
 */
@SuppressWarnings("MVCPathVariableInspection")
@Controller
@RequestMapping(method = RequestMethod.GET, value = {"/api/navigation/subtree", "/{path}/api/navigation/subtree"})
@Profile("dynamic.navigation.provider")
@Slf4j
public class OnDemandNavigationController {

    private final OnDemandNavigationProvider onDemandNavigationProvider;

    private final WebRequestContext webRequestContext;

    @Autowired
    public OnDemandNavigationController(OnDemandNavigationProvider onDemandNavigationProvider, WebRequestContext webRequestContext) {
        this.onDemandNavigationProvider = onDemandNavigationProvider;
        this.webRequestContext = webRequestContext;
    }

    @ResponseBody
    @RequestMapping
    public List<SitemapItem> handle(@RequestParam(value = "includeAncestors", required = false, defaultValue = "false") boolean includeAncestors,
                                    @RequestParam(value = "descendantLevels", required = false, defaultValue = "1") int descendantLevels,
                                    HttpServletRequest request) {

        return handleInternal(null, includeAncestors, descendantLevels, request);
    }

    @ResponseBody
    @RequestMapping("/{sitemapItemId}")
    public List<SitemapItem> handle(@PathVariable("sitemapItemId") String sitemapItemId,
                                    @RequestParam(value = "includeAncestors", required = false, defaultValue = "false") boolean includeAncestors,
                                    @RequestParam(value = "descendantLevels", required = false, defaultValue = "1") int descendantLevels,
                                    HttpServletRequest request) {
        return handleInternal(sitemapItemId, includeAncestors, descendantLevels, request);
    }

    private List<SitemapItem> handleInternal(String sitemapItemId, boolean includeAncestors, int descendantLevels,
                                             HttpServletRequest request) {
        if (onDemandNavigationProvider == null) {
            String message = "On-Demand Navigation is not enabled because current navigation provider doesn't support it. " +
                    "If you are using your own navigation provider, you should Implement OnDemandNavigationProvider interface, " +
                    "otherwise check document on how you should enable OnDemandNavigation.";
            log.warn(message);
            throw new UnsupportedOperationException(message);
        }

        ignoreByName(request, "XpmMetadata", "XpmPropertyMetadata");

        NavigationFilter navigationFilter = new NavigationFilter();
        navigationFilter.setWithAncestors(includeAncestors);
        navigationFilter.setDescendantLevels(descendantLevels);
        return onDemandNavigationProvider.getNavigationSubtree(sitemapItemId, navigationFilter, webRequestContext.getLocalization());
    }
}
