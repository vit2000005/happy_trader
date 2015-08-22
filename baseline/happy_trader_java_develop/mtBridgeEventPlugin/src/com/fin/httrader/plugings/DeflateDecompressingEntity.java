/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fin.httrader.plugings;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.entity.HttpEntityWrapper;

/**
 *
 * @author DanilinS
 */
public class DeflateDecompressingEntity extends HttpEntityWrapper  {
	public DeflateDecompressingEntity(final HttpEntity entity) {
		super(entity);
	}

	@Override
	public InputStream getContent()	throws IOException, IllegalStateException {

		// the wrapped entity's getContent() decides about repeatability
		InputStream wrappedin = wrappedEntity.getContent();

		return new InflaterInputStream(wrappedin);
	}

	@Override
	public long getContentLength() {
		// length of ungzipped content is not known
		return -1;
	}
}
