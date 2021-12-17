package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.vocalizer;

import java.util.*;
import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.*;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralNounModificationApplier;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.ConjugationResult;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description:فحص الأجوف حسب قائمة    </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class Ajwaf3WawiListedVocalizer extends AbstractAjwafWawiListedVocalizer implements IUnaugmentedTrilateralNounModificationApplier{
    private List substitutions = new LinkedList();

    public Ajwaf3WawiListedVocalizer() {
        substitutions.add(new InfixSubstitution("اوِ","ائِ"));// EX: (قائِمٌ)
    }

    public List getSubstitutions() {
        return substitutions;
    }

    protected static List appliedProunounsIndecies = new ArrayList(18);
    static {
        for (int i=0; i<18; i++) {
            appliedProunounsIndecies.add(i+1 +"");
        }
    }

    protected List getAppliedPronounsIndecies() {
        return appliedProunounsIndecies;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        return super.isApplied(conjugationResult);
    }
}
