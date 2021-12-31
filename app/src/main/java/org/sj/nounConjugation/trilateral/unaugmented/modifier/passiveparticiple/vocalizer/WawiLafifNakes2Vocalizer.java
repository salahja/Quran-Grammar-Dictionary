package org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.vocalizer;

import java.util.*;

import org.sj.nounConjugation.*;

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
public class WawiLafifNakes2Vocalizer extends TrilateralNounSubstitutionApplier implements IUnaugmentedTrilateralNounModificationApplier {
    List substitutions = new LinkedList();

    public WawiLafifNakes2Vocalizer() {
        substitutions.add(new InfixSubstitution("ُوو","ِيّ"));// EX: (مَرْضِيّ , مسوِيٌّ)
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        if (conjugationResult.getRoot().getC3() != 'و')
            return false;

        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());

        return (kov == 23 || kov == 28) && noc == 4;
    }
}
