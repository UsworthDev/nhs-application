# NHS Java Demo

Demonstration project showing JSR-303 annotation using custom ConstraintValidator.
Based on the following class definitions:
```
public class RegularAmount {
    private Frequency frequency;
    private String amount;
    public Frequency getFrequency() {
        return frequency;
    }
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
public enum Frequency {
    WEEK, TWO_WEEK, FOUR_WEEK, MONTH, QUARTER, YEAR;
}
```

This project validates a RegularAmount instance using JSR-303 annotations to ensure that any value entered is a multiple of a whole number of pence when divided to a one week frequency.
Monthly incomes will not divide to a whole weekly value.

Weekly values for Frequency:

* Week - 1
* Two Week - 2
* Four Week - 4
* Month - N/A does not divide to weekly amount
* Quarter - 13
* Year - 52

## Module Overview
* Main Class `com.nhs.demo.NhsApplication` contains empty main.
* Client class `com.nhs.demo.model.CustomerSubmission` composed of a `RegularAmount` object.
* `com.nhs.demo.model.RegularAmount` the object to be validated.
* Frequency Enum `com.nhs.demo.enums.Frequency`. 
* Constraint Validator classes `com.nhs.demo.validator.ValidRegularAmountValidator` and `com.nhs.demo.validator.ValidRegularAmount`
* JUnit tests contained in `test..com.nhs.demo.NhsApplicationTest`
* Application logfile output in project root `NhsApplication_log.txt` 

## Running the tests

Tests are ran using the native JUnit support of the Maven Surefire Plugin.
```
mvn clean test
```
Output of tests are contained in `NhsApplication_log.txt`.

Tests are provided within `test..com.nhs.demo.NhsApplicationTest` and contain several examples of various valid / invalid combinations of Frequency and Amounts.

For example, a Regular Amount with a Two Week frequency and an amount of 97.53 would produce an invalid result, (97.53 / 2 = 48.765).
A valid Regular Amount producing a value in whole pence would have a Quartely frequency and a 331.37 amount, (331.37 / 13 = 25.49).


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **David Wilson** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
