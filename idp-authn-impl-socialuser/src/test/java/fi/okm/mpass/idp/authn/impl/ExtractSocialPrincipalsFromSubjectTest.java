/*
 * The MIT License
 * Copyright (c) 2015 CSC - IT Center for Science, http://www.csc.fi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package fi.okm.mpass.idp.authn.impl;

import org.testng.Assert;

import net.shibboleth.idp.authn.AuthnEventIds;
import net.shibboleth.idp.authn.ExternalAuthentication;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.shibboleth.idp.authn.context.AuthenticationContext;
import net.shibboleth.idp.authn.context.ExternalAuthenticationContext;
import net.shibboleth.idp.authn.context.UsernamePasswordContext;
import net.shibboleth.idp.authn.impl.PopulateAuthenticationContextTest;

import org.springframework.webflow.execution.Event;

import net.shibboleth.idp.profile.ActionTestingSupport;

import org.springframework.mock.web.MockHttpServletRequest;

/** {@link ExtractSocialPrincipalsFromSubject} unit test. */
public class ExtractSocialPrincipalsFromSubjectTest extends PopulateAuthenticationContextTest{

    private ExtractSocialPrincipalsFromSubject action; 
    
    @BeforeMethod public void setUp() throws Exception {
        super.setUp();
        action = new ExtractSocialPrincipalsFromSubject();
        MockHttpServletRequest mockHttpServletRequest=new MockHttpServletRequest();
        action.setHttpServletRequest(mockHttpServletRequest);
        action.initialize();
    }
    
    @Test public void testNoServlet() throws Exception {
        final Event event = action.execute(src);
        
        ActionTestingSupport.assertEvent(event, AuthnEventIds.NO_CREDENTIALS);
    }
    
    @Test public void testMissingIdentity() throws Exception {
        AuthenticationContext authCtx = prc.getSubcontext(AuthenticationContext.class, false);
        final UsernamePasswordContext upCtx = authCtx.getSubcontext(UsernamePasswordContext.class, true);
        upCtx.setUsername(null);
        upCtx.setPassword(null);
        final Event event = action.execute(src);
        ActionTestingSupport.assertEvent(event, AuthnEventIds.NO_CREDENTIALS);
    }
    
    @Test public void testIdentity() throws Exception {
        //TODO..
        //AuthenticationContext authCtx = prc.getSubcontext(AuthenticationContext.class, false);
        //final UsernamePasswordContext upCtx = authCtx.getSubcontext(UsernamePasswordContext.class, true);
        //upCtx.setUsername("test123");
              
    }

}
