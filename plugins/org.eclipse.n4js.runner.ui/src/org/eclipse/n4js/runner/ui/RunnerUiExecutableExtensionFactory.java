/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package org.eclipse.n4js.runner.ui;

import static org.eclipse.n4js.runner.ui.RunnerUiModule.RUNNER_UI_MODULE_ID;

import com.google.inject.Injector;

import org.eclipse.n4js.utils.N4ExecutableExtensionFactory;

/**
 * Executable extension factory for the runner UI module.
 */
public class RunnerUiExecutableExtensionFactory extends N4ExecutableExtensionFactory {

	@Override
	protected ClassLoader getClassLoader() {
		return RunnerUiActivator.getDefault().getClass().getClassLoader();
	}

	@Override
	protected Injector getInjector() {
		return RunnerUiModule.getInjector(RUNNER_UI_MODULE_ID);
	}

	@Override
	protected String getBundleId() {
		return RunnerUiActivator.PLUGIN_ID;
	}

}
