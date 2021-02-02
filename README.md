# Report Generator application

The application generates Total Liabilites and Selection Liability reports. It is a maven 
application that packages the application into a JAR file that can be executed on any
platform with java >= 1.8.
 
General
--

**Input**
  - CSV file
  - External (JSON) (TODO)
  
**Output**
  - CSV files ( --csvOutput "report.csv" will be appended to the generated filenames)
    - totalLiabilitesreport.csv
    - selectionLiabilitesreport.csv
    
  - Console 
 
Run Tests
---

> mvn clean tests


Build 
---

> mvn package

Jar is located at `target\ReportGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar` 

Execute
---

``` 
java -jar ReportGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar --csvInput data.csv

```

 - sample data.csv is provided with main application
 - Default - the output will be directed towards console
 
To send output to csv files provide `--csvOutput` with file name i.e. report_2021.csv 

```
java -jar ReportGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar --csvInput data.csv --csvOutput report_2021.csv

```

- This will produce 2 files 
  - totalLiabilities_report_2021.csv
  - selectionLiabilities_report_2021.csv
  
```
#Usage
java -jar ReportGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar --help

usage: ReportGenerator
    --console            Output to console
    --csvInput <file>    CSV file input
    --csvOutput <file>   CSV output file
    --help               Usage
```






