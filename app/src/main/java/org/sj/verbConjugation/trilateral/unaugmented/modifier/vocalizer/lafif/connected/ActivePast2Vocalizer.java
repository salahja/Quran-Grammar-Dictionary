package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected;

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
public class ActivePast2Vocalizer extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public ActivePast2Vocalizer() {
        substitutions.add(new InfixSubstitution("ِوْ", "ِي"));
        substitutions.add(new InfixSubstitution("ِوُ", "ُ"));
        substitutions.add(new InfixSubstitution("ِيْ", "ِي"));
        substitutions.add(new InfixSubstitution("ِيُ", "ُ"));
        substitutions.add(new InfixSubstitution("ِوَ","ِيَ"));

    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());
        return (kov == 28 && noc == 4);
    }
}
