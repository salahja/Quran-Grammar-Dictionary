package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.*;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class ActivePastAjwafWawiVocalizer extends AbstractAjwafWawiVocalizer {
    private List substitutions = new LinkedList();

    public ActivePastAjwafWawiVocalizer() {
        substitutions.add(new ExpressionInfixSubstitution("َوَC3ْ", "ُC3ْ")); // EX: (قُمْتُ، بُؤْتُ، أُبْتُ)
        substitutions.add(new ExpressionInfixSubstitution("َوَC3ّ", "ُC3ّ")); // EX: (نحن صُنَّا، هنّ صُنَّ، أنا فُتُّ)
        substitutions.add(new ExpressionInfixSubstitution("َوَC3َ", "َاC3َ")); // EX: (قام، باء، آب)
        substitutions.add(new ExpressionInfixSubstitution("َوَC3ُ", "َاC3ُ")); // EX: (قاموا، باؤوا، آبوا)

    }

    /**
     *
     * @return List
     * @todo Implement this
     *   org.sj.verb.trilateral.Substitution.SubstitutionsApplier method
     */
    public List getSubstitutions() {
        return substitutions;
    }
}
