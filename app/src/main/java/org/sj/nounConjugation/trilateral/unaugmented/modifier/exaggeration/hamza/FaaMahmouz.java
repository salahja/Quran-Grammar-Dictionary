package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.*;

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
public class FaaMahmouz extends AbstractFaaMahmouz {
    List substitutions = new LinkedList();

    public FaaMahmouz() {
        substitutions.add(new InfixSubstitution("َءَا", "َآ")); // EX: (مآنِيف،)
        substitutions.add(new PrefixSubstitution("الءَ","الأَ"));// EX: (الأَكَّال، الأكول،)
        substitutions.add(new PrefixSubstitution("الءُ","الأُ"));// EX: (الأُكَلَة،)
        substitutions.add(new PrefixSubstitution("ءَ", "أَ")); // EX: (أَكَّال، أكول،)
        substitutions.add(new PrefixSubstitution("ءُ", "أُ")); // EX: (أُكَلَة،)
        substitutions.add(new InfixSubstitution("ِءْ", "ِئْ")); // EX: (مِئْناف،)

    }


    public List getSubstitutions() {
        return substitutions;
    }
}
