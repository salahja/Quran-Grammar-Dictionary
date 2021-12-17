package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei;

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
public class ImperativeAjwafYaeiVocalizer extends AbstractAjwafYaeiVocalizer {
    private List substitutions = new LinkedList();

    public ImperativeAjwafYaeiVocalizer() {
        substitutions.add(new ExpressionInfixSubstitution("اC1ْيِC3ُ", "C1ِيC3ُ")); // EX: (بِيعُوا، بِيعُنَّ)
        substitutions.add(new ExpressionInfixSubstitution("اC1ْيِC3ِ", "C1ِيC3ِ")); // EX: (بيعي، بيعِنَّ)
        substitutions.add(new ExpressionInfixSubstitution("اC1ْيِC3َ", "C1ِيC3َ")); // EX: (بيعا، بيعانَّ)
        substitutions.add(new ExpressionInfixSubstitution("اC1ْيِC3ْ", "C1ِC3ْ")); // EX: (بِعْ، بِعْنانِّ)
        substitutions.add(new ExpressionInfixSubstitution("اC1ْيِC3ّ", "C1ِC3ّ")); // EX: (أنتن لِنَّ)
    }

    public List getSubstitutions() {
        return substitutions;
    }

}
