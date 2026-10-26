import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// [Requirement 2] Custom annotation @Id (Marker)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {}

// [Requirement 2] Custom annotation @Positive with default message
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Positive {
    String message() default "must be > 0";
}

// [Requirement 2] Custom annotation @MaxLength
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value() default 50;
}

class Account7 {
    // [Requirement 3] Annotate accountNumber with @Id and balance with @Positive
    @Id
    private String accountNumber;

    @Positive
    private long balance;

    public Account7(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

// [Requirement 4] AnnotationValidator using reflection
class AnnotationValidator {
    public static String[] validate(Object obj) {
        List<String> errors = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.isAnnotationPresent(Positive.class)) {
                    long val = f.getLong(obj);
                    if (val <= 0) {
                        Positive p = f.getAnnotation(Positive.class);
                        errors.add("Field '" + f.getName() + "': " + p.message());
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Access error: " + f.getName());
            }
        }
        return errors.toArray(new String[0]);
    }
}

public class MiniBankPart7 {
    public static void main(String[] args) {
        System.out.println("=== Reflective Annotation Validator ===");
        // [Requirement 5] Account with negative balance triggers @Positive error
        Account7 badAcc = new Account7("AC999", -500);
        System.out.println("Validating Account with Invalid Balance (-500):");
        for (String err : AnnotationValidator.validate(badAcc)) {
            System.out.println("-> [VALIDATION ERROR] " + err);
        }

        // Valid account passes with 0 errors
        Account7 goodAcc = new Account7("AC001", 15000);
        System.out.println("\nValidating Account with Valid Balance (15000):");
        String[] errs = AnnotationValidator.validate(goodAcc);
        if (errs.length == 0) {
            System.out.println("-> All reflection annotation checks passed! (0 errors)");
        }
    }
}