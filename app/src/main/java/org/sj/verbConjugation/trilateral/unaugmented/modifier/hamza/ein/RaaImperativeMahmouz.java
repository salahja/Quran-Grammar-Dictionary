package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier;
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult;
import org.sj.verbConjugation.trilateral.*;

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
public class RaaImperativeMahmouz extends SubstitutionsApplier implements IUnaugmentedTrilateralModifier {
    private List substitutions = new ArrayList();

    public RaaImperativeMahmouz() {
        substitutions.add(new InfixSubstitution("ارْءَ","رَ"));// EX: (رَ، رَيْ، رَيا، رَوْا، رَيْنَ، رَيَنَّ، رَيِنَّ، رَيانِّ، رَوُنَّ، رَيْنانِّ)
    }

    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        TrilateralRoot root = conjugationResult.getRoot();
        return root.getC1() == 'ر' && root.getC2() == 'ء' && root.getC3() == 'ي';
    }
}
