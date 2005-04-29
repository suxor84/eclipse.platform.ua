/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.help.ui.internal;

import org.eclipse.help.ILiveHelpAction;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class ShowCapabilitiesPreferenceAction implements ILiveHelpAction {
	private boolean narrow;

	public void setInitializationString(String data) {
		if (data!=null && data.equals("narrow"))
			narrow=true;
	}

	public void run() {
		final Display display = PlatformUI.getWorkbench().getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				Shell windowShell=null;
				if (!narrow) {
					Shell[] shells = display.getShells();
					for (int i=0; i<shells.length; i++) {
						Object data = shells[i].getData();
						if (data!=null && data instanceof IWorkbenchWindow) {
							windowShell=shells[i];
							break;
						}
					}
				}
				if (windowShell!=null)
					windowShell.setActive();
				PreferenceDialog dialog = PreferencesUtil
						.createPreferenceDialogOn(windowShell, getCapabilityPageId(),
								null, null);
				dialog.open();
			}
		});
	}

	private String getCapabilityPageId() {
		return "org.eclipse.sdk.capabilities"; //$NON-NLS-1$
	}
}