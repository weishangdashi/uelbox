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
package therian.operator.add;

import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.commons.lang3.reflect.TypeUtils;

import therian.TherianContext;
import therian.operation.Add;
import therian.operation.ImmutableCheck;
import therian.util.Types;

/**
 * Add an element to a {@link Collection}.
 */
public class AddToCollection implements therian.Operator<Add<?, Collection<?>>> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void perform(Add<?, Collection<?>> operation) {
        operation.setResult(((Collection) operation.getTargetPosition().getValue()).add(operation.getSourcePosition()
            .getValue()));
        operation.setSuccessful(true);
    }

    public boolean supports(Add<?, Collection<?>> operation) {
        // cannot add to immutable types
        if (Boolean.TRUE.equals(TherianContext.getRequiredInstance().eval(
            ImmutableCheck.of(operation.getTargetPosition())))) {
            return false;
        }
        if (!TypeUtils.isAssignable(operation.getTargetPosition().getType(), Collection.class)) {
            return false;
        }
        final Type targetElementType =
            Types.unrollVariables(TypeUtils.getTypeArguments(operation.getTargetPosition().getType(), Collection.class), Collection.class.getTypeParameters()[0]);

        if (targetElementType == null) {
            // raw collection
            return true;
        }
        return TypeUtils.isAssignable(operation.getSourcePosition().getType(), targetElementType);
    }

}