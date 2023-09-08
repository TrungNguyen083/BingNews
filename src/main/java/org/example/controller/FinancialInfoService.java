package org.example.controller;

import org.example.model.FinancialInfo;

import java.io.IOException;
import java.util.List;

public class FinancialInfoService implements Service{

    public FinancialInfoService() throws IOException {
        readConfig();
    }

    @Override
    public void readConfig() throws IOException {

    }

    public List<FinancialInfo> getFinancialInfo() {
        return null;
    }
}
