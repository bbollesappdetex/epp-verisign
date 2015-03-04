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


package com.verisign.epp.codec.syncext;

import java.util.HashSet;
import java.util.Set;

import com.verisign.epp.codec.gen.*;

import org.w3c.dom.*;

/**
 * The EPPCodec Extension Factory that needs to be configured to encode/decode
 * Sync extensions.
 *
 * The Sync URI is: http://www.verisign.com/epp/sync-1.0
 *
 *
 *
 * <p>Title: EPP 1.0 RGP </p>
 * <p>Description: SYNC Extension to the EPP SDK</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: VeriSign</p>
 * @author clloyd
 * @version 1.0
 */

public class EPPSyncExtFactory
    extends EPPExtFactory {

    /** Namespace URI associated with EPPSyncExtFactory. */
    public static final String NS = "http://www.verisign.com/epp/sync-1.0";

    /** Namespace prefix associated with EPPSyncExtFactory. */
    public static final String NS_PREFIX = "sync";

    /** EPP Sync XML Schema. */
    public static final String NS_SCHEMA =
        "http://www.verisign.com/epp/sync-1.0 sync-1.0.xsd";

    /**
     *  Service object associated with EPPSyncExtFactory.  The service
     * object is used when creating the Greeting or the Login.
     */
    private EPPService service;

    /**
     * Create a new instance of EPPsyncExtFactory
     */
    public EPPSyncExtFactory() {
        service = new EPPService(NS_PREFIX, NS, NS_SCHEMA);
        service.setServiceType(EPPService.EXT_SERVICE);
    }

    /**
     * Overridden but doesn't do anything in the context of Sync.  Throws
     * an EPPCodecException if called
     *
     * @param aExtensionElm Element to create protocol extension from 
     * @return Return protocol extension if applicable
     * @throws com.verisign.epp.codec.gen.EPPCodecException When protocol extension is not supported
     */
    public EPPProtocolExtension createProtocolExtension(Element aExtensionElm) throws
        com.verisign.epp.codec.gen.EPPCodecException {
        throw new EPPCodecException(
            "EPPSyncExtFactory.createProtocolExtension: Protocol extensions not supported");
    }

    /**
     * Creates the concrete Sync ext instance when decoding XML that contains
     * a Sync Extension.  This is only EPPSyncExtUpdate as of 02/13/04
     *
     * @param aExtensionElm The DOM element that is a parent to the Sync XML fragment
     * @return A concrete EPPCodecComponet that knows how to decode itself from the
     * rest of the DOM document.
     * @throws com.verisign.epp.codec.gen.EPPCodecException Thrown if an unrecognized
     * sync element is found below the passed in extension element
     */

    public EPPCodecComponent createExtension(Element aExtensionElm) throws com.
        verisign.epp.codec.gen.EPPCodecException {

		String name = aExtensionElm.getLocalName();
		
		if (!aExtensionElm.getNamespaceURI().equals(NS)) {
			throw new EPPCodecException("Invalid extension type " + name);
		}		

        if (name.equals(EPPUtil
				.getLocalName(EPPSyncExtUpdate.ELM_NAME))) {
            return new EPPSyncExtUpdate();
        }
        else {
            throw new EPPCodecException("Invalid extension element " + name);
        }
    }

    /**
     * Returns the EPPService instance associated with this ExtFactory. The
     * EPPService instance contains the XML Namespace and XML Schema location
     *
     * @return  the EPPService instance associated with this ExtFactory
     */
    public EPPService getService() {
        return service;
    }
    
	/**
	 * Gets the list of XML schemas that need to be pre-loaded into the 
	 * XML Parser.
	 *
	 * @return <code>Set</code> of <code>String</code> XML Schema names that 
	 * should be pre-loaded in the XML Parser.
	 *   
	 * @see com.verisign.epp.codec.gen.EPPMapFactory#getXmlSchemas()
	 */
	public Set getXmlSchemas() {
		Set theSchemas = new HashSet();
		theSchemas.add("sync-1.0.xsd");
		return theSchemas;
	}
    
}