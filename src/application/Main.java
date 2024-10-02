package application;

import entities.Sale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import javax.print.DocFlavor.STRING;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Entre com o nome do local do arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Sale> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Sale(Integer.parseInt(fields[0]),
                        Integer.parseInt(fields[1]),
                        fields[2],
                        Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4])));
                line = br.readLine();
            }

            System.out.println("LISTA DE VENDEDORES:");
            HashSet<String> list2 = new HashSet<String>();

            for (Sale sellers : list) {
                list2.add(sellers.getSeller());
            }

            for (String sellers : list2) {
                System.out.println(sellers);
            }

            System.out.println("SOMA TOTAL DE VENDAS POR VENDEDOR");
            HashMap<String, Double> list3 = new HashMap<>();

            Double totalSeller;

            for (String sellers : list2) {
                totalSeller = list.stream().filter(v -> v.getSeller().equals(sellers))
                        .map(v -> v.getTotal())
                        .reduce(0.0, (x,y) -> x + y);
                list3.put(sellers, totalSeller);
            }

            for (Entry<String, Double> vendedor : list3.entrySet()) {
                System.out.print(vendedor.getKey() + " - R$ ");
                System.out.println(String.format("%.2f", vendedor.getValue()));
            }



        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}