package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;
import org.sj.verbConjugation.trilateral.augmented.*;


public class Present2Vocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public Present2Vocalizer() {
        substitutions.add(new SuffixSubstitution("َيُ","َى"));// EX: (يُذْوَى، يُقَوَّى، يُداوَى، يُنْزَوَى، يُحْتَوَى، يُتَدَاوَى، يُتَقَوَّى، يُسْتَهْوَى)
        substitutions.add(new SuffixSubstitution("يَ","ى"));// EX: (لن يُذْوَى، يُقَوَّى، يُداوَى، يُنْزَوَى، يُحْتَوَى، يُتَدَاوَى، يُتَقَوَّى، يُسْتَهْوَى)
        substitutions.add(new SuffixSubstitution("يْ",""));// EX: (لم يُذْوَ، يُقَوَّ، يُداوَ، يُنْزَوَ، يُحْتَوَ، يُتَدَاوَ، يُتَقَوَّ، يُسْتَهْوَ)
        substitutions.add(new InfixSubstitution("يِي","يْ"));// EX: (أنتِ تُذْوَيْنَ، تُقَوَّيْنَ، تداوَيْنَ، تنْزَوَيْنَ، تحتوين، تتداوين، تتقوين، تستهوين)
        substitutions.add(new InfixSubstitution("يُو","وْ"));// EX: (أنتم تُذْوَوْنَ، تُقَوَّوْنَ، تداوَوْنَ، تنْزَوَوْنَ، تحتوَوْنَ، تتداوَوْنَ، تتقوَّوْن، تُسْتَهْوَوْنَ)
        substitutions.add(new InfixSubstitution("يُنّ","وُنّ"));// EX: (أنتم تُذْوَوُنَّ، تُقَوَّوُنَّ، تداوَوُنَّ، تنْزَوَوُنَّ، تحتوَوُنَّ، تتداوَوُنَّ، تتقوَّوُنَّ، تُسْتَهْوَوُنَّ)

    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();
        AugmentedTrilateralRoot root = mazeedConjugationResult.getRoot();
        if (root.getC2() == 'و' && root.getC3() == 'ي') {
            switch (kov) {
                case 27:
                    switch (formulaNo) {
                        case 1:
                        case 2:
                        case 5:
                        case 7:
                        case 8:
                        case 9:
                            return true;
                    }

                case 28:
                    switch (formulaNo) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 7:
                        case 8:
                        case 9:
                            return true;
                    }
            }
        }


        return false;
    }
}
