/*
 *   
 * TOM - To One Matching Compiler
 * 
 * Copyright (C) 2000-2004 Inria
 * Nancy, France.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA
 * 
 * Pierre-Etienne Moreau  e-mail: Pierre-Etienne.Moreau@loria.fr
 * Julien Guyon						e-mail: Julien.guyon@loria.fr
 * 
 **/

package fr.loria.eclipse.jtom.editor.template;

import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.GlobalTemplateVariables;


/**
 * A very simple context type.
 */
public class TomContextType extends TemplateContextType {

	/** This context's id */
	public static final String TOM_CONTEXT_TYPE= "fr.loria.eclipse.jtom.template.tom";

	/**
	 * Creates a new Tom context type. 
	 */
	public TomContextType() {
		addGlobalResolvers();
	}

	private void addGlobalResolvers() {
		addResolver(new GlobalTemplateVariables.Cursor());
	}

} //class TomContextType