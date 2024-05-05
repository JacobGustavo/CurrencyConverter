import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            HashMap<Integer, String> currencies = new HashMap<>();
            currencies.put(1, "USD");
            currencies.put(2, "BRL");
            currencies.put(3, "CAD");
            currencies.put(4, "EUR");
            currencies.put(5, "GBP");
            currencies.put(6, "JPY");

            System.out.println("Bem-vindo ao conversor de moedas!");

            System.out.println("Escolha a moeda de origem:");
            printCurrencyOptions(currencies);
            String baseCurrency = currencies.get(scanner.nextInt());

            System.out.println("Escolha a moeda de destino:");
            printCurrencyOptions(currencies);
            String destinationCurrency = currencies.get(scanner.nextInt());

            System.out.println("Digite o valor a ser convertido:");
            double amount = scanner.nextDouble();

            ApiHandler apiHandler = new ApiHandler();
            double conversionRate = apiHandler.getConversionRate(baseCurrency, destinationCurrency);
            if (conversionRate != -1) {
                double convertedAmount = amount * conversionRate;
                System.out.printf("%.2f %s equivale a %.2f %s%n", amount, baseCurrency, convertedAmount, destinationCurrency);
            } else {
                System.out.println("Erro ao obter taxa de conversão. Por favor, tente novamente mais tarde.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao se comunicar com a API. Por favor, verifique sua conexão com a internet.");
        }
    }

    private static void printCurrencyOptions(HashMap<Integer, String> currencies) {
        for (int key : currencies.keySet()) {
            System.out.printf("%d - %s%n", key, currencies.get(key));
        }
    }
}