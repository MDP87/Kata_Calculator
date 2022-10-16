package KataCalculator;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


class Calculator {
  private static Calculator calculator = new Calculator("", "");
  public   String firstOperand;
  public   String secondOperand;
  public  String operator;

    private Calculator(String firstOperand, String secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public boolean chekString(String chekinngString) throws Exception {
        chekinngString =  chekinngString.trim();
        chekinngString = chekinngString.replaceAll(" ", "");
        String[] strArr = chekinngString.split("[+*\\-/\\s]");
        String [] arrForOperator = chekinngString.replaceAll("[\\w\\s]", "").split("[^\\W]");
        operator = arrForOperator[0];
        if (strArr.length > 2)     // проверка на формат мат операции
         throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        if (strArr.length == 1 ||( strArr.length == 0 && arrForOperator.length == 1))
            throw new Exception("строка не является математической операцией");
        if (operator.contains(".") || operator.contains(","))
            throw new Exception("Введите целое число. Калькулятор умеет работать только с целыми числами");
       // проверка операндов и их присвоение
        if (strArr.length == 2) {
            this.firstOperand = strArr[0];
            this.secondOperand = strArr[1];
            if (Character.isDigit(firstOperand.charAt(0)) && Character.isDigit(secondOperand.charAt(0))) {
             return true;
            }
            else {
                if (Character.isLetter(firstOperand.charAt(0)) && Character.isLetter(secondOperand.charAt(0)))
                    return true;
                else
                    throw new Exception("используются одновременно разные системы счисления");
            }
        }

        return false;
    }
    public boolean isArabic(){
        return  Character.isDigit(firstOperand.charAt(0));

    }

    public Integer calculateArabic() throws Exception{
        int result;
        if ((Integer.parseInt(firstOperand) > 10 || Integer.parseInt(firstOperand) < 1 ) || (Integer.parseInt(secondOperand) > 10 || Integer.parseInt(secondOperand) < 1) )
            throw new Exception("Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более");
        switch (operator) {
            case ("+"):
                result = Integer.parseInt(firstOperand) + Integer.parseInt(secondOperand);
            break;
            case ("-"):
                result = Integer.parseInt(firstOperand) - Integer.parseInt(secondOperand);
            break;
            case ("*"):
                result = Integer.parseInt(firstOperand) * Integer.parseInt(secondOperand);
            break;
            case ("/"):
                result = Integer.parseInt(firstOperand) / Integer.parseInt(secondOperand);
            break;
            default:
                return null;
        }
        return result;
    }
    public String calculateRoman() throws Exception{
        String[] romanString = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int first = 0;
        int second = 0;
        int result;
        for (int i = 0; i < romanString.length; i++ ){
            if (firstOperand.equals(romanString[i]))
                first = i + 1;
            if (secondOperand.equals(romanString[i]))
                second = i + 1;
        }
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(romanString));
            if (!(arrayList.contains(firstOperand) && arrayList.contains(secondOperand)))
            throw new Exception("Калькулятор должен принимать на вход риские числа от I до X включительно,  не более");
        switch (operator) {
            case ("+"):
                result = first + second;
                break;
            case ("-"):
                result = first - second;
                break;
            case ("*"):
                result = first * second;
                break;
            case ("/"):
                result = first / second;
                break;
            default:
                return null;
        }
        if (result < 1)
            throw new Exception("в римской системе нет отрицательных чисел а также ноля");
        return IntegerConverter.intToRoman(result);
    }
    public static void main(String[] args) {
        System.out.println("Введите строку выражение");
          try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)))
            {
              String string = bufferedReader.readLine();
                if (calculator.chekString(string)){
                    if (calculator.isArabic()){
                        System.out.println(calculator.calculateArabic());
                    }
                    else {
                        System.out.println(calculator.calculateRoman());
                    }
                }
                else
                    throw new Exception("произошла ошибка");
            }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


}


