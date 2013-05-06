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
package therian.operator.getelementtype;

import java.util.Map;

import org.apache.commons.lang3.reflect.TypeUtils;

import therian.Operator;
import therian.TherianContext;
import therian.buildweaver.StandardOperator;
import therian.operation.GetElementType;
import therian.util.Types;

@SuppressWarnings("rawtypes")
@StandardOperator
public class GetMapElementType implements Operator<GetElementType<Map>> {

    @Override
    public boolean perform(TherianContext context, GetElementType<Map> op) {
        op.setResult(Types.unrollVariables(TypeUtils.getTypeArguments(op.getTypedItem().getType(), Map.class),
            Map.class.getTypeParameters()[1]));
        return true;
    }

    @Override
    public boolean supports(TherianContext context, GetElementType<Map> op) {
        return true;
    }

}
