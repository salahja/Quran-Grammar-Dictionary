package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.*;

   
public class ImperativeMahmouz extends AbstractLamMahmouz {

    private List substitutions = new LinkedList();

    public ImperativeMahmouz() {
        substitutions.add(new InfixSubstitution("يءَ","يئَ"));// EX: (أَسِيئَا، )
        substitutions.add(new InfixSubstitution("يءُ","يئُ"));// EX: (أَسِيئُوا، )
        substitutions.add(new InfixSubstitution("ِء","ِئ"));// EX: (أُجْزِئْ، كافِئْ، انْفَقِئْ، ابتَدِئْ، استهزِئْ، احْزَوْزِئْ، أَسِئْ، )
        substitutions.add(new InfixSubstitution("ءِ","ئِ"));// EX: (تكافئي )
        substitutions.add(new InfixSubstitution("ِّء","ِّئ"));// EX: (جَزِّئْ، )
        substitutions.add(new InfixSubstitution("َءْ","َأْ"));// EX: (تدارَأْ، تدارَأْنَ، اسْتَأْ)
        substitutions.add(new InfixSubstitution("َءَ","َأَ"));// EX: (تدارَأَا، )
        substitutions.add(new InfixSubstitution("َءُ","َؤُ"));// EX: (تدارَؤُوا، )
        substitutions.add(new InfixSubstitution("َّءْ","َّأْ"));// EX: (تَخَبَّأْ، تَخَبَّأْنَ، )
        substitutions.add(new InfixSubstitution("َّءَ","َّأَ"));// EX: (تَخَبَّأَا، )
        substitutions.add(new InfixSubstitution("َّءُ","َّؤُ"));// EX: (تَخَبَّؤُوا، )
        substitutions.add(new InfixSubstitution("اءُ","اؤُ"));// EX: (اسْتاؤُوا، )
    }


    public List getSubstitutions() {
        return substitutions;
    }
}
