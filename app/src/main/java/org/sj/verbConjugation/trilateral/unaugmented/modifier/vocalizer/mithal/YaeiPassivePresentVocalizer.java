package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal;

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
public class YaeiPassivePresentVocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public YaeiPassivePresentVocalizer() {
        substitutions.add(new InfixSubstitution("ُيْ","ُو"));// EX: (يُوسَرُ، يوقَظُ، )
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());
        return (kov == 12  && (noc == 2 || noc == 4)) ||
                (kov == 13  && (noc == 4 || noc == 6)) ||
                (kov == 14 && (noc == 1 || noc == 2 || noc == 3 || noc == 4 || noc == 5 || noc == 6));

    }
}
