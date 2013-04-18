package therian.operator.getelementtype;

import java.util.Iterator;

import org.apache.commons.lang3.reflect.TypeUtils;

import therian.Operator;
import therian.TherianContext;
import therian.operation.GetElementType;
import therian.util.Types;

@SuppressWarnings("rawtypes")
public class GetIteratorElementType implements Operator<GetElementType<Iterator>> {

    public void perform(TherianContext context, GetElementType<Iterator> op) {
        op.setResult(Types.unrollVariables(TypeUtils.getTypeArguments(op.getTypeHost().getType(), Iterator.class),
            Iterator.class.getTypeParameters()[0]));
        op.setSuccessful(true);
    }

    public boolean supports(TherianContext context, GetElementType<Iterator> op) {
        return true;
    }

}
