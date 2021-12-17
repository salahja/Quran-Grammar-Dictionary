package org.sj.nounConjugation.trilateral.unaugmented.instrumental.standard;

import org.sj.nounConjugation.NounFormula;
import org.sj.verbConjugation.trilateral.unaugmented.*;
import org.sj.verbConjugation.util.*;

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
public class NounFormula4 extends NounFormula{

    public NounFormula4(UnaugmentedTrilateralRoot root, String suffix) {
        super(root, suffix);
    }

    public String form() {
        if (suffixNo % 2 == 0)
            return root.getC1()+ArabCharUtil.FATHA+root.getC2()+ArabCharUtil.SHADDA+ArabCharUtil.FATHA+ArabCharUtil.Aleph+root.getC3()+suffix;
        return "";
    }

    public String getFormulaName() {
        return "فَعَّالَة";
    }


}
