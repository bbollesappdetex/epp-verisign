/***********************************************************
Copyright (C) 2004 VeriSign, Inc.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

http://www.verisign.com/nds/naming/namestore/techdocs.html
***********************************************************/
package com.verisign.epp.util;


// Log4j Imports
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XNIException;

// Xerces imports
import org.apache.xerces.xni.parser.XMLEntityResolver;
import org.apache.xerces.xni.parser.XMLInputSource;

import org.xml.sax.InputSource;

// XML imports
import org.xml.sax.SAXException;

// Java imports
import java.io.IOException;
import java.io.InputStream;


/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class EPPSchemaParsingEntityResolver implements XMLEntityResolver {
	/** Log4j category for logging */
	private static Logger cat =
		Logger.getLogger(
						 EPPSchemaParsingEntityResolver.class.getName(),
						 EPPCatFactory.getInstance().getFactory());

	/**
	 * Creates a new EPPSchemaParsingEntityResolver object.
	 */
	public EPPSchemaParsingEntityResolver() {
	}

	/**
	 * Resolves the entity passed in when parsing the instance document.  Will
	 * try to find the schema from the classpath.
	 *
	 * @param aXMLResourceIdentifier DOCUMENT ME!
	 *
	 * @return DOCUMENT ME!
	 *
	 * @throws org.apache.xerces.xni.XNIException DOCUMENT ME!
	 * @throws java.io.IOException DOCUMENT ME!
	 */
	public XMLInputSource resolveEntity(XMLResourceIdentifier aXMLResourceIdentifier)
								 throws org.apache.xerces.xni.XNIException, 
										java.io.IOException {
		String	    baseSystemId    = aXMLResourceIdentifier.getBaseSystemId();
		String	    systemId	    =
			aXMLResourceIdentifier.getExpandedSystemId();
		String	    literalSystemId =
			aXMLResourceIdentifier.getLiteralSystemId();
		String	    namespace = aXMLResourceIdentifier.getNamespace();
		String	    publicId  = aXMLResourceIdentifier.getPublicId();

		InputStream schemaStream =
			getClass().getClassLoader().getResourceAsStream("schemas/"
															+ literalSystemId);

		return new XMLInputSource(
								  publicId, systemId, baseSystemId, schemaStream,
								  null);
	}
}
