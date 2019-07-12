package com.nhs.demo;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.nhs.demo.model.RegularAmount;
import com.nhs.demo.model.CustomerSubmission;
import com.nhs.demo.enums.Frequency;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.pmw.tinylog.Logger;

public class NhsApplicationTest {
    
    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();        
        Logger.info("*** NhsApplicationTest Test Start");
    }

    @Test
    public void regularAmountInstanceIsNullInvalid() {    
        Logger.info("*** Testing for Null Regular Amount instance");

        CustomerSubmission customerSubmission = new CustomerSubmission();
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
        
        assertEquals( 1, constraintViolations.size() );
        assertEquals( "RegularAmount required", constraintViolations.iterator().next().getMessage() );
    }    

    @Test
    public void frequencyAndAmountIsNullInvalid() {    
        Logger.info("*** Testing for Null Frequency and Amount");

        RegularAmount regularAmount = new RegularAmount();

        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);

        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);

        assertEquals( 2, constraintViolations.size() );

        assertTrue(isExpectedConstraintViolated("Amount required", constraintViolations));
        assertTrue(isExpectedConstraintViolated("Frequency required", constraintViolations));
    }        

    @Test
    public void frequencyAndAmountValid() {
        Logger.info("*** Testing for valid Frequency and Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        regularAmount.setAmount("200.50");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );        
    }   
    
    @Test
    public void penceOnlyAmountInvalid() {
        Logger.info("*** Testing for invalid Pence only Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.QUARTER);
        regularAmount.setAmount("0.97");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));        
    }        

    @Test
    public void penceOnlyAmountValid() {
        Logger.info("*** Testing for valid Pence only Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.QUARTER);
        regularAmount.setAmount("0.13");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );  
    }        

    @Test
    public void poundsOnlyAmountInvalid() {
        Logger.info("*** Testing for invalid Pounds only Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.YEAR);
        regularAmount.setAmount("12345");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));        
    }        
    
    @Test
    public void poundsOnlyAmountValid() {
        Logger.info("*** Testing for valid Pounds only Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.FOUR_WEEK);
        regularAmount.setAmount("2632");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );
    }       

    @Test
    public void monthFrequencyValid() {
        Logger.info("*** Testing for Month Frequency (No division)");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.MONTH);
        regularAmount.setAmount("99999.99");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );
    }  
    
    @Test
    public void amountInvalid() {
        Logger.info("*** Testing for invalid Amount");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.YEAR);
        regularAmount.setAmount("3410.62");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));        
    }        

    @Test
    public void amountValid1() {
        Logger.info("*** Testing for valid Amount_1");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.QUARTER);
        regularAmount.setAmount("3345.68");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );        
    }        

    @Test
    public void amountValid2() {
        Logger.info("*** Testing for valid Amount_2");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.YEAR);
        regularAmount.setAmount("74714.12");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 0, constraintViolations.size() );        
    }        

    @Test
    public void amountFormatInvalid() {
        Logger.info("*** Testing for invalid Amount format");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        regularAmount.setAmount("NOTANUMBER");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));               
    }   
    
    @Test
    public void amountNegativeInvalid() {
        Logger.info("*** Testing for invalid Amount negative");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        regularAmount.setAmount("-341.87");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));               
    }                    

    @Test
    public void amountZeroInvalid() {
        Logger.info("*** Testing for invalid Amount zero");

        RegularAmount regularAmount = new RegularAmount();
    
        regularAmount.setFrequency(Frequency.TWO_WEEK);
        regularAmount.setAmount("0.00");
    
        CustomerSubmission customerSubmission = new CustomerSubmission();
        customerSubmission.setRegularAmount(regularAmount);
        
        Set<ConstraintViolation<CustomerSubmission>> constraintViolations =
        validator.validate(customerSubmission);
    
        assertEquals( 1, constraintViolations.size() );
        assertTrue(isExpectedConstraintViolated("ValidRegularAmount Regular Amount invalid" , constraintViolations));               
    }                        

    private boolean isExpectedConstraintViolated(String message, Set<ConstraintViolation<CustomerSubmission>> constraintViolations) {

        for (ConstraintViolation<CustomerSubmission> constraintViolation : constraintViolations)
        {
            if(constraintViolation.getMessage().equals(message))
                return true;
        }

        return false;
    }

}
