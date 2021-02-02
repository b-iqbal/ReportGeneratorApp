package com.report;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.report.models.Bet;
import com.report.models.TotalLiabilities;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.tuple.Pair;
import com.report.views.SelectionLiabilitiesReport;
import com.report.models.SelectionLiability;
import com.report.views.TotalLiabilitiesReport;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class ReportGenerator {

    private File file;

    public ReportGenerator(File file){
        this.file = file;
    }

    private List<Bet> getData(){
        try {
            return (List<Bet>) new CsvToBeanBuilder(new FileReader(file))
                    .withType(Bet.class)
                    .build()
                    .parse();

        }catch (FileNotFoundException fne){
            System.out.println("Specified file not found.");
            throw new RuntimeException(fne);
        }
    }

    private TotalLiabilitiesReport generateTotalLiabilities(){
        List<Bet> bets = getData();
        Map<String, ArrayList<Bet>> betsByCurrency = new HashMap<String, ArrayList<Bet>>();
        for(Bet bet: bets){
            if(betsByCurrency.containsKey(bet.getCurrency().trim())){
                betsByCurrency.get(bet.getCurrency().trim()).add(bet);
            }else{
                ArrayList<Bet> newList = new ArrayList<Bet>();
                newList.add(bet);
                betsByCurrency.put(bet.getCurrency().trim(), newList);
            }
        }
        List<TotalLiabilities> totalLiabilities = new ArrayList<>();
        betsByCurrency.forEach((k, v) -> {
            Double totalStake = v.stream().collect(Collectors.summingDouble(bet -> bet.getStake()));
            Double liability = v.stream().collect(Collectors.summingDouble(bet -> bet.getStake() * bet.getPrice()));
            TotalLiabilities totalLiability = new TotalLiabilities(k, (int) v.stream().count(), totalStake, liability);
            totalLiabilities.add(totalLiability);
        });
        return  new TotalLiabilitiesReport(totalLiabilities);
    }




    public static void main(String[] args) throws Exception {
         //input: csv or json
        //output: console, csv
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        options.addOption(Option.builder().longOpt("help").desc("Usage ").build());
        options.addOptionGroup(getInputOptions());
        options.addOptionGroup(getOutputOptions());
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            if(cmd.hasOption("help")){
                formatter.printHelp("ReportGenerator", options );
                return;
            }
            handleCommand(cmd);
        } catch (ParseException e) {
            System.err.println("An error occurred while parsing");
            formatter.printHelp("ReportGenerator", options );

        }
    }

    private static void handleCommand(CommandLine cmd) throws Exception {
        SelectionLiabilitiesReport selectionLiabilitiesReport= null;
        TotalLiabilitiesReport totalLiabilitiesReport = null;
        if (cmd.hasOption("csvInput")){
            String filepath = cmd.getOptionValue("csvInput");
            File file = new File(filepath);
            System.out.println("Generating reports with input csv file");
            ReportGenerator reportGenerator = new ReportGenerator(file);
            selectionLiabilitiesReport = reportGenerator.generateSelectionLiabilities();
            totalLiabilitiesReport = reportGenerator.generateTotalLiabilities();
        }if(cmd.hasOption("csvOutput")){
            String outputFile = cmd.getOptionValue("csvOutput");
            writeToFile("selectionLiabilities_" + outputFile, selectionLiabilitiesReport.getSelectionLiabilityList());
            writeToFile("totalLiabilities_" + outputFile, totalLiabilitiesReport.getTotalLiabilitiesList());

        }else{
            selectionLiabilitiesReport.display();
            totalLiabilitiesReport.display();
        }
    }

    private static void writeToFile(String filename, List<?> reports) throws Exception {
        Writer writer = new FileWriter(new File(filename));

        StatefulBeanToCsv sbc = new
                StatefulBeanToCsvBuilder(writer)
                .withSeparator(com.opencsv.CSVWriter.DEFAULT_SEPARATOR)
                .build();
        sbc.write(reports);
        writer.close();
    }

    private static OptionGroup getOutputOptions(){

        OptionGroup optionGroup = new OptionGroup();

        Option csvOutput = Option.builder()
                .longOpt("csvOutput")
                .argName("file")
                .hasArg()
                .desc("CSV output file")
                .build();

        Option console = Option.builder()
                .longOpt("console")
                .desc("Output to console")
                .build();

        optionGroup.addOption(console);
        optionGroup.addOption(csvOutput);
        return optionGroup;
    }
    private static OptionGroup getInputOptions(){
        OptionGroup optionGroup = new OptionGroup();
        Option csvInput = Option.builder()
                .longOpt("csvInput")
                .argName("file")
                .hasArg()
                .desc("CSV file input")
                .build();

        optionGroup.addOption(csvInput);
        return optionGroup;
    }

    private SelectionLiabilitiesReport generateSelectionLiabilities() {
        List<Bet> bets = getData();
        Map<Pair, SelectionLiability> selectionDataMap = new HashMap<>();
        for(Bet bet: bets){
            if(selectionDataMap.containsKey(Pair.of(bet.getSelectionId(), bet.getCurrency()))){
                SelectionLiability existingLiability = selectionDataMap.get(Pair.of(bet.getSelectionId(), bet.getCurrency()));
                existingLiability.updateData(bet.getStake(), bet.getPrice());
            }else{
                SelectionLiability selectionLiability = new SelectionLiability(bet.getSelectionName(),
                        bet.getCurrency(), bet.getStake(), bet.getStake() * bet.getPrice());
                selectionDataMap.put(Pair.of(bet.getSelectionId(), bet.getCurrency()), selectionLiability);
            }
        }
        return  new SelectionLiabilitiesReport(selectionDataMap.values().stream().collect(Collectors.toList()));

    }
}
