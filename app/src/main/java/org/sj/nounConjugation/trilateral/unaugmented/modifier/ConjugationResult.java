package org.sj.nounConjugation.trilateral.unaugmented.modifier;

import java.util.*;

import org.sj.verbConjugation.trilateral.unaugmented.*;

/**
 * <p>Title: Sarf Program</p>
 *
 * <p>Description: يمثل نتيجة التصريف مع الجذر ونوع الجذر      </p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: ALEXO</p>
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
public class ConjugationResult extends org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult{
    private String nounFormula;

    public ConjugationResult(int kov, UnaugmentedTrilateralRoot root, List originalResult, String nounFormula) {
        super(kov, root, originalResult);
        this.nounFormula = nounFormula;
    }

    public String getNounFormula() {
        return nounFormula;
    }
}
