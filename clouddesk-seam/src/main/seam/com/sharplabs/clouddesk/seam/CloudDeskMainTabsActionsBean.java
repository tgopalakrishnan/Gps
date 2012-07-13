package com.sharplabs.clouddesk.seam;

import java.io.Serializable;

import org.nuxeo.ecm.platform.actions.Action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.nuxeo.ecm.core.api.ClientException;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.ui.web.api.NavigationContext;
import org.nuxeo.ecm.platform.userworkspace.api.UserWorkspaceManagerActions;
import org.nuxeo.ecm.webapp.action.MainTabsActions;
import org.nuxeo.ecm.webapp.helpers.ResourcesAccessor;

/**
 * Seam component to handle navigation across the top level tabs.
 */
@Name("cloudDeskMainTabsActions")
@Scope(ScopeType.EVENT)
public class CloudDeskMainTabsActionsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(CloudDeskMainTabsActionsBean.class);

    @In(create = true, required = false)
    protected transient CoreSession documentManager;

    @In(create = true)
    protected NavigationContext navigationContext;

    @In(create = true, required = false)
    protected transient FacesMessages facesMessages;

    @In(create = true)
    protected transient ResourcesAccessor resourcesAccessor;
    
    @In(create = true)
    protected UserWorkspaceManagerActions userWorkspaceManagerActions;
    
    @In(create = true)
    protected MainTabsActions mainTabsActions;
    
    public DocumentModel getDocumentForTab(Action tab) throws ClientException {
        if ("clouddesk_my_documents".equals(tab.getId())) {
            // force invalidation of the tree navigation:
        	//userWorkspaceManagerActions.navigateToCurrentUserPersonalWorkspace();
            return userWorkspaceManagerActions.getCurrentUserPersonalWorkspace();
        } 
        else if ("clouddesk_shared_with_me".equals(tab.getId())) {
            // force invalidation of the tree navigation:
        	//userWorkspaceManagerActions.navigateToCurrentUserPersonalWorkspace();
            
        	return userWorkspaceManagerActions.getCurrentUserPersonalWorkspace();
        	//return userWorkspaceManagerActions.getCurrentUserPersonalWorkspace();
        } 

        
        else {
            // fallback to the default nuxe behavior
            return mainTabsActions.getDocumentFor(tab.getId());
        }
    }

}
