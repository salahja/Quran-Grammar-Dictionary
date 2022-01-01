package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.*;


public class PastMahmouz extends AbstractEinMahmouz {

    private List substitutions = new LinkedList();

    public PastMahmouz() {
        substitutions.add(new InfixSubstitution("ءِ", "ئِ")); // EX: (أُسْئِمَ، لُوئِمَ، انْذُئِجَ، ابتُئِسَ، تُسُوئِلَ، استُرئِفَ، اتُّئِدَ، )
        substitutions.add(new InfixSubstitution("ْءُ", "ْؤُ")); // EX: (اجْؤُلَّ، )
        substitutions.add(new InfixSubstitution("ُءُ","ُؤُ"));// EX: (ارتُؤُوا، )
        substitutions.add(new InfixSubstitution("ُءِّ", "ُئِّ")); // EX: (رُئِّسَ، تُرُئِّفَ، )
        substitutions.add(new InfixSubstitution("ُءُّ", "ُؤُّ")); // EX: (رُؤُّوا، )
    }

    public List getSubstitutions() {
        return substitutions;
    }
}
