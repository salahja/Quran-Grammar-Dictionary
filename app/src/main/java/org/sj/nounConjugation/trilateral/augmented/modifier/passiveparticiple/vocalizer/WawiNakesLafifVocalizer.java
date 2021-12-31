package org.sj.nounConjugation.trilateral.augmented.modifier.passiveparticiple.vocalizer;

import java.util.*;

import org.sj.nounConjugation.*;
import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier;


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
public class WawiNakesLafifVocalizer extends TrilateralNounSubstitutionApplier implements IAugmentedTrilateralModifier {

    private List substitutions = new LinkedList();

    public WawiNakesLafifVocalizer() {
        substitutions.add(new InfixSubstitution("َوَة","َاة"));// EX: (مُدْناةٌ،)
        substitutions.add(new InfixSubstitution("َوَت","َات"));// EX: (مُدْناتان، مُدْناتَيْنِ)
        substitutions.add(new InfixSubstitution("َوَا","َيَا"));// EX: (مُدْنَيَانِ، مُدْنَيَاتٌ)
        substitutions.add(new InfixSubstitution("َوَي","َيَي"));// EX: (مُدْنَيَيْنِ،)
        substitutions.add(new InfixSubstitution("َوُو","َوْ"));// EX: (مُدْنَوْنَ)
        substitutions.add(new InfixSubstitution("َوِي","َيْ"));// EX: (مُدْنَيْنَ)
        substitutions.add(new SuffixSubstitution("َوُ","َى"));// EX: (هذا المُدْنَى،)
        substitutions.add(new SuffixSubstitution("َوَ","َى"));// EX: (رأيتُ المُدْنَى ،  )
        substitutions.add(new SuffixSubstitution("َوِ","َى"));// EX: (مررتُ على المُدْنَى ، )
        substitutions.add(new InfixSubstitution("َوٌ","ًى"));// EX: (هذا مُدْنًى)
        substitutions.add(new InfixSubstitution("َوًا","ًى"));// EX: (رأيتُ مُدْنًى)
        substitutions.add(new InfixSubstitution("َوٍ","ًى"));// EX: (مررتُ على مُدْنًى)


    }

    public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
        if (mazeedConjugationResult.getRoot().getC3() != 'و')
            return false;

        int kov = mazeedConjugationResult.getKov();
        int formulaNo = mazeedConjugationResult.getFormulaNo();

        switch (kov) {
            case 21:
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

            case 22:
                switch (formulaNo) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                    case 7:
                    case 8:
                        return true;
                }

            case 23:
                switch (formulaNo) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
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
                    case 11:
                        return true;
                }
        }
        return false;
    }

    public List getSubstitutions() {
        return substitutions;
    }
}
