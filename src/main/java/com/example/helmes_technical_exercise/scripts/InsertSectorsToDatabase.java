package com.example.helmes_technical_exercise.scripts;

import com.example.helmes_technical_exercise.entities.Sector;
import com.example.helmes_technical_exercise.services.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

@Component
public class InsertSectorsToDatabase implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SectorService sectorService;
    private Stack<Integer> parentStack = new Stack<>();


    /**
     * Populates the sectors table in the database on application start-up.
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Path to the text file containing sectors information
        String filePath = "src/main/resources/data/sectors.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            int id;
            int level;
            String name;
            int parentId;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 3) {
                    id = Integer.parseInt(parts[0]);
                    level = Integer.parseInt(parts[1].split("-")[1]);
                    name = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                    parentId = getParentId(id, level);

                    sectorService.insertSector(new Sector(id, level, name, parentId));
                }
            }

            System.out.println("Sectors inserted into the database.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the parent stack and returns the parent id of the sector with the passed id.
     */
    private int getParentId(int id, int level) {
        while (!parentStack.isEmpty() && level <= parentStack.size()) {
            parentStack.pop();
        }
        int parentId = parentStack.isEmpty() ? 0 : parentStack.peek();
        parentStack.push(id);
        return parentId;
    }
}