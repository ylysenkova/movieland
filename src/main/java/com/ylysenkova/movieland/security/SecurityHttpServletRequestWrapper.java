package com.ylysenkova.movieland.security;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class SecurityHttpServletRequestWrapper extends HttpServletRequestWrapper {


    private Principal principal;

    public SecurityHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

}
