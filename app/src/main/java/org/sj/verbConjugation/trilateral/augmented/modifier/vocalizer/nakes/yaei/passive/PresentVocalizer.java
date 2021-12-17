package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.passive;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;


public class PresentVocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public PresentVocalizer() {
        substitutions.add(new SuffixSubstitution("َيُ","َى"));// EX: (يُهْدَى، يُرَقَّى، يُجارَى، يُنثنَى، يُكتفى، يُتناسى، يُترقَّى، يُستغنى، يُعرورى، يُجْأوَّى)
        substitutions.add(new SuffixSubstitution("يَ", "ى")); // EX: (لن يُهْدَى، يُرَقَّى، يُجارَى، يُنثنَى، يُكتفى، يُتناسى، يُترقَّى، يُستغنى، يُعرورى، يُجْأوَّى)
        substitutions.add(new SuffixSubstitution("يْ", "")); // EX: (لم يُهْدَ، يُرَقَّ، يُجارَ، يُنثنَ، يُكتف، يُتناس، يُترقَّ، يُستغن، يُعرور، يُجْأوَّ)
        substitutions.add(new InfixSubstitution("يِي", "يْ")); // EX: (أنتِ تُهْدَيْنَ، تُرَقَّيْنَ، تُجارَيْنَ، تُنثنَيْنَ، تُكتفيْنَ، تُتناسَيْنَ، تُترقَّيْنَ، تُستغنَيْنَ، تُعرورَيْنَ، تُجْأوَّيْنَ)
        substitutions.add(new InfixSubstitution("يُو", "وْ")); // EX: (أنتم تُهْدَوْنَ، تُرَقَّوْنَ، تُجارَوْنَ، تُنثنَوْنَ، تُكتفوْنَ، تُتناسَوْنَ، تُترقَّوْنَ، تُستغنَوْنَ، تُعرورَوْنَ، تُجْأوَّوْنَ)
        substitutions.add(new InfixSubstitution("يُنَّ","وُنَّ"));// EX: (أنتن تُهْدَوُنَّ، تُرَقَّوُنَّ، تُجارَوُنَّ، تُنثنَوُنَّ، تُكتفوُنَّ، تُتناسَوُنَّ، تُترقَّوُنَّ، تُستغنَوُنَّ، تُعرورَوُنَّ، تُجْأوَّوُنَّ)
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
        case 24:
            switch (formulaNo) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:
            case 8:
            case 9:
                return true;
            }

        case 25:
            switch (formulaNo) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 11:
                return true;
            }

        case 26:
            switch (formulaNo) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return true;
            }

        }
        return false;
    }
}
