package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam;

import java.util.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.*;
import org.sj.verbConjugation.trilateral.Substitution.*;

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
public class ActivePastMahmouz extends AbstractLamMahmouz {
    private List substitutions = new ArrayList();

    public ActivePastMahmouz() {
        substitutions.add(new InfixSubstitution("َءْ", "َأْ")); // EX: (بَدأتُ، وجأتُ)
        substitutions.add(new InfixSubstitution("َءَ", "َأَ")); // EX: (بَدَأَ، وَجَأَ)
        substitutions.add(new InfixSubstitution("َءُ", "َؤُ")); // EX: (بَدَؤُوا،)
        substitutions.add(new InfixSubstitution("ِء", "ِئ")); // EX: (ظَمِئَ، ظَمِئْتُ، ظَمِئُوا، وَطِئَ، هَوِئَ، قِئْتُ)
        substitutions.add(new InfixSubstitution("ُء", "ُؤ")); // EX: (جَرُؤَ، جَرُؤْتُ، جَرُؤُوا، وَضُؤَ، بُؤْتُ، )
        substitutions.add(new InfixSubstitution("اءُ", "اؤُ")); // EX: (باؤوا، قاؤوا )
    }

    public List getSubstitutions() {
        return substitutions;
    }
}
