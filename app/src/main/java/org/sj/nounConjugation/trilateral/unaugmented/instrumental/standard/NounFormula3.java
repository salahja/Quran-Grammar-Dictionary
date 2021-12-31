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
public class NounFormula3 extends NounFormula{

    public NounFormula3(UnaugmentedTrilateralRoot root, String suffix) {
        super(root, suffix);
    }

    public String form() {
        switch (suffixNo) {
        case 1:
        case 3:
        case 7:
        case 9:
        case 13:
        case 15:
            return ArabCharUtil.MEEM+ArabCharUtil.KASRA+root.getC1()+ArabCharUtil.SKOON+root.getC2()+ArabCharUtil.FATHA+ArabCharUtil.Aleph+root.getC3()+suffix;
        }

        return "";
    }

    public String getFormulaName() {
        return "مِفْعَال";
    }


}
