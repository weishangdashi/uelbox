/*
 *  Copyright the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package therian.operator.convert;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import therian.TherianContext;
import therian.Operator.DependsOn;
import therian.buildweaver.StandardOperator;
import therian.operation.Convert;
import therian.operator.getelementtype.GetEnumerationElementType;
import therian.operator.getelementtype.GetIterableElementType;

/**
 * Implements simple conversion of a compatible {@link Enumeration} to a {@link List}.
 */
@SuppressWarnings("rawtypes")
@StandardOperator
@DependsOn({ GetEnumerationElementType.class, GetIterableElementType.class })
public class EnumerationToList extends AssignableElementConverter<Enumeration<?>, List> {
    public EnumerationToList() {
        super(Enumeration.class.getTypeParameters()[0], List.class.getTypeParameters()[0]);
    }

    @Override
    public boolean perform(TherianContext context, Convert<? extends Enumeration<?>, ? super List> operation) {
        final List<Object> result = new ArrayList<Object>();
        for (Enumeration<?> e = operation.getSourcePosition().getValue(); e != null && e.hasMoreElements();) {
            result.add(e.nextElement());
        }
        operation.getTargetPosition().setValue(result);
        return true;
    }

}
