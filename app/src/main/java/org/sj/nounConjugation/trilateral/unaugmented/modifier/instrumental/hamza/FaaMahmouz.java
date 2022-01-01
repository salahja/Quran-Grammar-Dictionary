package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.hamza;

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
        substitutions.add(new InfixSubstitution("ِء", "ِئ")); // EX: (مِئْزَر، مِئزرة، مِئزار)
        substitutions.add(new PrefixSubstitution("ءَ", "أَ")); // EX: (أزَّارة، )
        substitutions.add(new InfixSubstitution("الءَ","الأَ"));// EX: (أتَّاءَة، أثّاءة)
        substitutions.add(new InfixSubstitution("ءِ", "إِ")); // EX: (إزار، )
        substitutions.add(new InfixSubstitution("ءُ", "أُ")); // EX: (أُخْذَة، )
    }


    public List getSubstitutions() {
        return substitutions;
    }
}
