package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi;

import java.util.List;
import java.util.LinkedList;
import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.*;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description:فحص الأجوف حسب قائمة    </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class PassivePresentAjwafWawiListedVocalizer extends AbstractAjwafWawiListedVocalizer {
    private List substitutions = new LinkedList();

    public PassivePresentAjwafWawiListedVocalizer() {

        substitutions.add(new ExpressionInfixSubstitution("ْوَC3ُ","َاC3ُ"));// EX: (يُقام، يُخاف، لن تُخافُوا، يُداء)
        substitutions.add(new ExpressionInfixSubstitution("ْوَC3ِ","َاC3ِ"));// EX: (تُقامين، تُخافين، لن تُخافي، )
        substitutions.add(new ExpressionInfixSubstitution("ْوَC3َ","َاC3َ"));// EX: (تُقامان، تُخافان، لن يُخافَ، يُخافَنَّ)
        substitutions.add(new ExpressionInfixSubstitution("ْوَC3ْ","َC3ْ"));// EX: (تُقَمْنَ، تُخَفْنَ، لم تُخَفْ)
        substitutions.add(new ExpressionInfixSubstitution("ْوَC3ّ","َC3ّ"));// EX: (أنتن تُصَنَّ، هن يُصَنَّ)

    }

    public List getSubstitutions() {
        return substitutions;
    }


}
