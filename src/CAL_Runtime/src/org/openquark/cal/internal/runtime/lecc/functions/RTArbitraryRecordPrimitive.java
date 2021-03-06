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
 * RTEqualsRecord.java
 * Created: Sept 28, 2006
 * By: Magnus Byne
 */
package org.openquark.cal.internal.runtime.lecc.functions;

import org.openquark.cal.internal.runtime.lecc.RTData;
import org.openquark.cal.internal.runtime.lecc.RTExecutionContext;
import org.openquark.cal.internal.runtime.lecc.RTRecordValue;
import org.openquark.cal.internal.runtime.lecc.RTResultFunction;
import org.openquark.cal.internal.runtime.lecc.RTSupercombinator;
import org.openquark.cal.internal.runtime.lecc.RTValue;
import org.openquark.cal.runtime.CALExecutorException;



/**
 * Implements the built-in primitive function: arbitraryRecordPrimitive ::
 * Arbitrary r => GenParams -> (GenParams -> GenParams) -> {r}
 *
 * @author Magnus Byne
 */
public final class RTArbitraryRecordPrimitive extends RTSupercombinator {

    /**
     * the index of the Arbitrary.generateInstance class method. This is currently
     * hard-coded. Changes to the type class would necessitate updating this
     * value.
     */
    private static final RTData.CAL_Int indexOfGenerateClassMethod = RTData.CAL_Int.make(2);

    public static final RTArbitraryRecordPrimitive $instance = new RTArbitraryRecordPrimitive();

    private RTArbitraryRecordPrimitive() {
        // Declared private to limit instantiation.
    }

    public static final RTArbitraryRecordPrimitive make(RTExecutionContext $ec) {
        return $instance;
    }

    @Override
    public final int getArity() {
        return 3;
    }

    @Override
    public final RTValue f(final RTResultFunction rootNode, final RTExecutionContext $ec) throws CALExecutorException {

        // Arguments
        RTValue independent = rootNode.getArgValue();
        RTValue currentRootNode;
        RTValue genParams = (currentRootNode = rootNode.prevArg()).getArgValue();
        RTValue recordDictionaryThunk = currentRootNode.prevArg().getArgValue();
        rootNode.clearMembers();

        return f3S(
                RTValue.lastRef(recordDictionaryThunk.evaluate($ec), recordDictionaryThunk = null),
                RTValue.lastRef(genParams.evaluate($ec), genParams = null),
                RTValue.lastRef(independent.evaluate($ec), independent = null),
                $ec);
    }

    @Override
    public final RTValue f3L(RTValue recordDictionary, RTValue genParams, RTValue independent, RTExecutionContext $ec) throws CALExecutorException {

        return f3S(
                RTValue.lastRef(recordDictionary.evaluate($ec), recordDictionary = null),
                RTValue.lastRef(genParams.evaluate($ec), genParams = null),
                RTValue.lastRef(independent.evaluate($ec), independent = null),
                $ec);
    }

    @Override
    public final RTValue f3S(RTValue recordDictionary, RTValue genParams, RTValue independent, RTExecutionContext $ec) throws CALExecutorException {
        $ec.incrementNMethodCalls();

        /*
         * Loops through all fields in the record dictionary and invokes the
         * arbitrary class method to construct each record field
         */

        RTRecordValue recordDict = (RTRecordValue)recordDictionary;

        int numOrdinalFields = recordDict.getNOrdinalFields();
        int numTextualFields = recordDict.getNTextualFields();

        RTValue ordinalValues[] = (numOrdinalFields > 0) ? new RTValue[numOrdinalFields] : null;
        RTValue textualValues[] = (numTextualFields > 0) ? new RTValue[numTextualFields] : null;

        for (int i = 0, nFields = numTextualFields + numOrdinalFields; i < nFields; ++i) {

            // update the genParams so that each field has an independent generator
            genParams = independent.apply(RTData.CAL_Int.make(i), genParams);
            RTValue fieldDict = recordDict.getNthValue(i);

            //generate an arbitrary instance of the field
            RTValue v = fieldDict.apply(indexOfGenerateClassMethod, genParams).evaluate($ec);

            if (i < recordDict.getNOrdinalFields()) {
                ordinalValues[i] = v;
            } else {
                textualValues[i - numOrdinalFields] = v;
            }
        }

        return recordDict.makeFromValues(ordinalValues, textualValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getModuleName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "Cal.Utilities.QuickCheck";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getUnqualifiedName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "arbitraryRecordPrimitive";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getQualifiedName() {
        //JUnit tested to equal its binding file value in RuntimeStringConstantsTest.
        return "Cal.Utilities.QuickCheck.arbitraryRecordPrimitive";
    }

}
