package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic;

import java.util.*;
import org.sj.verbConjugation.trilateral.Substitution.*;



public class PassivePresentGeminator extends SubstitutionsApplier {

    private List substitutions = new LinkedList();

    public PassivePresentGeminator() {

        substitutions.add(new ExpressionInfixSubstitution("ْC3َC3ُ","َC3ُّ"));// EX: (يُتَمُّ،)
        substitutions.add(new ExpressionInfixSubstitution("ْC3َC3َ","َC3َّ"));// EX: (يُتَمَّان،)
        substitutions.add(new ExpressionInfixSubstitution("ْC3َC3ِ","َC3ِّ"));// EX: (تُتَمِّي،)
        substitutions.add(new ExpressionSuffixSubstitution("ْC3َC3ْ","َC3َّ"));// EX: (لم يُتَمَّ،)
        substitutions.add(new ExpressionSuffixSubstitution("َC3َC3ْ","َC3َّ"));// EX: (لم يُنْقَضَّ، يُشْتَدَّ)
        substitutions.add(new ExpressionInfixSubstitution("اC3َC3ُ","اC3ُّ"));// EX: (يُتَصَامُّ، يُحاجّ)
        substitutions.add(new ExpressionInfixSubstitution("اC3َC3َ","اC3َّ"));// EX: (يُتَصَامَّا، يحاجّا)
        substitutions.add(new ExpressionInfixSubstitution("اC3َC3ِ","اC3ِّ"));// EX: (تُتَصَامِّي، تحاجِي)
        substitutions.add(new ExpressionSuffixSubstitution("اC3َC3ْ","اC3َّ"));// EX: (لم يُتَصَامَّ، يحاجَّ)
        substitutions.add(new ExpressionInfixSubstitution("َC3َC3ُ","َC3ُّ"));// EX: (يُحْمَرُّ، يُقْتَضُّ)
        substitutions.add(new ExpressionInfixSubstitution("َC3َC3َ","َC3َّ"));// EX: (لن يُحْمَرَّ، لن يُنْقَضَّ، لن يُشتدَّ)
        substitutions.add(new ExpressionInfixSubstitution("َC3َC3ِ","َC3ِّ"));// EX: (تُنقَضِّين)

    }

    public List getSubstitutions() {
        return substitutions;
    }
}
