/*
 * Copyright (c) 2007 BUSINESS OBJECTS SOFTWARE LIMITED
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *  
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *  
 *     * Neither the name of Business Objects nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */


/*
 * ResourceAccess.java
 * Creation date: May 31, 2006.
 * By: Joseph Wong
 */
package org.openquark.cal.runtime;

import java.io.InputStream;
import java.util.Locale;

/**
 * This interface represents the mechanism through which the CAL runtime is to access resources in the
 * current environment (e.g. from the workspace, or from Eclipse). On the CAL side, access to resources is
 * provided by the Resource module.
 *
 * @author Joseph Wong
 */
public interface ResourceAccess {

    /**
     * Returns an InputStream for the named user resource in the specified locale. Note that this
     * method does not implement any locale-fallback mechanism - it is up to the caller to do so.
     * 
     * @param moduleNameAsString the name of the module associated with the user resource.
     * @param name the name of the resource, not including any file extensions. Cannot contain the character '_'.
     * @param extension the file extension for the user resource.
     * @param locale the locale for which the resource is to be fetched.
     * @return an InputStream for the user resource, or null if the resource cannot be found.
     */
    public InputStream getUserResource(String moduleNameAsString, String name, String extension, Locale locale);
}