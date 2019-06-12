package Teste;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHora {

    public static void main(String[] args) {

        LocalDate hoje = LocalDate.now();
        System.out.println(hoje);
        DateTimeFormatter formatoBrasil = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        System.out.println(hoje.format(formatoBrasil));

        LocalDate dataProva = LocalDate.of(2019, 6, 26);
        System.out.println("Dia: " + dataProva.getDayOfMonth());
        System.out.println("Mês: " + dataProva.getMonthValue());
        System.out.println("Ano: " + dataProva.getYear());

        LocalDate amanha = LocalDate.parse("2019-01-01");
        System.out.println(amanha);
        System.out.println(amanha.getYear());
    }
}
