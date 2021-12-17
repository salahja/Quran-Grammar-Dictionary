package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.*;


public class PastMahmouz extends AbstractLamMahmouz {

    private List substitutions = new LinkedList();

    public PastMahmouz() {
        substitutions.add(new SuffixSubstitution("يءَ", "يءَ")); // EX: (أُسِيءَ، )
        substitutions.add(new InfixSubstitution("يء", "يئ")); // EX: (أسِيئَا، أُسِيئُوا)
        substitutions.add(new InfixSubstitution("ُءْ", "ُؤْ")); // EX: (استُؤْتُ، )
        substitutions.add(new InfixSubstitution("ِء", "ِئ")); // EX: (أُجْزِئَ، كُوفِئَ، انْفُقِئَ، ابتُدِئَ، تُدُورِئَ، استُهْزِئَ، احْزُوزِئَ، )
        substitutions.add(new InfixSubstitution("ِّء", "ِّئ")); // EX: (جُزِّئَ، تُخُبِّئَ، )
    }


    public List getSubstitutions() {
        return substitutions;
    }
}
