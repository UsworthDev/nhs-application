package com.nhs.demo.validator;

import com.nhs.demo.model.RegularAmount;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.BigDecimalValidator;
import java.math.RoundingMode;
import java.math.BigDecimal;
import ir.cafebabe.math.utils.BigDecimalUtils;
import org.pmw.tinylog.Logger;

public class ValidRegularAmountValidator
implements ConstraintValidator<ValidRegularAmount, RegularAmount> {
    @Override
    public void initialize(ValidRegularAmount constraintAnnotation) {
    }

    @Override
    public boolean isValid(RegularAmount regularAmount, ConstraintValidatorContext context) {
        if ( regularAmount == null )             
            return true;
        
        if (!isFrequencyAndAmountValid(regularAmount)) {
            Logger.info("regularAmount is invalid, returning false");

            return false;
        }
            
        Logger.info("regularAmount is valid, returning true");
        return true;        
    }

    private static boolean isFrequencyAndAmountValid(RegularAmount regularAmount) {
        
        if (isNullOrEmpty(regularAmount.getAmount())) {            
            return true;
        }

        if (regularAmount.getFrequency() == null) {
            return true;
        }            

        Logger.info("Checking regularAmount Frequency: {}, Amount: {}", regularAmount.getFrequency(), regularAmount.getAmount());

        var weeklyAmount = BigDecimalValidator.getInstance().validate(regularAmount.getAmount());
        
        // If amount is an invalid number weeklyAmount will be null
        if (weeklyAmount == null) {
            Logger.info("regularAmount Amount format invalid, returning false");

            return false;    
        }
            
        // Test if Positive
        if (!BigDecimalUtils.is(weeklyAmount).isPositive()) {
            Logger.info("regularAmount Amount is not Positive, returning false");
        
            return false;
        }

        // Set scale of division to 4 dp to prevent infinite digit exception 
        // Strip trailing zeros so 100.600 passes as valid   
        // For Month frequency, amount isn't divisable into a weekly amount so is omitted
        // Week frequency means it's already in a weekly amount obviously
        switch(regularAmount.getFrequency()) {
            case TWO_WEEK :
                weeklyAmount = weeklyAmount.divide(new BigDecimal("2"), 4, RoundingMode.HALF_UP).stripTrailingZeros();
            break;
            case FOUR_WEEK :
                weeklyAmount = weeklyAmount.divide(new BigDecimal("4"), 4, RoundingMode.HALF_UP).stripTrailingZeros();
            break;
            case QUARTER :
                weeklyAmount = weeklyAmount.divide(new BigDecimal("13"), 4, RoundingMode.HALF_UP).stripTrailingZeros();
            break;
            case YEAR :
                weeklyAmount = weeklyAmount.divide(new BigDecimal("52"), 4, RoundingMode.HALF_UP).stripTrailingZeros();
            break;

            default:
        }

        Logger.info("weeklyAmount: {}", weeklyAmount);

        // If scale > 2, division didn't result in whole number of pence i.e. 100.123
        if (weeklyAmount.scale() > 2) {
            Logger.info("weeklyAmount is invalid, returning false");
            return false;
        }

        return true;
    }
    
    private static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }            
}