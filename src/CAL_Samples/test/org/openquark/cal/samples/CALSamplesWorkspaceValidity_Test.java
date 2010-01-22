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
 * CALSamplesWorkspaceValidity_Test.java
 * Creation date: Oct 20, 2006.
 * By: Joseph Wong
 */
package org.openquark.cal.samples;

import junit.framework.TestCase;

import org.openquark.cal.services.CALServicesTestUtilities;


/**
 * This tests whether the workspaces in this project can compile.
 * @author Edward Lam
 */
public class CALSamplesWorkspaceValidity_Test extends TestCase {
    
    public CALSamplesWorkspaceValidity_Test(String name) {
        super(name);
    }
    
    public CALSamplesWorkspaceValidity_Test() {
        super("");
    }

    public void testBaseWorkspace() {
        CALServicesTestUtilities.testWorkspaceValidity("cal.samples.base.cws");
    }
    public void testSrcWorkspace() {
        CALServicesTestUtilities.testWorkspaceValidity("cal.samples.cws");
    }
    public void testTestWorkspace() {
        CALServicesTestUtilities.testWorkspaceValidity("cal.samples.test.cws");
    }
}