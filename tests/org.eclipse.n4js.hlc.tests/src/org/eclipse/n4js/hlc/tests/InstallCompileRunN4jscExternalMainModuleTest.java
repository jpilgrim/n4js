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
package org.eclipse.n4js.hlc.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.n4js.N4JSGlobals;
import org.eclipse.n4js.hlc.base.BuildType;
import org.eclipse.n4js.hlc.base.ExitCodeException;
import org.eclipse.n4js.utils.io.FileDeleter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Downloads, installs, compiles and runs several packages that are known to be problematic in terms of how they define
 * main module.
 */
public class InstallCompileRunN4jscExternalMainModuleTest extends AbstractN4jscTest {

	File workspace;

	/** Prepare workspace. */
	@Before
	public void setupWorkspace() throws IOException {
		workspace = setupWorkspace("externalmm", true, N4JSGlobals.N4JS_RUNTIME);
	}

	/** Delete workspace. */
	@After
	public void deleteWorkspace() throws IOException {
		FileDeleter.delete(workspace.toPath(), true);
	}

	/**
	 * Test for checking the npm support in the headless case by downloading third party package, importing it and
	 * running it with Common JS.
	 */
	@Test
	public void testCompileAndRunWithExternalDependencies()
			throws IOException, ExitCodeException {

		final String wsRoot = workspace.getAbsolutePath().toString();
		final String packages = wsRoot + "/packages";
		final String fileToRun = packages + "/external.project.mm/src/Main.n4js";

		final String[] args = {
				"--projectlocations", packages,
				"--buildType", BuildType.allprojects.toString(),
				"--installMissingDependencies",
				"--runWith", "nodejs",
				"--run", fileToRun
		};
		final String actual = runAndCaptureOutput(args);
		StringBuilder expected = new StringBuilder()
				.append("express imported").append("\n")
				.append("jade imported").append("\n")
				.append("lodash imported").append("\n")
				.append("karma imported").append("\n")
				.append("bar imported").append("\n")
				.append("pouchdb-find imported").append("\n")
				.append("next imported").append("\n")
				.append("body-parser imported");

		Assert.assertTrue(
				"Actual output does not match with pattern.\nActual:\n" + actual + "\nExpected pattern:\n" + expected,
				actual.matches(expected.toString()));

	}

}
