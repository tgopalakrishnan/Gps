/*
 * (C) Copyright 2006-2011 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo
 */

package com.sharplabs.clouddesk.seam;

import static org.jboss.seam.ScopeType.SESSION;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.webapp.helpers.StartupHelper;

/**
 * Overriding default {@see org.nuxeo.ecm.webapp.helpers.StartupHelper} to
 * redirect user into their dashboards after login in.
 *
 * @author Arnaud Kervern <akervern@nuxeo.com>
 * @since 5.5
 */
@Name("startupHelper")
@Scope(SESSION)
@Install(precedence = Install.APPLICATION + 1)
public class ClouddeskStartupPage extends StartupHelper {

    private static final long serialVersionUID = 3248232384219879845L;

    @Override
    @Begin(id = "#{conversationIdGenerator.nextMainConversationId}", join = true)
    public String initDomainAndFindStartupPage(String domainTitle, String viewId) {
        String result = super.initDomainAndFindStartupPage(domainTitle, viewId);

        	return "cd_view_home";
    }
}
