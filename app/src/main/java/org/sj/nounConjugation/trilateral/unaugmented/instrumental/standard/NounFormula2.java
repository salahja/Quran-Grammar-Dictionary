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
public class NounFormula2 extends NounFormula {

    public NounFormula2(UnaugmentedTrilateralRoot root, String suffix) {
        super(root, suffix);
    }

    public String form() {
        switch (suffixNo) {
        case 2:
        case 4:
        case 8:
        case 10:
        case 14:
        case 16:
            return ArabCharUtil.MEEM + ArabCharUtil.KASRA + root.getC1() + ArabCharUtil.SKOON + root.getC2() + ArabCharUtil.FATHA + root.getC3() + suffix;
        }

        return "";
    }

    public String getFormulaName() {
        return "مِفْعَلَة";
    }

}
