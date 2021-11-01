package com.ayush.tracker.service;

import com.ayush.tracker.model.*;

import java.text.*;
import java.util.*;

public interface TranscationService {
    List<Transaction> allTranscation();
    Transaction saveTranscation(Transaction transaction) throws ParseException;
    Boolean deleteTranscation(int id);

    List<Transaction> getIncome();
    List<Transaction> getExpense();
}
