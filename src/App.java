import domain.CalculatorService;
import domain.CalculatorServiceImpl;
import infra.LoggerImpl;

import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        String filename = args[0];
        String operation = args[1];
        boolean logging = args.length > 2 && args[2].equals("-log");

        CalculatorService calculator = new CalculatorServiceImpl();
        String result = calculator.calculate(filename, operation, logging);

        LoggerImpl.log(result);
    }
}