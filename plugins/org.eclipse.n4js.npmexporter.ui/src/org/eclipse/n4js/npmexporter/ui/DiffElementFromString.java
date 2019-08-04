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
package org.eclipse.n4js.npmexporter.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.compare.IEncodedStreamContentAccessor;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;

/**
 * Diffing text parts.
 */
class DiffElementFromString implements ITypedElement, IEncodedStreamContentAccessor {
	protected String text;

	public DiffElementFromString(String text) {
		this.text = text;
	}

	@Override
	public String getName() {
		return "<no name>"; //$NON-NLS-1$
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getType() {
		return TEXT_TYPE;
	}

	@Override
	public InputStream getContents() {
		try {
			return new ByteArrayInputStream(text.getBytes(getCharset()));
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String getCharset() throws CoreException {
		return "UTF-8"; //$NON-NLS-1$
	}
}