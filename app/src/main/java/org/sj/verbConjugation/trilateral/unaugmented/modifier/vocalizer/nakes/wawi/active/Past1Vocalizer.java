package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.*;
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult;

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
public class Past1Vocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public Past1Vocalizer() {
        substitutions.add(new SuffixSubstitution("َوَ","َا"));// EX: (غزا، أسا، عثا)
        substitutions.add(new InfixSubstitution("َوُوا","َوْا"));// EX: (غزَوْا، أسوا، عَثَوْا)
        substitutions.add(new InfixSubstitution("َوَت","َت"));// EX: (غَزَتْ، غَزَتَا، أسَتْ، أسَتَا، عَثَتْ، عَثَتَا)



    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());
        return (kov == 21 || kov == 23) && (noc == 1 || noc == 3);
    }
}
