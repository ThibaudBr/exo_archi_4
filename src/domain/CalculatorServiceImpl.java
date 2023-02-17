package domain;

import infra.FileParser;
import infra.FileParserImpl;
import infra.LoggerImpl;

import static infra.LoggerImpl.getTimestamp;

public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public String calculate(String filename, String operation, boolean logging) {
        int result = 0;
        int lineNumber = 1;

        try {
            FileParser parser = new FileParserImpl();
            parser.openFile(filename);
            String line;
            while ((line = parser.readLine()) != null) {
                try {
                    int value = Integer.parseInt(line.trim());
                    if (operation.equals("+")) {
                        result += value;
                    } else if (operation.equals("-")) {
                        result -= value;
                    } else if (operation.equals("*")) {
                        result *= value;
                    }

                    if (logging) {
                        String logMessage = String.format("[%s][log] parsed value = %d",
                                lineNumber, value);
                        LoggerImpl.log(logMessage);

                        logMessage = String.format("[%s][log] accumulation : %s on line %d",
                                lineNumber, result, lineNumber);
                        LoggerImpl.log(getOperationSymbol(operation) + value + " (= " + result + ")");
                        LoggerImpl.log(logMessage);
                    }
                } catch (NumberFormatException e) {
                    String errorMessage = String.format("[%s][error] Invalid value on line %d",
                            lineNumber, lineNumber);
                    LoggerImpl.error(errorMessage);
                }
                lineNumber++;
            }
        } catch (Exception e) {
            String errorMessage = String.format("[%s][error] Error reading file %s", 0, filename);
            LoggerImpl.error(errorMessage);
        }

        return formatResult(result, operation, logging);
    }


    private String getOperationSymbol(String operation) {
        if (operation.equals("+")) {
            return "+";
        } else if (operation.equals("-")) {
            return "-";
        } else if (operation.equals("*")) {
            return "*";
        } else {
            return "";
        }
    }

    private String formatResult(int result, String operation, boolean logging) {
        StringBuilder sb = new StringBuilder();
        sb.append("Total = ");
        sb.append(result);
        sb.append("\n");

        if (logging) {
            sb.append(String.format("%s [%s][log] end of program", getTimestamp(), 0));
        } else {
            sb.append(getOperationSymbol(operation));
            sb.append(" ");
            sb.append(result);
            sb.append(" (total)");
        }

        return sb.toString();
    }
}