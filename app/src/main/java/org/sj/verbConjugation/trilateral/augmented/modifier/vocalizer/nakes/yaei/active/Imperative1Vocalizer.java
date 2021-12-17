package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active;

import java.util.*;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.modifier.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;


public class Imperative1Vocalizer extends SubstitutionsApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public Imperative1Vocalizer() {

        substitutions.add(new SuffixSubstitution("يْ",""));// EX: (أهْدِ، جَارِ، انْثَنِ، اكْتَفِ، اسْتَغْنِِِِِِِِِ، اعْرَوْرِ)
        substitutions.add(new InfixSubstitution("يِي","ي"));// EX: (أنتِ أهْدِي، جَارِي، انْثَنِي، اكْتَفِي، استغْنِي، اعْرَوْرِي)
        substitutions.add(new InfixSubstitution("يِن","ن"));// EX: (أنتِ أهْدِنَّ، جَارِنَّ، انْثَنِنَّ، اكْتَفِنَّ، استغْنِنَّ، اعْرَوْرِنَّ)
        substitutions.add(new InfixSubstitution("يْن","ين"));// EX: (أنتن أهْدِينَ، جَارِينَ، انْثَنِينَ، اكْتَفِينَ، استغنِينَ، اعْرَوْرِي)
        substitutions.add(new InfixSubstitution("ِيُ","ُ"));// EX: (أنتم أهْدُوا، جَارُوا، انْثَنُوا، اكْتَفُوا، استغنُوا، اعْرَوْرُوا)

    }

    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
        case 26:
            switch (formulaNo) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 9:
            case 10:
                return true;
            }

        case 25:
            switch (formulaNo) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 9:
                return true;
            }

        case 24:
            switch (formulaNo) {
            case 1:
            case 3:
            case 5:
            case 9:
                return true;
            }
        }
        return false;
    }
}
