/***********************************************************
 Copyright (C) 2004 VeriSign, Inc.

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-0107  USA

 http://www.verisign.com/nds/naming/namestore/techdocs.html
 ***********************************************************/

package com.verisign.epp.codec.suggestion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.verisign.epp.codec.gen.EPPCodecComponent;
import com.verisign.epp.codec.gen.EPPDecodeException;
import com.verisign.epp.codec.gen.EPPEncodeException;
import com.verisign.epp.codec.gen.EPPUtil;
import com.verisign.epp.codec.suggestion.util.ExceptionUtil;
import com.verisign.epp.util.EqualityUtil;

/**
 * The Related element describes a word related to the encapsulating token.
 * 
 * @author jcolosi
 */
public class EPPSuggestionRelated implements EPPCodecComponent {

	private static final long serialVersionUID = -4273373513816529927L;

	static final String ELM_NAME = "suggestion:related";

	private String related = null;


	/**
	 * Constructor.
	 */
	public EPPSuggestionRelated () {
	}


	/**
	 * Constructor.
	 * 
	 * @param aRelated
	 *        related value
	 */
	public EPPSuggestionRelated ( final String aRelated ) {
		setRelated( aRelated );
	}


	/**
	 * Constructor.
	 * 
	 * @param aElement
	 *        a dom element
	 * @throws EPPDecodeException
	 */
	public EPPSuggestionRelated ( final Element aElement )
			throws EPPDecodeException {
		decode( aElement );
	}


	/**
	 * Related getter.
	 * 
	 * @return a related value
	 */
	public String getRelated () {
		return related;
	}


	/**
	 * Related setter.
	 * 
	 * @param aRelated
	 *        a related value
	 */
	public void setRelated ( final String aRelated ) {
		this.related = aRelated;
	}


	/**
	 * Implementation of <code>Object.toString</code>, which will result in an
	 * indented XML <code>String</code> representation of the concrete
	 * <code>EPPCodecComponent</code>.
	 * 
	 * @return Indented XML <code>String</code> if successful;
	 *         <code>ERROR</code> otherwise.
	 */
	@Override
	public String toString () {
		return EPPUtil.toString( this );
	}


	@Override
	public Object clone () throws CloneNotSupportedException {
		return (EPPSuggestionRelated) super.clone();
	}


	@Override
	public void decode ( final Element aElement ) throws EPPDecodeException {
		this.related = aElement.getFirstChild().getNodeValue();
	}


	@Override
	public Element encode ( final Document aDocument )
			throws EPPEncodeException {
		Element root =
				aDocument
						.createElementNS( EPPSuggestionMapFactory.NS, ELM_NAME );
		if ( related == null )
			ExceptionUtil.missingDuringEncode( "related" );
		root.appendChild( aDocument.createTextNode( related ) );
		return root;
	}


	@Override
	public boolean equals ( final Object o ) {
		if ( (o != null) && (o.getClass().equals( this.getClass() )) ) {
			EPPSuggestionRelated other = (EPPSuggestionRelated) o;
			if ( !EqualityUtil.equals( this.related, other.related ) )
				return false;
			return true;
		}
		return false;
	}
}