package org.sj.verbConjugation.trilateral.augmented.modifier.substituter.passive;

import org.sj.verbConjugation.trilateral.Substitution.*;
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult;
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier;


import java.util.LinkedList;
import java.util.List;

public class SpecialSubstituter2 extends SubstitutionsApplier implements IAugmentedTrilateralModifier {
  private List substitutions = new LinkedList();

  public SpecialSubstituter2() {
    substitutions.add(new InfixSubstitution("يْت","تّ"));// EX: (اتُّسِرَ)




  }

  public List getSubstitutions() {
    return substitutions;
  }

  public boolean isApplied(MazeedConjugationResult mazeedConjugationResult) {
    int kov = mazeedConjugationResult.getKov();
    int formulaNo = mazeedConjugationResult.getFormulaNo();
    return mazeedConjugationResult.getRoot().getC1() == 'ي' && formulaNo == 5 && (kov == 13 || kov == 14);
  }
}
