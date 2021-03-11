package com.boc;

import com.boc.entities.Climate;
import com.boc.repositories.ClimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBeanBuilder;

import java.util.List;

@Component
public class SourceDataLoader implements CommandLineRunner {

    @Autowired
    ClimateRepository climateRepository;

    @Override
    public void run(String... args) throws Exception {
        
        String workingDir = System.getProperty("user.dir");
        
        List<Climate> beans = new CsvToBeanBuilder(new FileReader(workingDir+"\\src\\main\\resources\\csv\\eng-climate-summary.csv"))
                .withType(Climate.class)
                .build()
                .parse();

        //beans.forEach(System.out::println);
        
        beans.listIterator().toString();
        
        Iterator<Climate> it = beans.iterator();
        long index = 0;
        while(it.hasNext()) {
        	Climate i = it.next();
 
        	climateRepository.save(i);
        }
    }
}
