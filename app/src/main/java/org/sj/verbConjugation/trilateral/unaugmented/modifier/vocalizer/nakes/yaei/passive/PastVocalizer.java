package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.passive;

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
public class PastVocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public PastVocalizer() {
        substitutions.add(new InfixSubstitution("ِيْ", "ِي"));
        substitutions.add(new InfixSubstitution("ِيُ", "ُ"));
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());
        return ((kov == 24 || kov == 26) && (noc == 2)) ||
                ((kov == 24 || kov == 25 || kov == 26) && (noc == 3 || noc == 4));
    }
}
