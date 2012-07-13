package com.sharplabs.clouddesk.spaceprovider;

import static org.nuxeo.ecm.user.center.dashboard.AbstractDashboardSpaceCreator.DASHBOARD_MANAGEMENT_NAME;
import static org.nuxeo.ecm.user.center.dashboard.AbstractDashboardSpaceCreator.DASHBOARD_MANAGEMENT_TYPE;
import static org.nuxeo.ecm.user.center.dashboard.DefaultDashboardSpaceCreator.DEFAULT_DASHBOARD_SPACE_NAME;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.PathRef;
import com.sharplabs.clouddesk.spaceprovider.CloudDeskDefaultDashboardSpaceCreator;

import org.nuxeo.ecm.multi.tenant.MultiTenantHelper;
import org.nuxeo.ecm.platform.userworkspace.api.UserWorkspaceService;
import org.nuxeo.ecm.spaces.api.Space;
import org.nuxeo.ecm.user.center.dashboard.DefaultDashboardSpaceProvider;
import org.nuxeo.runtime.api.Framework;

public class CloudDeskDefaultDashboardSpaceProvider extends
        DefaultDashboardSpaceProvider {
    
    private static final Log log = LogFactory.getLog(CloudDeskDefaultDashboardSpaceProvider.class);

    protected Space getOrCreateSpace(CoreSession session,
            Map<String, String> parameters) throws ClientException,SecurityException {
        String tenantId = MultiTenantHelper.getCurrentTenantId(session.getPrincipal());
        if (StringUtils.isBlank(tenantId)) {
            //return super.getOrCreateSpace(session, parameters);
        }
         

         String tenantDocumentPath = null;
		try {
			UserWorkspaceService svc = Framework.getService(UserWorkspaceService.class);
			tenantDocumentPath = svc.getCurrentUserPersonalWorkspace(session, session.getRootDocument()).getPathAsString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

         
       //MultiTenantHelper.getTenantDocumentPath(session, tenantId);
        if (tenantDocumentPath == null) {
            return super.getOrCreateSpace(session, parameters);
        }

        PathRef tenantDashboardManagementRef = new PathRef(tenantDocumentPath,
                DASHBOARD_MANAGEMENT_NAME);
        
        if (session.exists(tenantDashboardManagementRef)) {
            DocumentRef spaceRef = new PathRef(
                    tenantDashboardManagementRef.toString(),
                    DEFAULT_DASHBOARD_SPACE_NAME);
            if (session.exists(spaceRef)) {
                DocumentModel existingSpace = session.getDocument(spaceRef);
                return existingSpace.getAdapter(Space.class);
            } else {
                DocumentRef defaultDashboardSpaceRef = getOrCreateDefaultDashboardSpace(
                        session, parameters, tenantId,
                        tenantDashboardManagementRef.toString());
                DocumentModel defaultDashboardSpace = session.getDocument(defaultDashboardSpaceRef);
                return defaultDashboardSpace.getAdapter(Space.class);
            }
        } else {
            DocumentModel tenantDashboardManagement = session.createDocumentModel(
                    tenantDocumentPath, DASHBOARD_MANAGEMENT_NAME,
                    DASHBOARD_MANAGEMENT_TYPE);
            tenantDashboardManagement.setPropertyValue("dc:title",
                    "Tenant dashboard management");
            tenantDashboardManagement.setPropertyValue("dc:description",
                    "Tenant dashboard management");
            tenantDashboardManagement = session.createDocument(tenantDashboardManagement);
            DocumentRef defaultDashboardSpaceRef = getOrCreateDefaultDashboardSpace(
                    session, parameters, tenantId,
                    tenantDashboardManagement.getPathAsString());
            DocumentModel defaultDashboardSpace = session.getDocument(defaultDashboardSpaceRef);
            return defaultDashboardSpace.getAdapter(Space.class);
        }

    }

    protected DocumentRef getOrCreateDefaultDashboardSpace(CoreSession session,
            Map<String, String> parameters, String tenantId,
            String tenantDashboardManagementPath) throws ClientException {
    	CloudDeskDefaultDashboardSpaceCreator defaultDashboardSpaceCreator = new CloudDeskDefaultDashboardSpaceCreator(
                session, parameters, tenantId, tenantDashboardManagementPath,session);
        defaultDashboardSpaceCreator.runUnrestricted();
        return defaultDashboardSpaceCreator.defaultDashboardSpaceRef;
    }

}
