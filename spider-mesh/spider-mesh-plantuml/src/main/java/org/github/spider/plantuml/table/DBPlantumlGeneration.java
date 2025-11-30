package org.github.spider.plantuml.table;

import org.github.spider.api.table.ITableGenerate;
import org.github.spider.api.table.ITableModelDraw;
import org.github.spider.model.call.filter.FilterConfigureWrapper;
import org.github.spider.model.mysql.DBTable;
import org.github.spider.model.mysql.MySQLProcessor;
import org.github.spider.plantuml.GenerationWriter;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DBPlantumlGeneration implements ITableGenerate {

    private static final Logger logger = LoggerFactory.getLogger(FilterConfigureWrapper.class);

    private static ITableModelDraw drawImpl;
    private String outputDirectory;
    private List<MvnRepoSourceParam> mvnRepoSourceParams;

    public DBPlantumlGeneration(String outputDirectory, List<MvnRepoSourceParam> mvnRepoSourceParams) {
        this.outputDirectory = outputDirectory;
        this.mvnRepoSourceParams = mvnRepoSourceParams;
    }


    @Override
    public String generate() {
        MySQLProcessor mySQLProcessor = new MySQLProcessor(mvnRepoSourceParams);
        List<DBTable> tables;
        try {
            tables = mySQLProcessor.process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (tables == null) {
            return null;
        }
        drawImpl = new DBPlantumlDrawImpl();
        String body = drawImpl.draw(tables);
        logger.info(String.format("Prepare to generate:tables=%s", tables.size()));
        return GenerationWriter.write(outputDirectory, body);
    }

}