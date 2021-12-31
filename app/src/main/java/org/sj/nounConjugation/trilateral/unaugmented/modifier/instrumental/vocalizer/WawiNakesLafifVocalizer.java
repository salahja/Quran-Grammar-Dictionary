package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.vocalizer;

import java.util.*;

import org.sj.nounConjugation.*;
import org.sj.nounConjugation.trilateral.unaugmented.modifier.*;
import org.sj.verbConjugation.trilateral.Substitution.*;

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
public class WawiNakesLafifVocalizer extends TrilateralNounSubstitutionApplier implements IUnaugmentedTrilateralNounModificationApplier {
    List substitutions = new LinkedList();

    public WawiNakesLafifVocalizer() {
        substitutions.add(new SuffixSubstitution("َوُ", "َى")); // EX: (هذا المِغْزَى )
        substitutions.add(new SuffixSubstitution("َوَ", "َى")); // EX: (رأيت المِغْزَى )
        substitutions.add(new SuffixSubstitution("َوِ", "َى")); // EX: (كالمِغْزَى )
        substitutions.add(new InfixSubstitution("َوٌ", "ًى")); // EX: (هذا مِغْزًى )
        substitutions.add(new InfixSubstitution("َوًا", "ًى")); // EX: (رأيت مِغْزًى )
        substitutions.add(new InfixSubstitution("َوٍ", "ًى")); // EX: (كَمِغْزًى )
        substitutions.add(new InfixSubstitution("اوًا", "اءً")); // EX: (مِغْزاءً)
        substitutions.add(new InfixSubstitution("َوَة", "َاة")); // EX: (مِغْزاة )
        substitutions.add(new InfixSubstitution("َوَت", "َات")); // EX: (مِغْزاتان )
        substitutions.add(new InfixSubstitution("َوَ", "َيَ")); // EX: (مِغْزَيان )
        substitutions.add(new InfixSubstitution("او","اء"));// EX: (مِغْزاء )
    }


    public List getSubstitutions() {
        return substitutions;
    }

    public boolean isApplied(ConjugationResult conjugationResult) {
        if (conjugationResult.getRoot().getC3() != 'و')
            return false;

        int kov = conjugationResult.getKov();
        int noc = Integer.parseInt(conjugationResult.getRoot().getConjugation());

        switch (kov) {
        case 21:
            return noc == 1 || noc == 5;
        case 22:
            return noc == 1 || noc == 3;
        case 23:
            switch (noc) {
            case 1:
            case 3:
            case 4:
            case 5:
                return true;
            }

        case 24:
        case 26:
            switch (noc) {
            case 2:
            case 3:
            case 4:
                return true;
            }

        case 25:
            return noc == 3 || noc == 4;

        case 27:
        case 29:
            return noc == 2;
        case 28:
            return noc == 2 || noc == 4;
        case 30:
            switch (noc) {
            case 2:
            case 4:
            case 6:
                return true;
            }

        }
        return false;
    }

}
