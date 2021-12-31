package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic;

import java.util.*;
import org.sj.verbConjugation.trilateral.Substitution.*;


public class ImperativeGeminator extends SubstitutionsApplier {

    private List substitutions = new LinkedList();

    public ImperativeGeminator() {

        substitutions.add(new ExpressionInfixSubstitution("C1ْC3ِC3ُ", "C1ِC3ُّ")); // EX: (أَتِمُّوا)
        substitutions.add(new ExpressionInfixSubstitution("C1ْC3ِC3َ", "C1ِC3َّ")); // EX: (أَتِمَّا)
        substitutions.add(new ExpressionInfixSubstitution("C1ْC3ِC3ِ", "C1ِC3ِّ")); // EX: (أَتِمِّي)
        substitutions.add(new ExpressionSuffixSubstitution("C1ْC3ِC3ْ", "C1ِC3َّ")); // EX: (أَتِمَّ)
        substitutions.add(new ExpressionInfixSubstitution("C3ِC3ُ", "C3ُّ")); // EX: (انقَضُّوا، حاجُّوا)
        substitutions.add(new ExpressionInfixSubstitution("C3ِC3َ", "C3َّ")); // EX: (انْقَضَّا، حاجَّا)
        substitutions.add(new ExpressionInfixSubstitution("C3ِC3ِ", "C3ِّ")); // EX: (انْقَضِّي، حاجِّي)
        substitutions.add(new ExpressionSuffixSubstitution("C3ِC3ْ", "C3َّ")); // EX: (انْقَضَّ، حاجَّ)
        substitutions.add(new ExpressionSuffixSubstitution("اC3َC3ْ", "اC3َّ")); // EX: (تآجَّ)
        substitutions.add(new ExpressionInfixSubstitution("اC3َC3ْن","اC3َC3ْن"));// EX: (تآجَجْنَ)
        substitutions.add(new ExpressionInfixSubstitution("اC3َC3", "اC3ّ")); // EX: (تآجُّوا، تآجَّا، تآجِّي)
    }


    public List getSubstitutions() {
        return substitutions;
    }
}
