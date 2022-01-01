package org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace;

import java.util.*;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.timeandplace.vocalizer.*;
import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult;
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
public class Vocalizer {
    private List modifiers = new LinkedList();

    public Vocalizer() {
        modifiers.add(new ACAjwaf1Vocalizer());
        modifiers.add(new ACAjwaf2Vocalizer());
        modifiers.add(new BAjwafVocalizer());
        modifiers.add(new ALafifNakesVocalizer());
        modifiers.add(new CLafifNakesVocalizer());
    }

    public void apply(ConjugationResult conjResult) {
        Iterator iter = modifiers.iterator();
        while (iter.hasNext()) {
            IUnaugmentedTrilateralNounModificationApplier modifier = (IUnaugmentedTrilateralNounModificationApplier) iter.next();
            if (modifier.isApplied(conjResult)) {
                ((SubstitutionsApplier) modifier).apply(conjResult.getFinalResult(), conjResult.getRoot());
                break;
            }
        }
    }
}
