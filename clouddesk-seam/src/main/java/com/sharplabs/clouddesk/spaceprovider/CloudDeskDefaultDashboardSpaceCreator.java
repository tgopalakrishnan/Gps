package com.sharplabs.clouddesk.spaceprovider;

import static org.nuxeo.ecm.core.api.security.SecurityConstants.EVERYONE;
import static org.nuxeo.ecm.core.api.security.SecurityConstants.EVERYTHING;
import static org.nuxeo.ecm.spaces.api.Constants.SPACE_DOCUMENT_TYPE;
import static org.nuxeo.opensocial.container.shared.layout.api.LayoutHelper.Preset.X_2_DEFAULT;

import java.security.Principal;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.common.utils.Path;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.security.ACE;
import org.nuxeo.ecm.core.api.security.ACL;
import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.SecurityConstants;
import org.nuxeo.ecm.multi.tenant.MultiTenantHelper;
import org.nuxeo.ecm.multi.tenant.userworkspace.MultiTenantUserWorkspaceService;
import org.nuxeo.ecm.platform.userworkspace.api.UserWorkspaceService;
import org.nuxeo.ecm.spaces.api.Space;
import org.nuxeo.ecm.spaces.helper.WebContentHelper;
import org.nuxeo.ecm.user.center.dashboard.DefaultDashboardSpaceCreator;
import org.nuxeo.opensocial.container.shared.layout.api.LayoutHelper;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;

/**
 * Defines the list of gadget to be loaded by default in the (Business) Admin
 * customization view.
 */
public class CloudDeskDefaultDashboardSpaceCreator extends
        DefaultDashboardSpaceCreator {
    protected String tenantId;
    private CoreSession tempSession;
    protected String tenantDashboardManagementPath;
    private static final Log log = LogFactory.getLog(CloudDeskDefaultDashboardSpaceCreator.class);
    public CloudDeskDefaultDashboardSpaceCreator(CoreSession session,
            Map<String, String> parameters, String tenantId,
            String tenantDashboardManagementPath,CoreSession temp ) {
        super(session, parameters);
        this.tenantId = tenantId;
        this.tenantDashboardManagementPath = tenantDashboardManagementPath;
        // Default session is getting overwritten by DefaultDashboardSpaceCreator
        //we are creating the temporary session
        this.tempSession = temp;
    }

    @Override
    public void run() throws ClientException {
    	String tenentuserWorkSpace = null;
    	try {
    		
			UserWorkspaceService svc = Framework.getService(UserWorkspaceService.class);
			tenentuserWorkSpace =  svc.getUserPersonalWorkspace(getOriginatingUsername(), session.getRootDocument()).getPathAsString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String defaultDashboardSpacePath = new Path(
    			tenentuserWorkSpace).append(
                DEFAULT_DASHBOARD_SPACE_NAME).toString();
        DocumentRef defaultDashboardSpacePathRef = new PathRef(
                defaultDashboardSpacePath);

        DocumentModel defaultDashboardSpace;
        if (!session.exists(defaultDashboardSpacePathRef)) {
            defaultDashboardSpace = createDefaultDashboardSpace(tenentuserWorkSpace);
        } else {
            defaultDashboardSpace = session.getDocument(defaultDashboardSpacePathRef);
        }
        defaultDashboardSpaceRef = defaultDashboardSpace.getRef();
    }

    protected void addDefaultACP(DocumentModel defaultDashboardSpace)
            throws ClientException {
        ACP acp = defaultDashboardSpace.getACP();
        ACL acl = acp.getOrCreateACL();
        String tenantAdministratorsGroup = MultiTenantHelper.computeTenantAdministratorsGroup(tenantId);
        acl.add(new ACE(tenantAdministratorsGroup,
                SecurityConstants.EVERYTHING, true));
        acl.add(new ACE(session.getPrincipal().getName(), EVERYTHING, true));
        acl.add(new ACE(getOriginatingUsername(), EVERYTHING, true));
        acl.add(new ACE(EVERYONE, EVERYTHING, false));
        
        defaultDashboardSpace.setACP(acp, true);
    }

    @Override
    protected void initializeLayout(Space space) throws ClientException {
        space.initLayout(LayoutHelper.buildLayout(X_2_DEFAULT));
    }

    @Override
    protected void initializeGadgets(Space space, CoreSession session,
            Locale locale) throws ClientException {
    	NuxeoPrincipal  principle = (NuxeoPrincipal) tempSession.getPrincipal();
        if (principle.isAdministrator())
        {
        	WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mydocs", 0, 0, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale, "myotherdocs",
                    0, 0, 1);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mycloudfolders", 0, 1, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "myuserws", 0, 1, 1);
        }
        else if(principle.isMemberOf("members") )
        {
        	WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mydocs", 0, 0, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale, "myotherdocs",
                    0, 0, 1);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mycloudfolders", 0, 1, 0);
        }
        else if(principle.isMemberOf("powerusers") )
        {
        	WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mydocs", 0, 0, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale, "myotherdocs",
                    0, 0, 1);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mycloudfolders", 0, 1, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "myuserws", 0, 1, 1);
        }

        else 
        {
        	WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mydocs", 0, 0, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale, "myotherdocs",
            		0, 1, 0);
            WebContentHelper.createOpenSocialGadget(space, session, locale,
                    "mycloudfolders", 0, 1, 0);
        }
        
    }
    protected DocumentModel getUserPersonalWorkspace(CoreSession session)
            throws ClientException,SecurityException {
        try {
            UserWorkspaceService svc = Framework.getService(MultiTenantUserWorkspaceService.class);
            return svc.getCurrentUserPersonalWorkspace(session, session.getRootDocument());
        } catch (Exception e) {
            throw new ClientException(e);
        }

    }

}
