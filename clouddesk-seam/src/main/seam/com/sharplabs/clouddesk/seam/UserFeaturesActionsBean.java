/**
 * 
 */

package com.sharplabs.clouddesk.seam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;

/**
 * Seam component used to filter tabs based on the user features.
 */
@Name("userFeaturesActions")
@Scope(ScopeType.SESSION)
public class UserFeaturesActionsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Log log = LogFactory
			.getLog(UserFeaturesActionsBean.class);

	@In(create = true, required = false)
	protected transient CoreSession documentManager;

	@In(create = true, required = false)
	protected NuxeoPrincipal currentNuxeoPrincipal;

	private List<String> features;

	protected List<String> fetchUserFeatures() {
		List<String> list = new ArrayList<String>();
		// TODO: Read this information from the user profile.
		return list;
	}

	protected void checkFetchFeatures() {
		if (features == null) {
			features = fetchUserFeatures();
		}
	}

	public boolean hasFeatureEnabled(String featureId) {
		checkFetchFeatures();
		return features.contains(featureId);
	}
}
