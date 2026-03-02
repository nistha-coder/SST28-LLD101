import java.util.*;
public class DisciplinaryRule implements EligibilityRule {

    @Override
    public void evaluate(StudentProfile s, List<String> reasons) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            reasons.add("disciplinary flag present");
        }
    } 
}